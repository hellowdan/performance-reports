package org.benchmarks.documents;

import com.google.api.services.drive.Drive;

import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.ProcessScope;
import org.benchmarks.helper.GoogleDriveService;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DroolsReportTest {

    @Test
    public void generateReportTest() throws Exception {
        DroolsReportProperties droolsReportProperties = new DroolsReportProperties("/droolsReportTest.properties");

        DroolsReport droolsReport = new DroolsReport(droolsReportProperties, ProcessScope.FULL);
        Boolean result = droolsReport.generateReport();

        assertThat(result, is(true));
    }

    @Test
    public void getGoogleDriveFilesTest() throws Exception {
        final String localDir = "target/";
        Boolean result;
        DroolsReportProperties droolsReportProperties = new DroolsReportProperties("/getGoogleDriveFiles.properties");
        GoogleDriveService googleDriveService = new GoogleDriveService(droolsReportProperties.getGoogleAppApiKeyFile());

        Drive driveService = googleDriveService.getDrive();

        try {
            DroolsReport droolsReport = new DroolsReport(droolsReportProperties, ProcessScope.FULL);
            droolsReport.getGoogleDriveFiles(droolsReportProperties, driveService, localDir);

            result = true;
        } catch (Exception e) {
            result = false;
        }
        assertThat(result, is(true));
    }

}
