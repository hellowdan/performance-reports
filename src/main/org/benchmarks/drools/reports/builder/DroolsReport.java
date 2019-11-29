package org.benchmarks.drools.reports.builder;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import org.benchmarks.drools.reports.data.DroolsProperties;
import org.benchmarks.reports.builder.GoogleDriveHelper;
import org.benchmarks.reports.builder.GoogleDriveService;
import org.benchmarks.reports.data.FileLocation;
import org.benchmarks.reports.data.InputFileType;
import org.benchmarks.reports.data.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolsReport {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsReport.class);

    private static String startingCellNewVersion = "K2";
    private static String startingCellPreviousVersion = "J2";
    private static String startingCellOlderVersion = "I2";
    private DroolsProperties reportProperties;
    private String folderNewId;
    private String spreadSheetNewId;
    private String docNewId;
    private Drive driveService;
    private Docs docsService;
    private Sheets sheetsService;

    public DroolsReport() {
        try {
            this.reportProperties = DroolsProperties.getInstance();

            GoogleDriveService googleDriveService = new GoogleDriveService();
            this.driveService = googleDriveService.getDrive();
            this.docsService = googleDriveService.getDocs();
            this.sheetsService = googleDriveService.getSheets();
        } catch (IOException e) {
            LOGGER.debug("File cannot be read.", e);
        }
    }

    /*for tests purposes*/
    public DroolsReport(DroolsProperties reportProperties) {
        this.reportProperties = reportProperties;
    }

    public void generateReport() {
        try {
            this.folderNewId = GoogleDriveHelper.createNewDir(this.driveService, this.reportProperties.getFolderTitle(), this.reportProperties.getResultParentFolderID());
            getGoogleDriveFiles();
            createSpreadSheet();
            createDoc();
        } catch (Exception e) {
            LOGGER.debug("File cannot be read.", e);
        }
    }

    protected Boolean createSpreadSheet() {
        this.spreadSheetNewId = GoogleDriveHelper.copyFile(this.driveService, this.reportProperties.getFilesTitle(), this.reportProperties.getTemplateSheetID());
        DroolsGoogleDriveSpreadSheet droolsFileSpreadSheet = new DroolsGoogleDriveSpreadSheet(this.spreadSheetNewId, this.reportProperties, this.sheetsService);
        droolsFileSpreadSheet.updateSpreadSheetInfo();
        droolsFileSpreadSheet.updateSpreadSheetValues(Version.NEW, this.reportProperties.getNewVersionFileLocation(), startingCellNewVersion);
        droolsFileSpreadSheet.updateSpreadSheetValues(Version.PREVIOUS, this.reportProperties.getPreviousVersionFileLocation(), startingCellPreviousVersion);
        droolsFileSpreadSheet.updateSpreadSheetValues(Version.OLDER, this.reportProperties.getOlderVersionFileLocation(), startingCellOlderVersion);

        GoogleDriveHelper.moveFile(this.driveService, this.spreadSheetNewId, this.folderNewId);

        return GoogleDriveHelper.setPublishFile(this.driveService, this.spreadSheetNewId);
    }

    protected void getGoogleDriveFiles() {
        if (this.reportProperties.getNewVersionFileLocation() == FileLocation.DRIVE) {
            this.reportProperties.setNewVersionFileLocation(FileLocation.LOCAL);
            this.reportProperties.setNewVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, InputFileType.BUILDTIME.getFileType(), this.reportProperties.getNewVersionBuildtimePath(), Version.NEW, this.reportProperties.getNewVersionFileExtension()));
            this.reportProperties.setNewVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, InputFileType.RUNTIME.getFileType(), this.reportProperties.getNewVersionRuntimePath(), Version.NEW, this.reportProperties.getNewVersionFileExtension()));
        }

        if (this.reportProperties.getPreviousVersionFileLocation() == FileLocation.DRIVE) {
            this.reportProperties.setPreviousVersionFileLocation(FileLocation.LOCAL);
            this.reportProperties.setPreviousVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, InputFileType.BUILDTIME.getFileType(), this.reportProperties.getPreviousVersionBuildtimePath(), Version.PREVIOUS, this.reportProperties.getPreviousVersionFileExtension()));
            this.reportProperties.setPreviousVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, InputFileType.RUNTIME.getFileType(), this.reportProperties.getPreviousVersionRuntimePath(), Version.PREVIOUS, this.reportProperties.getPreviousVersionFileExtension()));
        }

        if (this.reportProperties.getOlderVersionFileLocation() == FileLocation.DRIVE) {
            this.reportProperties.setOlderVersionFileLocation(FileLocation.LOCAL);
            this.reportProperties.setOlderVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, InputFileType.BUILDTIME.getFileType(), this.reportProperties.getOlderVersionBuildtimePath(), Version.OLDER, this.reportProperties.getOlderVersionFileExtension()));
            this.reportProperties.setOlderVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, InputFileType.RUNTIME.getFileType(), this.reportProperties.getOlderVersionRuntimePath(), Version.OLDER, this.reportProperties.getOlderVersionFileExtension()));
        }
    }

    /*I intend to write upload methods to store datasources on google drive*/
    private String getDataFormatNameToUpload(String type, String version) {
        String newName;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer year = cal.get(Calendar.YEAR);
        Integer month = cal.get(Calendar.MONTH);
        Integer day = cal.get(Calendar.DAY_OF_MONTH);

        newName = year.toString() + month.toString() + day.toString() + type + version;

        return newName;
    }

    protected String createDoc() {
        String result = "";
        try {
            this.docNewId = GoogleDriveHelper.copyFile(this.driveService, this.reportProperties.getFilesTitle(), this.reportProperties.getTemplateDocID());
            DroolsGoogleDriveDocument droolsFileDoc = new DroolsGoogleDriveDocument(this.docNewId, this.spreadSheetNewId, this.reportProperties, this.docsService, this.sheetsService);

            List<Request> requests = droolsFileDoc.getReplaceAllBody();
            droolsFileDoc.requestsExecute(requests);

            droolsFileDoc.updateChartWithLink();

            result = GoogleDriveHelper.moveFile(this.driveService, this.docNewId, this.folderNewId);
        } catch (IOException e) {
            LOGGER.debug("File cannot be read.", e);
        }

        return result;
    }
}
