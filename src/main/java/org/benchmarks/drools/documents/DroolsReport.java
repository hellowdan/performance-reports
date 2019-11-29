package org.benchmarks.drools.documents;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.benchmarks.commons.api.helper.GoogleDriveHelper;
import org.benchmarks.commons.api.helper.GoogleDriveService;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.definitions.JenkinsReportType;

public class DroolsReport {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsReport.class);

    private static String startingCellNewVersion = "K2";
    private static String startingCellPreviousVersion = "J2";
    private static String startingCellOlderVersion = "I2";
    private DroolsPropertiesLoader droolsPropertiesLoader;
    private String folderNewId;
    private String spreadSheetNewId;
    private String docNewId;
    private Drive driveService;
    private Docs docsService;
    private Sheets sheetsService;

    public DroolsReport() {
        try {
            this.droolsPropertiesLoader = DroolsPropertiesLoader.getInstance();

            GoogleDriveService googleDriveService = new GoogleDriveService();
            this.driveService = googleDriveService.getDrive();
            this.docsService = googleDriveService.getDocs();
            this.sheetsService = googleDriveService.getSheets();
        } catch (IOException e) {
            LOGGER.debug("File cannot be read.", e);
        }
    }

    /*for tests purposes*/
    public DroolsReport(DroolsPropertiesLoader droolsPropertiesLoader) {
        this.droolsPropertiesLoader = droolsPropertiesLoader;
    }

    public void generateReport() {
        try {
            this.folderNewId = GoogleDriveHelper.createNewDir(this.driveService, this.droolsPropertiesLoader.getFolderTitle(), this.droolsPropertiesLoader.getResultParentFolderID());
            getGoogleDriveFiles();
            createSpreadSheet();
            createDoc();
        } catch (Exception e) {
            LOGGER.debug("File cannot be read.", e);
        }
    }

    protected Boolean createSpreadSheet() {
        this.spreadSheetNewId = GoogleDriveHelper.copyFile(this.driveService, this.droolsPropertiesLoader.getFilesTitle(), this.droolsPropertiesLoader.getTemplateSheetID());
        DroolsGoogleDriveSpreadSheet droolsFileSpreadSheet = new DroolsGoogleDriveSpreadSheet(this.spreadSheetNewId, this.droolsPropertiesLoader, this.sheetsService);
        droolsFileSpreadSheet.updateSpreadSheetInfo();
        droolsFileSpreadSheet.updateSpreadSheetValues(JenkinsReportVersion.NEW, this.droolsPropertiesLoader.getNewVersionJenkinsReportLocation(), startingCellNewVersion);
        droolsFileSpreadSheet.updateSpreadSheetValues(JenkinsReportVersion.PREVIOUS, this.droolsPropertiesLoader.getPreviousVersionJenkinsReportLocation(), startingCellPreviousVersion);
        droolsFileSpreadSheet.updateSpreadSheetValues(JenkinsReportVersion.OLDER, this.droolsPropertiesLoader.getOlderVersionJenkinsReportLocation(), startingCellOlderVersion);

        GoogleDriveHelper.moveFile(this.driveService, this.spreadSheetNewId, this.folderNewId);

        return GoogleDriveHelper.setPublishFile(this.driveService, this.spreadSheetNewId);
    }

    protected void getGoogleDriveFiles() {
        if (this.droolsPropertiesLoader.getNewVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
            this.droolsPropertiesLoader.setNewVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
            this.droolsPropertiesLoader.setNewVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.BUILDTIME.getFileType(), this.droolsPropertiesLoader.getNewVersionBuildtimePath(), JenkinsReportVersion.NEW, this.droolsPropertiesLoader.getNewVersionJenkinsReportFileExtension()));
            this.droolsPropertiesLoader.setNewVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.RUNTIME.getFileType(), this.droolsPropertiesLoader.getNewVersionRuntimePath(), JenkinsReportVersion.NEW, this.droolsPropertiesLoader.getNewVersionJenkinsReportFileExtension()));
        }

        if (this.droolsPropertiesLoader.getPreviousVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
            this.droolsPropertiesLoader.setPreviousVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
            this.droolsPropertiesLoader.setPreviousVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.BUILDTIME.getFileType(), this.droolsPropertiesLoader.getPreviousVersionBuildtimePath(), JenkinsReportVersion.PREVIOUS, this.droolsPropertiesLoader.getPreviousVersionJenkinsReportFileExtension()));
            this.droolsPropertiesLoader.setPreviousVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.RUNTIME.getFileType(), this.droolsPropertiesLoader.getPreviousVersionRuntimePath(), JenkinsReportVersion.PREVIOUS, this.droolsPropertiesLoader.getPreviousVersionJenkinsReportFileExtension()));
        }

        if (this.droolsPropertiesLoader.getOlderVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
            this.droolsPropertiesLoader.setOlderVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
            this.droolsPropertiesLoader.setOlderVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.BUILDTIME.getFileType(), this.droolsPropertiesLoader.getOlderVersionBuildtimePath(), JenkinsReportVersion.OLDER, this.droolsPropertiesLoader.getOlderVersionJenkinsReportFileExtension()));
            this.droolsPropertiesLoader.setOlderVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.RUNTIME.getFileType(), this.droolsPropertiesLoader.getOlderVersionRuntimePath(), JenkinsReportVersion.OLDER, this.droolsPropertiesLoader.getOlderVersionJenkinsReportFileExtension()));
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
            this.docNewId = GoogleDriveHelper.copyFile(this.driveService, this.droolsPropertiesLoader.getFilesTitle(), this.droolsPropertiesLoader.getTemplateDocID());
            DroolsGoogleDriveDocument droolsFileDoc = new DroolsGoogleDriveDocument(this.docNewId, this.spreadSheetNewId, this.droolsPropertiesLoader, this.docsService, this.sheetsService);

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
