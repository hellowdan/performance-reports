package org.benchmarks.drools.reports.builder;

import com.google.api.services.drive.model.File;
import java.io.IOException;
import org.benchmarks.drools.reports.data.DroolsProperties;
import org.benchmarks.reports.builder.Report;
import org.json.simple.parser.ParseException;

import java.security.GeneralSecurityException;
import java.util.Arrays;

public class DroolsReport extends Report {

    private DroolsProperties reportProperties;
    private String folderNewId;
    private String spreadSheetNewId;
    private String docNewId;

    public DroolsReport() throws IOException, GeneralSecurityException {
        this.reportProperties = DroolsProperties.getInstance();
    }

    /*for tests purposes*/
    public DroolsReport(DroolsProperties reportProperties) throws IOException, GeneralSecurityException {
        this.reportProperties = reportProperties;
    }

    public void generateReport() throws IOException, ParseException {
        createNewDir();
        createSpreadSheet();
        createDoc();
    }

    private void createNewDir() throws IOException {
        File body = new File();
        body.setName(this.reportProperties.getFolderTitle());
        body.setParents(Arrays.asList(this.reportProperties.getResultParentFolderID()));
        body.setMimeType("application/vnd.google-apps.folder");
        File file = driveService.files().create(body).setFields("id").execute();
        this.folderNewId = file.getId();
    }

    private void createSpreadSheet() throws IOException, ParseException {
        this.spreadSheetNewId = copyFile(this.reportProperties.getTemplateSheetID());
        DroolsFileSpreadSheet droolsFileSpreadSheet = new DroolsFileSpreadSheet(this.spreadSheetNewId,
                this.reportProperties, this.getSheetService());
        droolsFileSpreadSheet.updateFile();
        moveFile(this.spreadSheetNewId);
    };

    protected void createDoc() throws IOException{
        this.docNewId = copyFile(this.reportProperties.getTemplateDocID());
        DroolsFileDoc droolsFileDoc = new DroolsFileDoc(this.docNewId,
                this.reportProperties, this.getDocsService());
        droolsFileDoc.updateFile();
        moveFile(this.docNewId);
    };

    private void moveFile(String fileNewID){
        try {
            File fileToMove = this.driveService.files().get(fileNewID)
                    .setFields("parents")
                    .execute();

            StringBuilder previousParents = new StringBuilder();
            for (String parent : fileToMove.getParents()) {
                previousParents.append(parent);
                previousParents.append(',');
            }

            this.driveService.files().update(fileNewID, null)
                    .setAddParents(this.folderNewId)
                    .setRemoveParents(previousParents.toString())
                    .setFields("id, parents")
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String copyFile(String templateID){
        File copiedFile = new File();
        String newID = "";
        try {
            copiedFile.setName(this.reportProperties.getFilesTitle());
            newID = this.getDriveService().files().copy(templateID, copiedFile).execute().getId();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newID;
    }
}
