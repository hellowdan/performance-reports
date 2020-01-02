package org.benchmarks.documents;

import java.io.IOException;
import java.util.List;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import org.benchmarks.definitions.DroolsPropertiesLoader;
import org.benchmarks.definitions.JenkinsReportLocation;
import org.benchmarks.definitions.JenkinsReportType;
import org.benchmarks.definitions.JenkinsReportVersion;
import org.benchmarks.exceptions.ExceptionsConstants;
import org.benchmarks.helper.GoogleDriveHelper;
import org.benchmarks.helper.GoogleDriveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolsReport {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsReport.class);

    private static String startingCellCurrentVersion = "K2";
    private static String startingCellPreviousVersion = "J2";
    private static String startingCellOlderVersion = "I2";
    private DroolsPropertiesLoader droolsPropertiesLoader;
    private String folderNewId;
    private String spreadSheetNewId;
    private Drive driveService;
    private Docs docsService;
    private Sheets sheetsService;

    /*for tests purposes*/
    public DroolsReport(DroolsPropertiesLoader droolsPropertiesLoader) {
        try {
            this.droolsPropertiesLoader = droolsPropertiesLoader;

            GoogleDriveService googleDriveService = new GoogleDriveService(this.droolsPropertiesLoader.getGoogleAppApiKeyFile());
            this.driveService = googleDriveService.getDrive();
            this.docsService = googleDriveService.getDocs();
            this.sheetsService = googleDriveService.getSheets();
        } catch (Exception e) {
            LOGGER.debug(ExceptionsConstants.FAILED_GENERATE_REPORT, e);
        }
    }

    public Boolean generateReport() {
        Boolean result = null;
        try {
            this.folderNewId = GoogleDriveHelper.createNewDir(this.driveService, this.droolsPropertiesLoader.getFolderTitle(), this.droolsPropertiesLoader.getResultParentFolderID());
            getGoogleDriveFiles(this.droolsPropertiesLoader, this.driveService);
            createSpreadSheet();
            createDoc();
            result = true;
        } catch (IOException e) {
            LOGGER.debug(ExceptionsConstants.FILE_CANNOT_BE_PARSED, e);
        }

        return result;
    }

    protected Boolean createSpreadSheet() {
        Boolean result = false;
        try {
            this.spreadSheetNewId = GoogleDriveHelper.copyFile(this.driveService, this.droolsPropertiesLoader.getFilesTitle(), this.droolsPropertiesLoader.getTemplateSheetID());

            DroolsGoogleDriveSpreadSheet droolsFileSpreadSheet = new DroolsGoogleDriveSpreadSheet();
            droolsFileSpreadSheet.updateSpreadSheetInfo(this.droolsPropertiesLoader, this.sheetsService, this.spreadSheetNewId);
            droolsFileSpreadSheet.updateSpreadSheetValues(this.droolsPropertiesLoader.getCurrentVersionJenkinsReportFileExtension(), JenkinsReportVersion.NEW, this.droolsPropertiesLoader.getCurrentVersionJenkinsReportLocation(), startingCellCurrentVersion, this.droolsPropertiesLoader, this.sheetsService, this.spreadSheetNewId);
            droolsFileSpreadSheet.updateSpreadSheetValues(this.droolsPropertiesLoader.getPreviousVersionJenkinsReportFileExtension(), JenkinsReportVersion.PREVIOUS, this.droolsPropertiesLoader.getPreviousVersionJenkinsReportLocation(), startingCellPreviousVersion, this.droolsPropertiesLoader, this.sheetsService, this.spreadSheetNewId);
            droolsFileSpreadSheet.updateSpreadSheetValues(this.droolsPropertiesLoader.getOlderVersionJenkinsReportFileExtension(), JenkinsReportVersion.OLDER, this.droolsPropertiesLoader.getOlderVersionJenkinsReportLocation(), startingCellOlderVersion, this.droolsPropertiesLoader, this.sheetsService, this.spreadSheetNewId);

            GoogleDriveHelper.moveFile(this.driveService, this.spreadSheetNewId, this.folderNewId);

            return GoogleDriveHelper.setPublishFile(this.driveService, this.spreadSheetNewId);
        } catch (IOException e) {
            LOGGER.debug(ExceptionsConstants.FILE_CANNOT_BE_READ, e);
        }

        return result;
    }

    protected String createDoc() {
        String result = null;
        String docNewId;

        try {
            docNewId = GoogleDriveHelper.copyFile(this.driveService, this.droolsPropertiesLoader.getFilesTitle(), this.droolsPropertiesLoader.getTemplateDocID());
            DroolsGoogleDriveDocument droolsFileDoc = new DroolsGoogleDriveDocument();

            List<Request> requests = droolsFileDoc.getReplaceAllBody(this.droolsPropertiesLoader);
            droolsFileDoc.requestsExecute(requests, docNewId, this.docsService);
            droolsFileDoc.updateChartWithLink(docNewId, this.docsService, this.sheetsService, this.spreadSheetNewId);
            result = GoogleDriveHelper.moveFile(this.driveService, docNewId, this.folderNewId);
        } catch (IOException e) {
            LOGGER.debug(ExceptionsConstants.FILE_CANNOT_BE_READ, e);
        }

        return result;
    }

    public void getGoogleDriveFiles(DroolsPropertiesLoader droolsPropertiesLoader, Drive driveService) {
        try {
            if (droolsPropertiesLoader.getCurrentVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
                droolsPropertiesLoader.setCurrentVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
                droolsPropertiesLoader.setCurrentVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.BUILDTIME.getFileType(), droolsPropertiesLoader.getCurrentVersionBuildtimePath(), JenkinsReportVersion.NEW, droolsPropertiesLoader.getCurrentVersionJenkinsReportFileExtension()));
                droolsPropertiesLoader.setCurrentVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.RUNTIME.getFileType(), droolsPropertiesLoader.getCurrentVersionRuntimePath(), JenkinsReportVersion.NEW, droolsPropertiesLoader.getCurrentVersionJenkinsReportFileExtension()));
            }

            if (droolsPropertiesLoader.getPreviousVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
                droolsPropertiesLoader.setPreviousVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
                droolsPropertiesLoader.setPreviousVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.BUILDTIME.getFileType(), droolsPropertiesLoader.getPreviousVersionBuildtimePath(), JenkinsReportVersion.PREVIOUS, droolsPropertiesLoader.getPreviousVersionJenkinsReportFileExtension()));
                droolsPropertiesLoader.setPreviousVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.RUNTIME.getFileType(), droolsPropertiesLoader.getPreviousVersionRuntimePath(), JenkinsReportVersion.PREVIOUS, droolsPropertiesLoader.getPreviousVersionJenkinsReportFileExtension()));
            }

            if (droolsPropertiesLoader.getOlderVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
                droolsPropertiesLoader.setOlderVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
                droolsPropertiesLoader.setOlderVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.BUILDTIME.getFileType(), droolsPropertiesLoader.getOlderVersionBuildtimePath(), JenkinsReportVersion.OLDER, droolsPropertiesLoader.getOlderVersionJenkinsReportFileExtension()));
                droolsPropertiesLoader.setOlderVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.RUNTIME.getFileType(), droolsPropertiesLoader.getOlderVersionRuntimePath(), JenkinsReportVersion.OLDER, droolsPropertiesLoader.getOlderVersionJenkinsReportFileExtension()));
            }
        } catch (IOException e) {
            LOGGER.debug(ExceptionsConstants.FAILED_PREPARING_GOOGLE_DRIVE_FILES, e);
        }
    }
}
