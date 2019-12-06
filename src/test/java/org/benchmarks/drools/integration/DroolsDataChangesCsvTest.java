package org.benchmarks.drools.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.drive.Drive;

import org.benchmarks.commons.api.helper.GoogleDriveHelper;
import org.benchmarks.commons.api.helper.GoogleDriveService;
import org.benchmarks.commons.data.JenkinsReportRow;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.definitions.JenkinsReportType;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.drools.data.DroolsBuildtimeJenkinsReport;
import org.benchmarks.drools.data.DroolsRuntimeJenkinsReport;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.benchmarks.drools.definitions.DroolsSheetPositionsTest;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DroolsDataChangesCsvTest {

    private DroolsPropertiesLoader droolsPropertiesLoader;
    private Drive driveService;

    @Before
    public void setUp() throws Exception {
        droolsPropertiesLoader = new DroolsPropertiesLoader("/drools-reports-data-changes-csv.properties");

        GoogleDriveService googleDriveService = new GoogleDriveService(droolsPropertiesLoader.getGoogleAppApiKeyFile());
        driveService = googleDriveService.getDrive();

        if (droolsPropertiesLoader.getNewVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
            droolsPropertiesLoader.setNewVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
            droolsPropertiesLoader.setNewVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.BUILDTIME.getFileType(), droolsPropertiesLoader.getNewVersionBuildtimePath(), JenkinsReportVersion.NEW, droolsPropertiesLoader.getNewVersionJenkinsReportFileExtension()));
            droolsPropertiesLoader.setNewVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.RUNTIME.getFileType(), droolsPropertiesLoader.getNewVersionRuntimePath(), JenkinsReportVersion.NEW, droolsPropertiesLoader.getNewVersionJenkinsReportFileExtension()));
        }

        if (droolsPropertiesLoader.getPreviousVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
            droolsPropertiesLoader.setPreviousVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
            droolsPropertiesLoader.setPreviousVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.BUILDTIME.getFileType(), droolsPropertiesLoader.getPreviousVersionBuildtimePath(), JenkinsReportVersion.PREVIOUS, droolsPropertiesLoader.getPreviousVersionJenkinsReportFileExtension()));
            droolsPropertiesLoader.setPreviousVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.RUNTIME.getFileType(), droolsPropertiesLoader.getPreviousVersionRuntimePath(), JenkinsReportVersion.PREVIOUS, droolsPropertiesLoader.getPreviousVersionJenkinsReportFileExtension()));
        }

        if (droolsPropertiesLoader.getOlderVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
            droolsPropertiesLoader.setOlderVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
            droolsPropertiesLoader.setOlderVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.BUILDTIME.getFileType(), droolsPropertiesLoader.getOlderVersionBuildtimePath(), JenkinsReportVersion.OLDER, droolsPropertiesLoader.getOlderVersionJenkinsReportFileExtension()));
            droolsPropertiesLoader.setOlderVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.RUNTIME.getFileType(), droolsPropertiesLoader.getOlderVersionRuntimePath(), JenkinsReportVersion.OLDER, droolsPropertiesLoader.getOlderVersionJenkinsReportFileExtension()));
        }
    }

    /*Verifies if any code change affects the hashcode ordering strategy. A local config file is loaded to
     * setup local json source files to compare with a expected hashcode list*/
    @Test
    public void resultNewVersionDataHashcodeCodeChangesTest() throws IOException {
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport();
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport();

        String buildtimePath = droolsBuildtimeJenkinsReport.getDataSourcePath(JenkinsReportVersion.NEW, droolsPropertiesLoader);
        String runtimePath = droolsRuntimeJenkinsReport.getDataSourcePath(JenkinsReportVersion.NEW, droolsPropertiesLoader);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(buildtimePath, droolsPropertiesLoader.getNewVersionJenkinsReportFileExtension(), droolsPropertiesLoader.getNewVersionJenkinsReportLocation());
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(runtimePath, droolsPropertiesLoader.getNewVersionJenkinsReportFileExtension(), droolsPropertiesLoader.getNewVersionJenkinsReportLocation()));

        for (Integer key : DroolsSheetPositionsTest.droolsSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getHashCode() == DroolsSheetPositionsTest.droolsSheetPositions.get(key)) {
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
                if (testResultData.get(i).getHashCode() == DroolsSheetPositionsTest.droolsSheetPositions.get(key)) {
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
                if (testResultData.get(i).getHashCode() == DroolsSheetPositionsTest.droolsSheetPositions.get(key)) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(DroolsSheetPositionsTest.droolsSheetPositions.size()));
    }
}
