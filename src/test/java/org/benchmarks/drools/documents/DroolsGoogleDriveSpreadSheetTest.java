package org.benchmarks.drools.documents;

import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import org.benchmarks.commons.api.helper.GoogleDriveService;
import org.benchmarks.commons.util.PropertiesLoader;
import org.junit.Before;
import org.junit.Test;

public class DroolsGoogleDriveSpreadSheetTest {

    private static String serviceAccountJsonPath = "/service_account.json";

    private Drive driveService;
    private Sheets sheetService;
    private DroolsGoogleDriveSpreadSheet droolsGoogleDriveSpreadSheet;

    @Before
    public void setUp() throws Exception {
//        GoogleDriveService googleDriveService = new GoogleDriveService(serviceAccountJsonPath);
//        driveService = googleDriveService.getDrive();
//        sheetService = googleDriveService.getSheets();
    }

    @Test
    public void getUpdateInfoRequestsBodyTest() {
    }

    @Test
    public void getUpdateDataRequestsBodyTest() {
    }
}