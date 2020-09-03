package org.benchmarks.documents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.ReportType;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.helper.GoogleDriveHelper;
import org.benchmarks.helper.GoogleDriveService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GoogleDriveSpreadSheetTest {

    private static String serviceAccountJsonPath = "/service_account.json";

    private Drive driveService;
    private Sheets sheetsService;
    private String sheetTemplateId = "1V3ITyMR7aiHAvSbCy5aOv6U3xtu5KGWAVaLFiYqOoIU";
    private DroolsGoogleDriveSpreadSheet droolsGoogleDriveSpreadSheet;
    private DroolsReportProperties droolsReportProperties;

    @Before
    public void setUp() throws Exception {
        GoogleDriveService googleDriveService = new GoogleDriveService("/service_account.json");
        this.driveService = googleDriveService.getDrive();
        this.sheetsService = googleDriveService.getSheets();
        this.droolsReportProperties = new DroolsReportProperties("/droolsUpdateSheet.properties");

        this.droolsGoogleDriveSpreadSheet = new DroolsGoogleDriveSpreadSheet();
    }

    @Test
    public void updateSpreadSheetInfoTest() throws IOException {
        BatchUpdateSpreadsheetResponse response;

        String spreadSheetNewId = GoogleDriveHelper.copyFile(this.driveService, this.droolsReportProperties.getFilesTitle(), this.sheetTemplateId);
        response = this.droolsGoogleDriveSpreadSheet.updateSpreadSheetInfo(this.droolsReportProperties, this.sheetsService, spreadSheetNewId);

        assertThat(response.size(), is(2));
    }

//    @Test
//    public void updateSpreadSheetValuesTest() throws IOException {
//        UpdateValuesResponse response;
//        final String startingCellCurrentVersion = "K2";
//
//        String spreadSheetNewId = GoogleDriveHelper.copyFile(this.driveService, this.droolsReportProperties.getFilesTitle(), this.sheetTemplateId);
//        response = this.droolsGoogleDriveSpreadSheet.updateSpreadSheetValues(this.droolsReportProperties.getCurrentVersionSourceFileExtension(), StaticVersion.CURRENT, this.droolsReportProperties.getCurrentVersionSourceFileLocation(), startingCellCurrentVersion, this.droolsReportProperties, this.sheetsService, spreadSheetNewId, ReportType.RUNTIME);
//
//        assertThat(response.getUpdatedRows(), is(45));
//    }

    @Test
    public void getReplaceSpreadSheetBodyRequestTest() {
        List<Request> requests = new ArrayList<>();
        final String currentVersion = "{{current_version}}";
        final String previousVersion = "{{previous_version}}";
        final String olderVersion = "{{older_version}}";

        requests.add(this.droolsGoogleDriveSpreadSheet.getReplaceSpreadSheetBodyRequest(currentVersion, droolsReportProperties.getCurrentVersion()));
        requests.add(this.droolsGoogleDriveSpreadSheet.getReplaceSpreadSheetBodyRequest(previousVersion, droolsReportProperties.getPreviousVersion()));
        requests.add(this.droolsGoogleDriveSpreadSheet.getReplaceSpreadSheetBodyRequest(olderVersion, droolsReportProperties.getOlderVersion()));

        assertThat(requests.size(), is(3));
    }
}