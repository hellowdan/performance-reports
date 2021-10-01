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
import org.benchmarks.definitions.DroolsDocumentsToGenerate;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.DroolsSpreadsheetsToGenerate;
import org.benchmarks.definitions.ProcessScope;
import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.exceptions.ExceptionsConstants;
import org.benchmarks.helper.GoogleDriveHelper;
import org.benchmarks.helper.GoogleDriveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolsReport {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsReport.class);

    private static final String STARTING_CELL_NEXT_VERSION = "L2";
    private static final String STARTING_CELL_CURRENT_VERSION = "K2";
    private static final String STARTING_CELL_PREVIOUS_VERSION = "J2";
    private static final String STARTING_CELL_OLDER_VERSION = "I2";
    private static final String SPREADSHEET_BUILDTIME_SHEET_NAME = "Charts - buildtime openjdk11";
    private static final String SPREADSHEET_BUILDTIME_RANGE = "A3:F24";
    private static final String SPREADSHEET_RUNTIME_SHEET_NAME = "Charts - runtime openjdk11";
    private static final String SPREADSHEET_RUNTIME_RANGE = "A3:F25";

    private static final String SPREADSHEET_DMN_BUILDTIME_SHEET_NAME = "Charts - DMN build time";
    private static final String SPREADSHEET_DMN_BUILDTIME_RANGE = "A3:F8";
    private static final String SPREADSHEET_DMN_RUNTIME_SHEET_NAME = "Charts - DMN runtime";
    private static final String SPREADSHEET_DMN_RUNTIME_RANGE = "A3:F13";
    private static final String SPREADSHEET_DMN_FEEL_SHEET_NAME = "Charts - DMN FEEL expressions";
    private static final String SPREADSHEET_DMN_FEEL_RANGE = "A3:F533";

    private DroolsReportProperties droolsReportProperties;
    private String folderNewId;
    private Drive driveService;
    private Docs docsService;
    private Sheets sheetsService;
    private String spreadsheetDmnNewID;
    private String spreadsheetEventProcessingNewID;
    private String spreadsheetEventProcessingMultithreadedNewID;
    private String spreadsheetOopathNewID;
    private String spreadsheetOperatorsNewID;
    private String spreadsheetSessionNewID;
    private String spreadsheetThroughputNewID;
    private String spreadsheetBuildtimeNewID;
    private String spreadsheetRuntimeNewID;
    private String spreadsheetRuntimeMultithreadedNewID;

    private ProcessScope processScope;

    /*for tests purposes*/
    public DroolsReport(DroolsReportProperties droolsReportProperties, ProcessScope processScope) {
        try {
            this.droolsReportProperties = droolsReportProperties;
            this.processScope = processScope;

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

            createSpreadSheets();

            if (this.processScope != ProcessScope.SPREADSHEETS_ONLY) {
                if(this.droolsReportProperties.getDroolsDocumentsToGenerate().contains(DroolsDocumentsToGenerate.BRE)) {
                    createBreDoc();
                }
                if(this.droolsReportProperties.getDroolsDocumentsToGenerate().contains(DroolsDocumentsToGenerate.DMN)){
                    createDmnDoc();
                }
            }

            result = true;
        } catch (IOException e) {
            LOGGER.debug(ExceptionsConstants.FILE_CANNOT_BE_PARSED, e);
        }

        return result;
    }

    protected Boolean createSpreadSheets() {
        Boolean result = false;

        for (DroolsSpreadsheetsToGenerate r : this.droolsReportProperties.getDroolsSpreadsheetsToGenerate()) {
            String newId = createSpreadSheet(this.droolsReportProperties.getTemplateIdBySpreadsheetToGenerate(r), r);
            setIdBySpreadsheetsToGenerate(r, newId);
            result = setPublish(newId);
        }

        return result;
    }

    private void setIdBySpreadsheetsToGenerate(DroolsSpreadsheetsToGenerate droolsSpreadsheetsToGenerate, String newID) {
        switch (droolsSpreadsheetsToGenerate) {
            case DMN: {
                this.spreadsheetDmnNewID = newID;
                break;
            }
            case EVENT_PROCESSING: {
                this.spreadsheetEventProcessingNewID = newID;
                break;
            }
            case EVENT_PROCESSING_MULTITHREADED: {
                this.spreadsheetEventProcessingMultithreadedNewID = newID;
                break;
            }
            case OOPATH: {
                this.spreadsheetOopathNewID = newID;
                break;
            }
            case OPERATORS: {
                this.spreadsheetOperatorsNewID = newID;
                break;
            }
            case SESSION: {
                this.spreadsheetSessionNewID = newID;
                break;
            }
            case THROUGHPUT: {
                this.spreadsheetThroughputNewID = newID;
                break;
            }
            case BUILDTIME: {
                this.spreadsheetBuildtimeNewID = newID;
                break;
            }
            case RUNTIME: {
                this.spreadsheetRuntimeNewID = newID;
                break;
            }
            case RUNTIME_MULTITHREADED: {
                this.spreadsheetRuntimeMultithreadedNewID = newID;
                break;
            }
        }
    }

    private Boolean setPublish(String spreadSheetNewID) {
        Boolean result = false;

        try {
            result = GoogleDriveHelper.setPublishFile(this.driveService, spreadSheetNewID);
        } catch (IOException e) {
            LOGGER.debug(ExceptionsConstants.FILE_CANNOT_BE_READ, e);
        }
        return result;
    }

    private String createSpreadSheet(String templateID, DroolsSpreadsheetsToGenerate droolsSpreadsheetsToGenerate) {
        String newID = "";

        try {
            String newTitle = this.droolsReportProperties.getFilesTitle() + " - " + droolsSpreadsheetsToGenerate.getFileTitle();
            newID = GoogleDriveHelper.copyFile(this.driveService, newTitle, templateID);

            DroolsGoogleDriveSpreadSheet droolsFileSpreadSheet = new DroolsGoogleDriveSpreadSheet();
            droolsFileSpreadSheet.updateSpreadSheetInfo(this.droolsReportProperties, this.sheetsService, newID);

            droolsFileSpreadSheet.updateSpreadSheetValues(this.droolsReportProperties.getNextVersionSourceFileExtension(), StaticVersion.NEXT,
                                                          this.droolsReportProperties.getNextVersionSourceFileLocation(), STARTING_CELL_NEXT_VERSION,
                                                          this.droolsReportProperties, this.sheetsService, newID, droolsSpreadsheetsToGenerate);
            droolsFileSpreadSheet.updateSpreadSheetValues(this.droolsReportProperties.getCurrentVersionSourceFileExtension(), StaticVersion.CURRENT,
                                                          this.droolsReportProperties.getCurrentVersionSourceFileLocation(), STARTING_CELL_CURRENT_VERSION,
                                                          this.droolsReportProperties, this.sheetsService, newID, droolsSpreadsheetsToGenerate);
            droolsFileSpreadSheet.updateSpreadSheetValues(this.droolsReportProperties.getPreviousVersionSourceFileExtension(), StaticVersion.PREVIOUS,
                                                          this.droolsReportProperties.getPreviousVersionSourceFileLocation(), STARTING_CELL_PREVIOUS_VERSION,
                                                          this.droolsReportProperties, this.sheetsService, newID, droolsSpreadsheetsToGenerate);
            droolsFileSpreadSheet.updateSpreadSheetValues(this.droolsReportProperties.getOlderVersionSourceFileExtension(), StaticVersion.OLDER,
                                                          this.droolsReportProperties.getOlderVersionSourceFileLocation(), STARTING_CELL_OLDER_VERSION,
                                                          this.droolsReportProperties, this.sheetsService, newID, droolsSpreadsheetsToGenerate);

            GoogleDriveHelper.moveFile(this.driveService, newID, this.folderNewId);
        } catch (IOException e) {
            LOGGER.debug(ExceptionsConstants.FILE_CANNOT_BE_READ, e);
        }

        return newID;
    }

    protected String createDmnDoc(){
        String result = null;
        String dmnDocNewId;

        try {
            String newTitle = this.droolsReportProperties.getFilesTitle() + " - " + DroolsDocumentsToGenerate.DMN;
            dmnDocNewId = GoogleDriveHelper.copyFile(this.driveService, newTitle, this.droolsReportProperties.getTemplateDmnDocID());
            DMNGoogleDriveDocument dmnFileDoc = new DMNGoogleDriveDocument();

            List<Request> requestsBody = dmnFileDoc.getReplaceAllBody(this.droolsReportProperties);
            dmnFileDoc.processRequests(requestsBody, dmnDocNewId, this.docsService);

            dmnFileDoc.updateChartsWithLinks(dmnDocNewId, this.docsService, this.sheetsService, this.spreadsheetDmnNewID);

            ValueRange dmnBuildtimeValueRange = dmnFileDoc.getValueRangeFromSheet(sheetsService, this.spreadsheetDmnNewID, SPREADSHEET_DMN_BUILDTIME_SHEET_NAME, SPREADSHEET_DMN_BUILDTIME_RANGE);
            ValueRange dmnRuntimeValueRange = dmnFileDoc.getValueRangeFromSheet(sheetsService, this.spreadsheetDmnNewID, SPREADSHEET_DMN_RUNTIME_SHEET_NAME, SPREADSHEET_DMN_RUNTIME_RANGE);
            ValueRange dmnFEELValueRange = dmnFileDoc.getValueRangeFromSheet(sheetsService, this.spreadsheetDmnNewID, SPREADSHEET_DMN_FEEL_SHEET_NAME, SPREADSHEET_DMN_FEEL_RANGE);

            List<Request> requestsTables = dmnFileDoc.getReplaceAllTables(dmnBuildtimeValueRange, dmnRuntimeValueRange, dmnFEELValueRange);
            dmnFileDoc.processRequests(requestsTables, dmnDocNewId, this.docsService);

            List<Request> requestsFormatTables = dmnFileDoc.getFormatAllTables(this.docsService, dmnDocNewId, dmnBuildtimeValueRange, dmnRuntimeValueRange, dmnFEELValueRange);
            dmnFileDoc.processRequests(requestsFormatTables, dmnDocNewId, this.docsService);

            result = GoogleDriveHelper.moveFile(this.driveService, dmnDocNewId, this.folderNewId);
        } catch (IOException e) {
            LOGGER.debug(ExceptionsConstants.FILE_CANNOT_BE_READ, e);
        }

        return result;
    }

    protected String createBreDoc() {
        String result = null;
        String docNewId;

        try {
            String newTitle = this.droolsReportProperties.getFilesTitle() + " - " + DroolsDocumentsToGenerate.BRE;
            docNewId = GoogleDriveHelper.copyFile(this.driveService, newTitle, this.droolsReportProperties.getTemplateBreDocID());
            BREGoogleDriveDocument breFileDoc = new BREGoogleDriveDocument();

            List<Request> requestsBody = breFileDoc.getReplaceAllBody(this.droolsReportProperties);
            breFileDoc.processRequests(requestsBody, docNewId, this.docsService);

            breFileDoc.updateChartsWithLinks(docNewId, this.docsService, this.sheetsService, this.spreadsheetBuildtimeNewID);
            breFileDoc.updateChartsWithLinks(docNewId, this.docsService, this.sheetsService, this.spreadsheetRuntimeNewID);

            ValueRange buildTimeValueRange = breFileDoc.getValueRangeFromSheet(sheetsService, this.spreadsheetBuildtimeNewID, SPREADSHEET_BUILDTIME_SHEET_NAME, SPREADSHEET_BUILDTIME_RANGE);
            ValueRange runTimeValueRange = breFileDoc.getValueRangeFromSheet(sheetsService, this.spreadsheetRuntimeNewID, SPREADSHEET_RUNTIME_SHEET_NAME, SPREADSHEET_RUNTIME_RANGE);

            List<Request> requestsTables = breFileDoc.getReplaceAllTables(buildTimeValueRange, runTimeValueRange);
            breFileDoc.processRequests(requestsTables, docNewId, this.docsService);

            List<Request> requestsFormatTables = breFileDoc.getFormatAllTables(this.docsService, docNewId, buildTimeValueRange, runTimeValueRange);
            breFileDoc.processRequests(requestsFormatTables, docNewId, this.docsService);

            result = GoogleDriveHelper.moveFile(this.driveService, docNewId, this.folderNewId);
        } catch (IOException e) {
            LOGGER.debug(ExceptionsConstants.FILE_CANNOT_BE_READ, e);
        }

        return result;
    }

    public void getGoogleDriveFiles(DroolsReportProperties droolsReportProperties, Drive driveService, String localDir) {
        try {
            if (droolsReportProperties.getNextVersionSourceFileLocation() == SourceFileLocation.DRIVE) {
                droolsReportProperties.setNextVersionSourceFileLocation(SourceFileLocation.LOCAL);
                if (droolsReportProperties.getDroolsSpreadsheetsToGenerate().contains(DroolsSpreadsheetsToGenerate.BUILDTIME)) {
                    droolsReportProperties.setNextVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsSpreadsheetsToGenerate.BUILDTIME.getFileType(), droolsReportProperties.getNextVersionBuildtimePath(), localDir, StaticVersion.NEXT, droolsReportProperties.getNextVersionSourceFileExtension()));
                }
                if (droolsReportProperties.getDroolsSpreadsheetsToGenerate().contains(DroolsSpreadsheetsToGenerate.RUNTIME)) {
                    droolsReportProperties.setNextVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsSpreadsheetsToGenerate.RUNTIME.getFileType(), droolsReportProperties.getNextVersionRuntimePath(), localDir, StaticVersion.NEXT, droolsReportProperties.getNextVersionSourceFileExtension()));
                }
                if (droolsReportProperties.getDroolsSpreadsheetsToGenerate().contains(DroolsSpreadsheetsToGenerate.DMN)) {
                    droolsReportProperties.setNextVersionDMNPath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsSpreadsheetsToGenerate.DMN.getFileType(), droolsReportProperties.getNextVersionDMNPath(), localDir, StaticVersion.NEXT, droolsReportProperties.getNextVersionSourceFileExtension()));
                }
            }

            if (droolsReportProperties.getCurrentVersionSourceFileLocation() == SourceFileLocation.DRIVE) {
                droolsReportProperties.setCurrentVersionSourceFileLocation(SourceFileLocation.LOCAL);
                if (droolsReportProperties.getDroolsSpreadsheetsToGenerate().contains(DroolsSpreadsheetsToGenerate.BUILDTIME)) {
                    droolsReportProperties.setCurrentVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsSpreadsheetsToGenerate.BUILDTIME.getFileType(), droolsReportProperties.getCurrentVersionBuildtimePath(), localDir, StaticVersion.CURRENT, droolsReportProperties.getCurrentVersionSourceFileExtension()));
                }
                if (droolsReportProperties.getDroolsSpreadsheetsToGenerate().contains(DroolsSpreadsheetsToGenerate.RUNTIME)) {
                    droolsReportProperties.setCurrentVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsSpreadsheetsToGenerate.RUNTIME.getFileType(), droolsReportProperties.getCurrentVersionRuntimePath(), localDir, StaticVersion.CURRENT, droolsReportProperties.getCurrentVersionSourceFileExtension()));
                }
                if (droolsReportProperties.getDroolsSpreadsheetsToGenerate().contains(DroolsSpreadsheetsToGenerate.DMN)) {
                    droolsReportProperties.setCurrentVersionDMNPath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsSpreadsheetsToGenerate.DMN.getFileType(), droolsReportProperties.getCurrentVersionDMNPath(), localDir, StaticVersion.CURRENT, droolsReportProperties.getCurrentVersionSourceFileExtension()));
                }
            }

            if (droolsReportProperties.getPreviousVersionSourceFileLocation() == SourceFileLocation.DRIVE) {
                droolsReportProperties.setPreviousVersionSourceFileLocation(SourceFileLocation.LOCAL);
                if (droolsReportProperties.getDroolsSpreadsheetsToGenerate().contains(DroolsSpreadsheetsToGenerate.BUILDTIME)) {
                    droolsReportProperties.setPreviousVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsSpreadsheetsToGenerate.BUILDTIME.getFileType(), droolsReportProperties.getPreviousVersionBuildtimePath(), localDir, StaticVersion.PREVIOUS, droolsReportProperties.getPreviousVersionSourceFileExtension()));
                }
                if (droolsReportProperties.getDroolsSpreadsheetsToGenerate().contains(DroolsSpreadsheetsToGenerate.RUNTIME)) {
                    droolsReportProperties.setPreviousVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsSpreadsheetsToGenerate.RUNTIME.getFileType(), droolsReportProperties.getPreviousVersionRuntimePath(), localDir, StaticVersion.PREVIOUS, droolsReportProperties.getPreviousVersionSourceFileExtension()));
                }
                if (droolsReportProperties.getDroolsSpreadsheetsToGenerate().contains(DroolsSpreadsheetsToGenerate.DMN)) {
                    droolsReportProperties.setPreviousVersionDMNPath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsSpreadsheetsToGenerate.DMN.getFileType(), droolsReportProperties.getPreviousVersionDMNPath(), localDir, StaticVersion.PREVIOUS, droolsReportProperties.getPreviousVersionSourceFileExtension()));
                }
            }

            if (droolsReportProperties.getOlderVersionSourceFileLocation() == SourceFileLocation.DRIVE) {
                droolsReportProperties.setOlderVersionSourceFileLocation(SourceFileLocation.LOCAL);
                if (droolsReportProperties.getDroolsSpreadsheetsToGenerate().contains(DroolsSpreadsheetsToGenerate.BUILDTIME)) {
                    droolsReportProperties.setOlderVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsSpreadsheetsToGenerate.BUILDTIME.getFileType(), droolsReportProperties.getOlderVersionBuildtimePath(), localDir, StaticVersion.OLDER, droolsReportProperties.getOlderVersionSourceFileExtension()));
                }
                if (droolsReportProperties.getDroolsSpreadsheetsToGenerate().contains(DroolsSpreadsheetsToGenerate.RUNTIME)) {
                    droolsReportProperties.setOlderVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsSpreadsheetsToGenerate.RUNTIME.getFileType(), droolsReportProperties.getOlderVersionRuntimePath(), localDir, StaticVersion.OLDER, droolsReportProperties.getOlderVersionSourceFileExtension()));
                }
                if (droolsReportProperties.getDroolsSpreadsheetsToGenerate().contains(DroolsSpreadsheetsToGenerate.DMN)) {
                    droolsReportProperties.setOlderVersionDMNPath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsSpreadsheetsToGenerate.DMN.getFileType(), droolsReportProperties.getOlderVersionDMNPath(), localDir, StaticVersion.OLDER, droolsReportProperties.getOlderVersionSourceFileExtension()));
                }
            }
        } catch (IOException e) {
            LOGGER.debug(ExceptionsConstants.FAILED_PREPARING_GOOGLE_DRIVE_FILES, e);
        }
    }
}
