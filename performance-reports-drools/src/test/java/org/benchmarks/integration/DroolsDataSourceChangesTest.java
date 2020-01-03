package org.benchmarks.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.drive.Drive;

import org.benchmarks.data.DroolsBuildtimeJenkinsReport;
import org.benchmarks.data.DroolsRuntimeJenkinsReport;
import org.benchmarks.data.JenkinsReportRow;
import org.benchmarks.definitions.DroolsPropertiesLoader;
import org.benchmarks.definitions.DroolsSheetPositionsTest;
import org.benchmarks.definitions.JenkinsReportLocation;
import org.benchmarks.definitions.JenkinsReportType;
import org.benchmarks.definitions.JenkinsReportVersion;
import org.benchmarks.helper.GoogleDriveHelper;
import org.benchmarks.helper.GoogleDriveService;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DroolsDataSourceChangesTest {

    private static DroolsPropertiesLoader droolsPropertiesLoader;
    private static Drive driveService;

    @BeforeClass
    public static void setUp() throws Exception {
        final String localDir = "target/";

        droolsPropertiesLoader = DroolsPropertiesLoader.getInstance();

        GoogleDriveService googleDriveService = new GoogleDriveService(droolsPropertiesLoader.getGoogleAppApiKeyFile());
        driveService = googleDriveService.getDrive();

        if (droolsPropertiesLoader.getCurrentVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
            droolsPropertiesLoader.setCurrentVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
            droolsPropertiesLoader.setCurrentVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.BUILDTIME.getFileType(), droolsPropertiesLoader.getCurrentVersionBuildtimePath(), localDir, JenkinsReportVersion.NEW, droolsPropertiesLoader.getCurrentVersionJenkinsReportFileExtension()));
            droolsPropertiesLoader.setCurrentVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.RUNTIME.getFileType(), droolsPropertiesLoader.getCurrentVersionRuntimePath(), localDir, JenkinsReportVersion.NEW, droolsPropertiesLoader.getCurrentVersionJenkinsReportFileExtension()));
        }

        if (droolsPropertiesLoader.getPreviousVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
            droolsPropertiesLoader.setPreviousVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
            droolsPropertiesLoader.setPreviousVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.BUILDTIME.getFileType(), droolsPropertiesLoader.getPreviousVersionBuildtimePath(), localDir, JenkinsReportVersion.PREVIOUS, droolsPropertiesLoader.getPreviousVersionJenkinsReportFileExtension()));
            droolsPropertiesLoader.setPreviousVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.RUNTIME.getFileType(), droolsPropertiesLoader.getPreviousVersionRuntimePath(), localDir, JenkinsReportVersion.PREVIOUS, droolsPropertiesLoader.getPreviousVersionJenkinsReportFileExtension()));
        }

        if (droolsPropertiesLoader.getOlderVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
            droolsPropertiesLoader.setOlderVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
            droolsPropertiesLoader.setOlderVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.BUILDTIME.getFileType(), droolsPropertiesLoader.getOlderVersionBuildtimePath(), localDir, JenkinsReportVersion.OLDER, droolsPropertiesLoader.getOlderVersionJenkinsReportFileExtension()));
            droolsPropertiesLoader.setOlderVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, JenkinsReportType.RUNTIME.getFileType(), droolsPropertiesLoader.getOlderVersionRuntimePath(), localDir, JenkinsReportVersion.OLDER, droolsPropertiesLoader.getOlderVersionJenkinsReportFileExtension()));
        }
    }

    /*Verifies if any code change affects the hashcode ordering strategy. A local config file is loaded to
     * setup local json source files to compare with a expected hashcode list*/
    @Test
    public void resultCurrentVersionDataHashcodeCodeChangesTest() throws IOException {
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport();
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport();

        String buildtimePath = droolsBuildtimeJenkinsReport.getDataSourcePath(JenkinsReportVersion.NEW, droolsPropertiesLoader);
        String runtimePath = droolsRuntimeJenkinsReport.getDataSourcePath(JenkinsReportVersion.NEW, droolsPropertiesLoader);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(buildtimePath, droolsPropertiesLoader.getCurrentVersionJenkinsReportFileExtension(), droolsPropertiesLoader.getCurrentVersionJenkinsReportLocation());
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(runtimePath, droolsPropertiesLoader.getCurrentVersionJenkinsReportFileExtension(), droolsPropertiesLoader.getCurrentVersionJenkinsReportLocation()));

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

        String buildtimePath = droolsBuildtimeJenkinsReport.getDataSourcePath(JenkinsReportVersion.PREVIOUS, droolsPropertiesLoader);
        String runtimePath = droolsRuntimeJenkinsReport.getDataSourcePath(JenkinsReportVersion.PREVIOUS, droolsPropertiesLoader);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(buildtimePath, droolsPropertiesLoader.getPreviousVersionJenkinsReportFileExtension(), droolsPropertiesLoader.getPreviousVersionJenkinsReportLocation());
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(runtimePath, droolsPropertiesLoader.getPreviousVersionJenkinsReportFileExtension(), droolsPropertiesLoader.getPreviousVersionJenkinsReportLocation()));

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
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport();
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport();

        String buildtimePath = droolsBuildtimeJenkinsReport.getDataSourcePath(JenkinsReportVersion.OLDER, droolsPropertiesLoader);
        String runtimePath = droolsRuntimeJenkinsReport.getDataSourcePath(JenkinsReportVersion.OLDER, droolsPropertiesLoader);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(buildtimePath, droolsPropertiesLoader.getOlderVersionJenkinsReportFileExtension(), droolsPropertiesLoader.getOlderVersionJenkinsReportLocation());
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(runtimePath, droolsPropertiesLoader.getOlderVersionJenkinsReportFileExtension(), droolsPropertiesLoader.getOlderVersionJenkinsReportLocation()));

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
