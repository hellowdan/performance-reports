package org.benchmarks.reports.builder;

import com.google.api.client.http.FileContent;
import com.google.api.services.docs.v1.Docs;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Revision;
import com.google.api.services.drive.model.RevisionList;
import com.google.api.services.sheets.v4.Sheets;
import com.google.common.collect.Iterables;
import org.apache.commons.io.FileUtils;
import org.benchmarks.reports.data.FileLocation;
import org.benchmarks.reports.util.HttpOperations;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/*Provides the general Google authentication for the inherited classes.
 *Already implemented for Drools.*/
public abstract class Report {

    protected Docs docsService;
    protected Drive driveService;
    protected Sheets sheetService;

    public Report() throws IOException, GeneralSecurityException {
        GoogleService googleService = new GoogleService();
        this.setDocsService(googleService.getDocs());
        this.setSheetService(googleService.getSheets());
        this.setDriveService(googleService.getDrive());
    }

    public Docs getDocsService() {
        return docsService;
    }

    public void setDocsService(Docs docsService) {
        this.docsService = docsService;
    }

    public Drive getDriveService() {
        return driveService;
    }

    public void setDriveService(Drive driveService) {
        this.driveService = driveService;
    }

    public Sheets getSheetService() {
        return sheetService;
    }

    public void setSheetService(Sheets sheetService) {
        this.sheetService = sheetService;
    }

    public abstract void generateReport() throws Exception;

    protected abstract Boolean createSpreadSheet() throws Exception;

    protected abstract String createDoc() throws IOException;

    protected String createNewDir(String folderTitle, String resultParentFolderID) throws IOException {
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

    protected String moveFile(String fileNewID, String folderNewID) {
        String newID = "";
        try {
            File fileToMove = this.driveService.files().get(fileNewID)
                    .setFields("parents")
                    .execute();

            StringBuilder previousParents = new StringBuilder();
            for (String parent : fileToMove.getParents()) {
                previousParents.append(parent);
                previousParents.append(',');
            }

            newID = this.driveService.files().update(fileNewID, null)
                    .setAddParents(folderNewID)
                    .setRemoveParents(previousParents.toString())
                    .setFields("id, parents")
                    .execute().getId();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return newID;
    }

    protected String copyFile(String fileTitle, String templateID) {
        String newID = "";
        try {
            File copiedFile = new File();
            copiedFile.setName(fileTitle);
            newID = this.getDriveService().files().copy(templateID, copiedFile).execute().getId();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newID;
    }

    protected ByteArrayOutputStream downloadFile(String fileID) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        this.driveService.files().get(fileID)
                .executeMediaAndDownloadTo(outputStream);
        return outputStream;
    }

    protected Boolean setPublishFile(String fileID) throws IOException {
        this.driveService.revisions().update(fileID, "head", new Revision()
                .setPublishAuto(true)
                .setPublished(true)
                .setPublishedOutsideDomain(true)
        )
                .setFields("id,mimeType,modifiedTime,publishAuto,published,publishedOutsideDomain")
                .execute();

        RevisionList revisions = this.driveService.revisions().list(fileID)
                .setFields("nextPageToken,revisions(id,mimeType,modifiedTime,publishAuto,published,publishedOutsideDomain)")
                .execute();

        final Revision updated = Iterables.getLast(revisions.getRevisions());

        return (updated.getPublishAuto() && updated.getPublished() && updated.getPublishedOutsideDomain());
    }

    protected void WriteToFile(ByteArrayOutputStream byteArrayOutputStream, String filePath) throws Exception {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            byteArrayOutputStream.writeTo(outputStream);
        }
    }

}
