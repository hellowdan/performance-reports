package org.benchmarks.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.drive.Drive;

import org.benchmarks.data.turtle.buildtime.BuildtimeReportData;
import org.benchmarks.data.turtle.runtime.RuntimeReportData;
import org.benchmarks.data.ReportRow;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.SheetPositionsTest;
import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.definitions.DroolsReportType;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.helper.GoogleDriveHelper;
import org.benchmarks.helper.GoogleDriveService;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DataSourceChangesTest {

    private static DroolsReportProperties droolsReportProperties;
    private static Drive driveService;

    @BeforeClass
    public static void setUp() throws Exception {
        final String localDir = "target/";

        droolsReportProperties = DroolsReportProperties.getInstance();

        GoogleDriveService googleDriveService = new GoogleDriveService(droolsReportProperties.getGoogleAppApiKeyFile());
        driveService = googleDriveService.getDrive();

        if (droolsReportProperties.getCurrentVersionSourceFileLocation() == SourceFileLocation.DRIVE) {
            droolsReportProperties.setCurrentVersionSourceFileLocation(SourceFileLocation.LOCAL);
            droolsReportProperties.setCurrentVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsReportType.BUILDTIME.getFileType(), droolsReportProperties.getCurrentVersionBuildtimePath(), localDir, StaticVersion.CURRENT, droolsReportProperties.getCurrentVersionSourceFileExtension()));
            droolsReportProperties.setCurrentVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsReportType.RUNTIME.getFileType(), droolsReportProperties.getCurrentVersionRuntimePath(), localDir, StaticVersion.CURRENT, droolsReportProperties.getCurrentVersionSourceFileExtension()));
        }

        if (droolsReportProperties.getPreviousVersionSourceFileLocation() == SourceFileLocation.DRIVE) {
            droolsReportProperties.setPreviousVersionSourceFileLocation(SourceFileLocation.LOCAL);
            droolsReportProperties.setPreviousVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsReportType.BUILDTIME.getFileType(), droolsReportProperties.getPreviousVersionBuildtimePath(), localDir, StaticVersion.PREVIOUS, droolsReportProperties.getPreviousVersionSourceFileExtension()));
            droolsReportProperties.setPreviousVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsReportType.RUNTIME.getFileType(), droolsReportProperties.getPreviousVersionRuntimePath(), localDir, StaticVersion.PREVIOUS, droolsReportProperties.getPreviousVersionSourceFileExtension()));
        }

        if (droolsReportProperties.getOlderVersionSourceFileLocation() == SourceFileLocation.DRIVE) {
            droolsReportProperties.setOlderVersionSourceFileLocation(SourceFileLocation.LOCAL);
            droolsReportProperties.setOlderVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsReportType.BUILDTIME.getFileType(), droolsReportProperties.getOlderVersionBuildtimePath(), localDir, StaticVersion.OLDER, droolsReportProperties.getOlderVersionSourceFileExtension()));
            droolsReportProperties.setOlderVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(driveService, DroolsReportType.RUNTIME.getFileType(), droolsReportProperties.getOlderVersionRuntimePath(), localDir, StaticVersion.OLDER, droolsReportProperties.getOlderVersionSourceFileExtension()));
        }
    }

    /*Verifies if any code change affects the hashcode ordering strategy. A local config file is loaded to
     * setup local json source files to compare with a expected hashcode list*/
    @Test
    public void resultCurrentVersionDataHashcodeCodeChangesTest() throws IOException {
        BuildtimeReportData droolsBuildtimeJenkinsReport = new BuildtimeReportData();
        RuntimeReportData droolsRuntimeJenkinsReport = new RuntimeReportData();

        String buildtimePath = droolsBuildtimeJenkinsReport.getDataSourcePath(StaticVersion.CURRENT, droolsReportProperties);
        String runtimePath = droolsRuntimeJenkinsReport.getDataSourcePath(StaticVersion.CURRENT, droolsReportProperties);

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(buildtimePath, droolsReportProperties.getCurrentVersionSourceFileExtension(), droolsReportProperties.getCurrentVersionSourceFileLocation());
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(runtimePath, droolsReportProperties.getCurrentVersionSourceFileExtension(), droolsReportProperties.getCurrentVersionSourceFileLocation()));

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

        String buildtimePath = droolsBuildtimeJenkinsReport.getDataSourcePath(StaticVersion.PREVIOUS, droolsReportProperties);
        String runtimePath = droolsRuntimeJenkinsReport.getDataSourcePath(StaticVersion.PREVIOUS, droolsReportProperties);

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(buildtimePath, droolsReportProperties.getPreviousVersionSourceFileExtension(), droolsReportProperties.getPreviousVersionSourceFileLocation());
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(runtimePath, droolsReportProperties.getPreviousVersionSourceFileExtension(), droolsReportProperties.getPreviousVersionSourceFileLocation()));

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
        BuildtimeReportData droolsBuildtimeJenkinsReport = new BuildtimeReportData();
        RuntimeReportData droolsRuntimeJenkinsReport = new RuntimeReportData();

        String buildtimePath = droolsBuildtimeJenkinsReport.getDataSourcePath(StaticVersion.OLDER, droolsReportProperties);
        String runtimePath = droolsRuntimeJenkinsReport.getDataSourcePath(StaticVersion.OLDER, droolsReportProperties);

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(buildtimePath, droolsReportProperties.getOlderVersionSourceFileExtension(), droolsReportProperties.getOlderVersionSourceFileLocation());
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(runtimePath, droolsReportProperties.getOlderVersionSourceFileExtension(), droolsReportProperties.getOlderVersionSourceFileLocation()));

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
