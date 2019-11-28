package org.benchmarks.reports.builder;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Revision;
import com.google.api.services.drive.model.RevisionList;
import com.google.common.collect.Iterables;
import org.benchmarks.reports.data.FileExtension;
import org.benchmarks.reports.data.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;

/*Provides the general Google authentication for the inherited classes.
 *Already implemented for Drools.*/
public class GoogleDriveHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveHelper.class);

    public static String createNewDir(Drive driveService, String folderTitle, String resultParentFolderID) throws IOException {
        String newID = "";
        try {
            File body = new File();
            body.setName(folderTitle);
            body.setParents(Arrays.asList(resultParentFolderID));
            body.setMimeType("application/vnd.google-apps.folder");
            File file = driveService.files().create(body).setFields("id").execute();
            newID = file.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newID;
    }

    public static String moveFile(Drive driveService, String fileNewID, String folderNewID) {
        String newID = "";
        try {
            File fileToMove = driveService.files().get(fileNewID)
                    .setFields("parents")
                    .execute();

            StringBuilder previousParents = new StringBuilder();
            for (String parent : fileToMove.getParents()) {
                previousParents.append(parent);
                previousParents.append(',');
            }

            newID = driveService.files().update(fileNewID, null)
                    .setAddParents(folderNewID)
                    .setRemoveParents(previousParents.toString())
                    .setFields("id, parents")
                    .execute().getId();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newID;
    }

    public static String copyFile(Drive driveService, String fileTitle, String templateID) {
        String newID = "";
        try {
            File copiedFile = new File();
            copiedFile.setName(fileTitle);
            newID = driveService.files().copy(templateID, copiedFile).execute().getId();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newID;
    }

    public static ByteArrayOutputStream downloadFile(Drive driveService, String fileID) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        driveService.files().get(fileID)
                .executeMediaAndDownloadTo(outputStream);
        return outputStream;
    }

    public static Boolean setPublishFile(Drive driveService, String fileID) throws IOException {
        driveService.revisions().update(fileID, "head", new Revision()
                .setPublishAuto(true)
                .setPublished(true)
                .setPublishedOutsideDomain(true)
        )
                .setFields("id,mimeType,modifiedTime,publishAuto,published,publishedOutsideDomain")
                .execute();

        RevisionList revisions = driveService.revisions().list(fileID)
                .setFields("nextPageToken,revisions(id,mimeType,modifiedTime,publishAuto,published,publishedOutsideDomain)")
                .execute();

        final Revision updated = Iterables.getLast(revisions.getRevisions());

        return (updated.getPublishAuto() && updated.getPublished() && updated.getPublishedOutsideDomain());
    }

    public static void writeToFile(ByteArrayOutputStream byteArrayOutputStream, String filePath) {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            byteArrayOutputStream.writeTo(outputStream);
        } catch (Exception e) {
            LOGGER.debug("Failed to write file to disk: " + filePath, e);
        }
    }

    public static String prepareGoogleDriveFile(Drive driveService, String fileType, String fileID, Version version, FileExtension fileExtension) {
        String filePath = "";
        ByteArrayOutputStream byteArrayOutputStream;

        try {
            filePath = version.toString() + fileType + "." + fileExtension.getExtension();
            byteArrayOutputStream = GoogleDriveHelper.downloadFile(driveService, fileID);
            writeToFile(byteArrayOutputStream, filePath);
        } catch (Exception e) {
            LOGGER.debug("Failed to download file from Google Drive: " + fileID + " - " +
                                 version.toString() + fileType + "." + fileExtension.getExtension(), e);
        }

        return filePath;
    }
}
