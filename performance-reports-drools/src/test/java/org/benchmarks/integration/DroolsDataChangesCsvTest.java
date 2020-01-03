package org.benchmarks.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.api.services.drive.Drive;
import org.benchmarks.data.DroolsBuildtimeJenkinsReport;
import org.benchmarks.data.DroolsRuntimeJenkinsReport;
import org.benchmarks.data.JenkinsReportRow;
import org.benchmarks.definitions.DroolsSheetPositionsTest;
import org.benchmarks.definitions.JenkinsReportFileExtension;
import org.benchmarks.definitions.JenkinsReportLocation;
import org.benchmarks.definitions.JenkinsReportType;
import org.benchmarks.definitions.JenkinsReportVersion;
import org.benchmarks.exceptions.GoogleCredentialException;
import org.benchmarks.helper.GoogleDriveHelper;
import org.benchmarks.helper.GoogleDriveService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DroolsDataChangesCsvTest {

    private static WireMockServer wireMockServer;
    private static Drive driveService;
    private static String currentVersionBuildtimePath = "http://127.0.0.1:8888/buildtime.csv";
    private static String currentVersionRuntimePath = "http://127.0.0.1:8888/runtime.csv";
    private static String previousVersionBuildtimePath = "http://127.0.0.1:8888/buildtime.csv";
    private static String previousVersionRuntimePath = "http://127.0.0.1:8888/runtime.csv";
    private static String olderVersionBuildtimePath = "1pXXbcjj2qLqVx98rUPJbxIIkRGboW5YD";
    private static String olderVersionRuntimePath = "1QWaKPloJiifWhiudvPGfqs46jX6ExtZJ";

    @BeforeClass
    public static void setUpServer() throws GoogleCredentialException {
        wireMockServer = new WireMockServer(wireMockConfig().port(8888).httpsPort(8889).withRootDirectory("src/test/resources/"));
        wireMockServer.start();

        GoogleDriveService googleDriveService = new GoogleDriveService("/service_account.json");
        driveService = googleDriveService.getDrive();
    }

    @AfterClass
    public static void tearDown() {
        wireMockServer.stop();
    }

    /*Verifies if any code change affects the hashcode ordering strategy. A local config file is loaded to
     * setup local json source files to compare with a expected hashcode list*/
    @Test
    public void resultCurrentVersionDataHashcodeCodeChangesTest() throws IOException {
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport();
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport();

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(currentVersionBuildtimePath, JenkinsReportFileExtension.CSV, JenkinsReportLocation.WEB);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(currentVersionRuntimePath, JenkinsReportFileExtension.CSV, JenkinsReportLocation.WEB));

        for (Integer key : DroolsSheetPositionsTest.droolsSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(DroolsSheetPositionsTest.droolsSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(DroolsSheetPositionsTest.droolsSheetPositions.size()));
    }

    @Test
    public void resultPreviousVersionDataHashcodeCodeChangesTest() throws IOException {
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport();
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport();

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(previousVersionBuildtimePath, JenkinsReportFileExtension.CSV, JenkinsReportLocation.WEB);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(previousVersionRuntimePath, JenkinsReportFileExtension.CSV, JenkinsReportLocation.WEB));

        for (Integer key : DroolsSheetPositionsTest.droolsSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(DroolsSheetPositionsTest.droolsSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(DroolsSheetPositionsTest.droolsSheetPositions.size()));
    }

    @Test
    public void resultOlderVersionDataHashcodeCodeChangesTest() throws IOException {
        final String localDir = "target/";

        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport();
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport();

        olderVersionBuildtimePath = GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.BUILDTIME.getFileType(), olderVersionBuildtimePath, localDir, JenkinsReportVersion.OLDER, JenkinsReportFileExtension.CSV);
        olderVersionRuntimePath = GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.RUNTIME.getFileType(), olderVersionRuntimePath, localDir, JenkinsReportVersion.OLDER, JenkinsReportFileExtension.CSV);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(olderVersionBuildtimePath, JenkinsReportFileExtension.CSV, JenkinsReportLocation.LOCAL);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(olderVersionRuntimePath, JenkinsReportFileExtension.CSV, JenkinsReportLocation.LOCAL));

        for (Integer key : DroolsSheetPositionsTest.droolsSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(DroolsSheetPositionsTest.droolsSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(DroolsSheetPositionsTest.droolsSheetPositions.size()));
    }
}
