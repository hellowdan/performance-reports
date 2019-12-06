package org.benchmarks.drools.documents;

import com.google.api.services.drive.Drive;

import org.benchmarks.commons.api.helper.GoogleDriveService;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DroolsReportTest {

    @Test
    public void generateReportTest() throws Exception {
        DroolsPropertiesLoader droolsPropertiesLoader = new DroolsPropertiesLoader("/droolsReportTest.properties");

        DroolsReport droolsReport = new DroolsReport(droolsPropertiesLoader);
        Boolean result = droolsReport.generateReport();

        assertThat(result, is(true));
    }

    @Test
    public void getGoogleDriveFilesTest() throws Exception {
        DroolsPropertiesLoader droolsPropertiesLoader = new DroolsPropertiesLoader("/getGoogleDriveFiles.properties");
        GoogleDriveService googleDriveService = new GoogleDriveService(droolsPropertiesLoader.getGoogleAppApiKeyFile());

        Drive driveService = googleDriveService.getDrive();

        try {
            DroolsReport droolsReport = new DroolsReport(droolsPropertiesLoader);
            droolsReport.getGoogleDriveFiles(droolsPropertiesLoader, driveService);

            assert(true);
        } catch (Exception e) {
            assert(false);
        }
    }

}
