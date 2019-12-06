package org.benchmarks.drools.documents;

import java.io.IOException;
import java.util.List;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import org.benchmarks.commons.api.helper.GoogleDriveHelper;
import org.benchmarks.commons.api.helper.GoogleDriveService;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.definitions.JenkinsReportType;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    /*for tests purposes*/
    public DroolsReport(DroolsPropertiesLoader droolsPropertiesLoader) throws Exception {
        this.droolsPropertiesLoader = droolsPropertiesLoader;

        GoogleDriveService googleDriveService = new GoogleDriveService(this.droolsPropertiesLoader.getGoogleAppApiKeyFile());
        this.driveService = googleDriveService.getDrive();
        this.docsService = googleDriveService.getDocs();
        this.sheetsService = googleDriveService.getSheets();
    }

    public Boolean generateReport() throws Exception {
        Boolean result;
        try {
            this.folderNewId = GoogleDriveHelper.createNewDir(this.driveService, this.droolsPropertiesLoader.getFolderTitle(), this.droolsPropertiesLoader.getResultParentFolderID());
            getGoogleDriveFiles(this.droolsPropertiesLoader, this.driveService);
            createSpreadSheet();
            createDoc();
            result = true;
        } catch (Exception e) {
            LOGGER.debug("File cannot be read.", e);
            throw new Exception("File cannot be read.", e);
        }

        return result;
    }

    protected Boolean createSpreadSheet() throws IOException {
        this.spreadSheetNewId = GoogleDriveHelper.copyFile(this.driveService, this.droolsPropertiesLoader.getFilesTitle(), this.droolsPropertiesLoader.getTemplateSheetID());

        DroolsGoogleDriveSpreadSheet droolsFileSpreadSheet = new DroolsGoogleDriveSpreadSheet();
        droolsFileSpreadSheet.updateSpreadSheetInfo(this.droolsPropertiesLoader, this.sheetsService, this.spreadSheetNewId);
        droolsFileSpreadSheet.updateSpreadSheetValues(this.droolsPropertiesLoader.getNewVersionJenkinsReportFileExtension(), JenkinsReportVersion.NEW, this.droolsPropertiesLoader.getNewVersionJenkinsReportLocation(), startingCellNewVersion, this.droolsPropertiesLoader, this.sheetsService, this.spreadSheetNewId);
        droolsFileSpreadSheet.updateSpreadSheetValues(this.droolsPropertiesLoader.getPreviousVersionJenkinsReportFileExtension(), JenkinsReportVersion.PREVIOUS, this.droolsPropertiesLoader.getPreviousVersionJenkinsReportLocation(), startingCellPreviousVersion, this.droolsPropertiesLoader, this.sheetsService, this.spreadSheetNewId);
        droolsFileSpreadSheet.updateSpreadSheetValues(this.droolsPropertiesLoader.getOlderVersionJenkinsReportFileExtension(), JenkinsReportVersion.OLDER, this.droolsPropertiesLoader.getOlderVersionJenkinsReportLocation(), startingCellOlderVersion, this.droolsPropertiesLoader, this.sheetsService, this.spreadSheetNewId);

        GoogleDriveHelper.moveFile(this.driveService, this.spreadSheetNewId, this.folderNewId);

        return GoogleDriveHelper.setPublishFile(this.driveService, this.spreadSheetNewId);
    }


    protected String createDoc() throws IOException, ParseException {
        String result = "";
        this.docNewId = GoogleDriveHelper.copyFile(this.driveService, this.droolsPropertiesLoader.getFilesTitle(), this.droolsPropertiesLoader.getTemplateDocID());
        DroolsGoogleDriveDocument droolsFileDoc = new DroolsGoogleDriveDocument();

        List<Request> requests = droolsFileDoc.getReplaceAllBody(this.droolsPropertiesLoader);
        droolsFileDoc.requestsExecute(requests, this.docNewId, this.docsService);

        droolsFileDoc.updateChartWithLink(this.docNewId, this.docsService, this.sheetsService, this.spreadSheetNewId);

        result = GoogleDriveHelper.moveFile(this.driveService, this.docNewId, this.folderNewId);

        return result;
    }

    public void getGoogleDriveFiles(DroolsPropertiesLoader droolsPropertiesLoader, Drive driveService) throws IOException {
        try {
            if (droolsPropertiesLoader.getNewVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
                droolsPropertiesLoader.setNewVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
                droolsPropertiesLoader.setNewVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.BUILDTIME.getFileType(), droolsPropertiesLoader.getNewVersionBuildtimePath(), JenkinsReportVersion.NEW, droolsPropertiesLoader.getNewVersionJenkinsReportFileExtension()));
                droolsPropertiesLoader.setNewVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.RUNTIME.getFileType(), droolsPropertiesLoader.getNewVersionRuntimePath(), JenkinsReportVersion.NEW, droolsPropertiesLoader.getNewVersionJenkinsReportFileExtension()));
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
            LOGGER.debug("Failed preparing file from Google Drive.", e);
            throw new IOException("Failed preparing file from Google Drive.", e);
        }
    }
}
