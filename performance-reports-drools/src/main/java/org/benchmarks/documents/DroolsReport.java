package org.benchmarks.documents;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.definitions.ReportType;
import org.benchmarks.definitions.StaticVersion;
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
    private static String spreadsheetBuildtimeSheetName = "Charts - buildtime Oracle JDK8";
    private static String spreadsheetBuildtimeRange = "A3:F24";
    private static String spreadsheetRuntimeSheetName = "Charts - runtime Oracle JDK8";
    private static String spreadsheetRuntimeRange = "A3:F25";
    private DroolsReportProperties droolsReportProperties;
    private String folderNewId;
    private String spreadSheetNewId;
    private Drive driveService;
    private Docs docsService;
    private Sheets sheetsService;

    /*for tests purposes*/
    public DroolsReport(DroolsReportProperties droolsReportProperties) {
        try {
            this.droolsReportProperties = droolsReportProperties;

            GoogleDriveService googleDriveService = new GoogleDriveService(this.droolsReportProperties.getGoogleAppApiKeyFile());
            this.driveService = googleDriveService.getDrive();
            this.docsService = googleDriveService.getDocs();
            this.sheetsService = googleDriveService.getSheets();
        } catch (Exception e) {
            LOGGER.debug(ExceptionsConstants.FAILED_GENERATE_REPORT, e);
        }
    }

    public DroolsReport() {
    }

    public Boolean generateReport() {
        Path localTargetPath = Paths.get("target");
        String localDir = localTargetPath.getFileName().toString();

        Boolean result = null;
        try {
            this.folderNewId = GoogleDriveHelper.createNewDir(this.driveService, this.droolsReportProperties.getFolderTitle(), this.droolsReportProperties.getResultParentFolderID());
            getGoogleDriveFiles(this.droolsReportProperties, this.driveService, localDir);
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
            this.spreadSheetNewId = GoogleDriveHelper.copyFile(this.driveService, this.droolsReportProperties.getFilesTitle(), this.droolsReportProperties.getTemplateSheetID());

            DroolsGoogleDriveSpreadSheet droolsFileSpreadSheet = new DroolsGoogleDriveSpreadSheet();
            droolsFileSpreadSheet.updateSpreadSheetInfo(this.droolsReportProperties, this.sheetsService, this.spreadSheetNewId);
            droolsFileSpreadSheet.updateSpreadSheetValues(this.droolsReportProperties.getCurrentVersionSourceFileExtension(), StaticVersion.CURRENT, this.droolsReportProperties.getCurrentVersionSourceFileLocation(), startingCellCurrentVersion, this.droolsReportProperties, this.sheetsService, this.spreadSheetNewId);
            droolsFileSpreadSheet.updateSpreadSheetValues(this.droolsReportProperties.getPreviousVersionSourceFileExtension(), StaticVersion.PREVIOUS, this.droolsReportProperties.getPreviousVersionSourceFileLocation(), startingCellPreviousVersion, this.droolsReportProperties, this.sheetsService, this.spreadSheetNewId);
            droolsFileSpreadSheet.updateSpreadSheetValues(this.droolsReportProperties.getOlderVersionSourceFileExtension(), StaticVersion.OLDER, this.droolsReportProperties.getOlderVersionSourceFileLocation(), startingCellOlderVersion, this.droolsReportProperties, this.sheetsService, this.spreadSheetNewId);

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
            docNewId = GoogleDriveHelper.copyFile(this.driveService, this.droolsReportProperties.getFilesTitle(), this.droolsReportProperties.getTemplateDocID());
            DroolsGoogleDriveDocument droolsFileDoc = new DroolsGoogleDriveDocument();

            List<Request> requestsBody = droolsFileDoc.getReplaceAllBody(this.droolsReportProperties);
            droolsFileDoc.requestsExecute(requestsBody, docNewId, this.docsService);

            droolsFileDoc.updateChartsWithLinks(docNewId, this.docsService, this.sheetsService, this.spreadSheetNewId);

            ValueRange buildTimeValueRange = droolsFileDoc.getValueRangeFromSheet(sheetsService, this.spreadSheetNewId, spreadsheetBuildtimeSheetName, spreadsheetBuildtimeRange);
            ValueRange runTimeValueRange = droolsFileDoc.getValueRangeFromSheet(sheetsService, this.spreadSheetNewId, spreadsheetRuntimeSheetName, spreadsheetRuntimeRange);

            List<Request> requestsTables = droolsFileDoc.getReplaceAllTables(buildTimeValueRange, runTimeValueRange);
            droolsFileDoc.requestsExecute(requestsTables, docNewId, this.docsService);

            List<Request> requestsFormatTables = droolsFileDoc.getFormatAllTables(this.docsService, docNewId, buildTimeValueRange, runTimeValueRange);
            droolsFileDoc.requestsExecute(requestsFormatTables, docNewId, this.docsService);

            result = GoogleDriveHelper.moveFile(this.driveService, docNewId, this.folderNewId);
        } catch (IOException e) {
            LOGGER.debug(ExceptionsConstants.FILE_CANNOT_BE_READ, e);
        }

        return result;
    }

    public void getGoogleDriveFiles(DroolsReportProperties droolsReportProperties, Drive driveService, String localDir) {
        try {
            if (droolsReportProperties.getCurrentVersionSourceFileLocation() == SourceFileLocation.DRIVE) {
                droolsReportProperties.setCurrentVersionSourceFileLocation(SourceFileLocation.LOCAL);
                droolsReportProperties.setCurrentVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, ReportType.BUILDTIME.getFileType(), droolsReportProperties.getCurrentVersionBuildtimePath(), localDir, StaticVersion.CURRENT, droolsReportProperties.getCurrentVersionSourceFileExtension()));
                droolsReportProperties.setCurrentVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, ReportType.RUNTIME.getFileType(), droolsReportProperties.getCurrentVersionRuntimePath(), localDir, StaticVersion.CURRENT, droolsReportProperties.getCurrentVersionSourceFileExtension()));
            }

            if (droolsReportProperties.getPreviousVersionSourceFileLocation() == SourceFileLocation.DRIVE) {
                droolsReportProperties.setPreviousVersionSourceFileLocation(SourceFileLocation.LOCAL);
                droolsReportProperties.setPreviousVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, ReportType.BUILDTIME.getFileType(), droolsReportProperties.getPreviousVersionBuildtimePath(), localDir, StaticVersion.PREVIOUS, droolsReportProperties.getPreviousVersionSourceFileExtension()));
                droolsReportProperties.setPreviousVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, ReportType.RUNTIME.getFileType(), droolsReportProperties.getPreviousVersionRuntimePath(), localDir, StaticVersion.PREVIOUS, droolsReportProperties.getPreviousVersionSourceFileExtension()));
            }

            if (droolsReportProperties.getOlderVersionSourceFileLocation() == SourceFileLocation.DRIVE) {
                droolsReportProperties.setOlderVersionSourceFileLocation(SourceFileLocation.LOCAL);
                droolsReportProperties.setOlderVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, ReportType.BUILDTIME.getFileType(), droolsReportProperties.getOlderVersionBuildtimePath(), localDir, StaticVersion.OLDER, droolsReportProperties.getOlderVersionSourceFileExtension()));
                droolsReportProperties.setOlderVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, ReportType.RUNTIME.getFileType(), droolsReportProperties.getOlderVersionRuntimePath(), localDir, StaticVersion.OLDER, droolsReportProperties.getOlderVersionSourceFileExtension()));
            }
        } catch (IOException e) {
            LOGGER.debug(ExceptionsConstants.FAILED_PREPARING_GOOGLE_DRIVE_FILES, e);
        }
    }
}
