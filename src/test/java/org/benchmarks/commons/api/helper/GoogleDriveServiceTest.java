package org.benchmarks.commons.api.helper;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GoogleDriveServiceTest {

    private Drive driveService;
    private Sheets sheetsService;
    private Docs docsService;

    @Before
    public void setUp() throws Exception {
        GoogleDriveService googleDriveService = new GoogleDriveService("/service_account.json");
        this.driveService = googleDriveService.getDrive();
        this.sheetsService = googleDriveService.getSheets();
        this.docsService = googleDriveService.getDocs();
    }

    @Test
    public void getSheetsTest() {
        Assert.assertNotNull(this.sheetsService);
    }

    @Test
    public void getDriveTest() {
        Assert.assertNotNull(this.driveService);
    }

    @Test
    public void getDocsTest() {
        Assert.assertNotNull(this.docsService);
    }
}