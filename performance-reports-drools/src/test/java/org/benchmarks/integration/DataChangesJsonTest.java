package org.benchmarks.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.api.services.drive.Drive;

import org.benchmarks.data.turtle.buildtime.BuildtimeReportData;
import org.benchmarks.data.turtle.runtime.RuntimeReportData;
import org.benchmarks.data.ReportRow;
import org.benchmarks.definitions.SheetPositionsTest;
import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.definitions.ReportType;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.exceptions.GoogleCredentialException;
import org.benchmarks.helper.GoogleDriveHelper;
import org.benchmarks.helper.GoogleDriveService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DataChangesJsonTest {

    private static WireMockServer wireMockServer;
    private static Drive driveService;
    private static String currentVersionBuildtimePath = "http://127.0.0.1:8888/buildtime.json";
    private static String currentVersionRuntimePath = "http://127.0.0.1:8888/runtime.json";
    private static String previousVersionBuildtimePath = "http://127.0.0.1:8888/buildtime.json";
    private static String previousVersionRuntimePath = "http://127.0.0.1:8888/runtime.json";
    private static String olderVersionBuildtimePath = "1ECRrGVSueFOQwr5nVII5ZAXHm3LNjnKI";
    private static String olderVersionRuntimePath = "1p9DioojofQu0EWNxpvm51d0mrrHG2m3i";

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
        BuildtimeReportData droolsBuildtimeJenkinsReport = new BuildtimeReportData();
        RuntimeReportData droolsRuntimeJenkinsReport = new RuntimeReportData();

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(currentVersionBuildtimePath, SourceFileExtension.JSON, SourceFileLocation.WEB);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(currentVersionRuntimePath, SourceFileExtension.JSON, SourceFileLocation.WEB));

        for (Integer key : SheetPositionsTest.droolsSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(SheetPositionsTest.droolsSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(SheetPositionsTest.droolsSheetPositions.size()));
    }

    @Test
    public void resultPreviousVersionDataHashcodeCodeChangesTest() throws IOException {
        BuildtimeReportData droolsBuildtimeJenkinsReport = new BuildtimeReportData();
        RuntimeReportData droolsRuntimeJenkinsReport = new RuntimeReportData();

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(previousVersionBuildtimePath, SourceFileExtension.JSON, SourceFileLocation.WEB);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(previousVersionRuntimePath, SourceFileExtension.JSON, SourceFileLocation.WEB));

        for (Integer key : SheetPositionsTest.droolsSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(SheetPositionsTest.droolsSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(SheetPositionsTest.droolsSheetPositions.size()));
    }

    @Test
    public void resultOlderVersionDataHashcodeCodeChangesTest() throws IOException {
        final String localDir = "target/";

        BuildtimeReportData droolsBuildtimeJenkinsReport = new BuildtimeReportData();
        RuntimeReportData droolsRuntimeJenkinsReport = new RuntimeReportData();

        olderVersionBuildtimePath = GoogleDriveHelper.prepareGoogleDriveFile(driveService, ReportType.BUILDTIME.getFileType(), olderVersionBuildtimePath, localDir, StaticVersion.OLDER, SourceFileExtension.CSV);
        olderVersionRuntimePath = GoogleDriveHelper.prepareGoogleDriveFile(driveService, ReportType.RUNTIME.getFileType(), olderVersionRuntimePath, localDir, StaticVersion.OLDER, SourceFileExtension.CSV);

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(olderVersionBuildtimePath, SourceFileExtension.JSON, SourceFileLocation.LOCAL);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(olderVersionRuntimePath, SourceFileExtension.JSON, SourceFileLocation.LOCAL));

        for (Integer key : SheetPositionsTest.droolsSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(SheetPositionsTest.droolsSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(SheetPositionsTest.droolsSheetPositions.size()));
    }
}
