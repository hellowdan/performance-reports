package org.benchmarks.drools.documents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import org.benchmarks.commons.api.helper.GoogleDriveHelper;
import org.benchmarks.commons.api.helper.GoogleDriveService;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DroolsGoogleDriveSpreadSheetTest {

    private static String serviceAccountJsonPath = "/service_account.json";

    private Drive driveService;
    private Sheets sheetsService;
    private String sheetTemplateId = "1V3ITyMR7aiHAvSbCy5aOv6U3xtu5KGWAVaLFiYqOoIU";
    private DroolsGoogleDriveSpreadSheet droolsGoogleDriveSpreadSheet;
    private DroolsPropertiesLoader droolsPropertiesLoader;

    @Before
    public void setUp() throws Exception {
        GoogleDriveService googleDriveService = new GoogleDriveService("/service_account.json");
        this.driveService = googleDriveService.getDrive();
        this.sheetsService = googleDriveService.getSheets();
        this.droolsPropertiesLoader = new DroolsPropertiesLoader("/drools-reports-code-changes-csv.properties");

        this.droolsGoogleDriveSpreadSheet = new DroolsGoogleDriveSpreadSheet();
    }

    @Test
    public void updateSpreadSheetInfoTest() throws IOException {
        BatchUpdateSpreadsheetResponse response;

        String spreadSheetNewId = GoogleDriveHelper.copyFile(this.driveService, this.droolsPropertiesLoader.getFilesTitle(), this.sheetTemplateId);
        response = this.droolsGoogleDriveSpreadSheet.updateSpreadSheetInfo(this.droolsPropertiesLoader, this.sheetsService, spreadSheetNewId);

        assertThat(response.size(), is(2));
    }

    @Test
    public void updateSpreadSheetValuesTest() throws IOException {
        UpdateValuesResponse response;
        final String startingCellCurrentVersion = "K2";

        String spreadSheetNewId = GoogleDriveHelper.copyFile(this.driveService, this.droolsPropertiesLoader.getFilesTitle(), this.sheetTemplateId);
        response = this.droolsGoogleDriveSpreadSheet.updateSpreadSheetValues(this.droolsPropertiesLoader.getCurrentVersionJenkinsReportFileExtension(), JenkinsReportVersion.NEW, this.droolsPropertiesLoader.getCurrentVersionJenkinsReportLocation(), startingCellCurrentVersion, this.droolsPropertiesLoader, this.sheetsService, spreadSheetNewId);

        assertThat(response.getUpdatedRows(), is(45));
    }

    @Test
    public void getReplaceSpreadSheetBodyRequestTest() {
        List<Request> requests = new ArrayList<>();
        final String currentVersion = "{{current_version}}";
        final String previousVersion = "{{previous_version}}";
        final String olderVersion = "{{older_version}}";

        requests.add(this.droolsGoogleDriveSpreadSheet.getReplaceSpreadSheetBodyRequest(currentVersion, droolsPropertiesLoader.getCurrentVersion()));
        requests.add(this.droolsGoogleDriveSpreadSheet.getReplaceSpreadSheetBodyRequest(previousVersion, droolsPropertiesLoader.getPreviousVersion()));
        requests.add(this.droolsGoogleDriveSpreadSheet.getReplaceSpreadSheetBodyRequest(olderVersion, droolsPropertiesLoader.getOlderVersion()));

        assertThat(requests.size(), is(3));
    }
}