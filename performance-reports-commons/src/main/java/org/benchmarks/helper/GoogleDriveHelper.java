package org.benchmarks.helper;

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
import org.benchmarks.definitions.JenkinsReportFileExtension;
import org.benchmarks.definitions.JenkinsReportVersion;
import org.benchmarks.exceptions.FileCannotBeSavedException;
import org.benchmarks.exceptions.GoogleDriveCopyFileException;
import org.benchmarks.exceptions.GoogleDriveDownloadFileException;
import org.benchmarks.exceptions.GoogleDriveFolderCreateException;
import org.benchmarks.exceptions.GoogleDriveMoveFileException;
import org.benchmarks.exceptions.GoogleDriveShareFileException;

/*Provides the general Google authentication for the inherited classes.
 *Already implemented for Drools.*/
public class GoogleDriveHelper {

    private GoogleDriveHelper() {
    }

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
            throw new GoogleDriveFolderCreateException(folderTitle, e);
        }

        return newID;
    }

    public static String moveFile(Drive driveService, String fileNewID, String folderNewID) throws IOException {
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
            throw new GoogleDriveMoveFileException(e);
        }

        return newID;
    }

    public static String copyFile(Drive driveService, String fileTitle, String templateID) throws IOException {
        String newID = "";
        try {
            File copiedFile = new File();
            copiedFile.setName(fileTitle);
            newID = driveService.files().copy(templateID, copiedFile).execute().getId();
        } catch (IOException e) {
            throw new GoogleDriveCopyFileException(e);
        }

        return newID;
    }

    public static ByteArrayOutputStream downloadFile(Drive driveService, String fileID) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            driveService.files().get(fileID).executeMediaAndDownloadTo(outputStream);
        } catch (IOException e) {
            throw new GoogleDriveDownloadFileException(fileID, e);
        }

        return outputStream;
    }

    public static Boolean setPublishFile(Drive driveService, String fileID) throws IOException {
        Boolean result;

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
            throw new GoogleDriveShareFileException(fileID, e);
        }

        return result;
    }

    public static Boolean writeToFile(ByteArrayOutputStream byteArrayOutputStream, String filePath) throws IOException {
        Boolean result = false;
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            byteArrayOutputStream.writeTo(outputStream);
            result = true;
        } catch (IOException e) {
            throw new FileCannotBeSavedException(filePath, e);
        }

        return result;
    }

    public static String prepareGoogleDriveFile(Drive driveService, String fileType, String fileID, String localDir, JenkinsReportVersion jenkinsReportVersion, JenkinsReportFileExtension jenkinsReportFileExtension) throws IOException {
        String filePath = "";
        ByteArrayOutputStream byteArrayOutputStream;

        try {
            filePath = localDir + jenkinsReportVersion.toString() + fileType + "." + jenkinsReportFileExtension.getExtension();
            byteArrayOutputStream = GoogleDriveHelper.downloadFile(driveService, fileID);
            writeToFile(byteArrayOutputStream, filePath);
        } catch (Exception e) {
            throw new GoogleDriveDownloadFileException(fileID, e);
        }

        return filePath;
    }
}
