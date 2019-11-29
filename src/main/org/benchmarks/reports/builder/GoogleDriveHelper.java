package org.benchmarks.reports.builder;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Revision;
import com.google.api.services.drive.model.RevisionList;
import com.google.common.collect.Iterables;
import org.benchmarks.reports.data.FileExtension;
import org.benchmarks.reports.data.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*Provides the general Google authentication for the inherited classes.
 *Already implemented for Drools.*/
public class GoogleDriveHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveHelper.class);

    public static String createNewDir(Drive driveService, String folderTitle, String resultParentFolderID) {
        String newID = "";
        try {
            File body = new File();
            body.setName(folderTitle);
            body.setParents(Arrays.asList(resultParentFolderID));
            body.setMimeType("application/vnd.google-apps.folder");
            File file = driveService.files().create(body).setFields("id").execute();
            newID = file.getId();
        } catch (IOException e) {
            LOGGER.debug("Failed creating new folder on Google Drive: " + folderTitle, e);
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
            LOGGER.debug("Failed moving a file to a new folder on Google Drive.", e);
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
            LOGGER.debug("Failed copying a file on Google Drive.", e);
        }

        return newID;
    }

    public static ByteArrayOutputStream downloadFile(Drive driveService, String fileID) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            driveService.files().get(fileID).executeMediaAndDownloadTo(outputStream);
        } catch (IOException e) {
            LOGGER.debug("Failed downloading a file from Google Drive.", e);
        }

        return outputStream;
    }

    public static Boolean setPublishFile(Drive driveService, String fileID) {
        Boolean result = false;

        try {
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
            result = (updated.getPublishAuto() && updated.getPublished() && updated.getPublishedOutsideDomain());
        } catch (IOException e) {
            LOGGER.debug("Failed sharing a file on Google Drive.", e);
        }

        return result;
    }

    public static void writeToFile(ByteArrayOutputStream byteArrayOutputStream, String filePath) {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            byteArrayOutputStream.writeTo(outputStream);
        } catch (IOException e) {
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
