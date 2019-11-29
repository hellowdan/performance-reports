package org.benchmarks.drools.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.api.services.drive.Drive;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.drools.definitions.DroolsSheetPositionsTest;
import org.benchmarks.commons.definitions.JenkinsReportType;
import org.benchmarks.commons.data.JenkinsReportRow;
import org.benchmarks.commons.api.helper.GoogleDriveHelper;
import org.benchmarks.commons.api.helper.GoogleDriveService;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DroolsDataChangesCsvTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsDataChangesCsvTest.class);

    private DroolsPropertiesLoader droolsProperties;
    private Drive driveService;

    @Before
    public void init() {
        try {
            droolsProperties = new DroolsPropertiesLoader("/drools-reports-data-changes-csv.properties");
        } catch (IOException e) {
            LOGGER.debug("File cannot be read.", e);
        }

        GoogleDriveService googleDriveService = new GoogleDriveService();
        driveService = googleDriveService.getDrive();

        if (droolsProperties.getNewVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
            droolsProperties.setNewVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
            droolsProperties.setNewVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.BUILDTIME.getFileType(), droolsProperties.getNewVersionBuildtimePath(), JenkinsReportVersion.NEW, droolsProperties.getNewVersionJenkinsReportFileExtension()));
            droolsProperties.setNewVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.RUNTIME.getFileType(), droolsProperties.getNewVersionRuntimePath(), JenkinsReportVersion.NEW, droolsProperties.getNewVersionJenkinsReportFileExtension()));
        }

        if (droolsProperties.getPreviousVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
            droolsProperties.setPreviousVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
            droolsProperties.setPreviousVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.BUILDTIME.getFileType(), droolsProperties.getPreviousVersionBuildtimePath(), JenkinsReportVersion.PREVIOUS, droolsProperties.getPreviousVersionJenkinsReportFileExtension()));
            droolsProperties.setPreviousVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.RUNTIME.getFileType(), droolsProperties.getPreviousVersionRuntimePath(), JenkinsReportVersion.PREVIOUS, droolsProperties.getPreviousVersionJenkinsReportFileExtension()));
        }

        if (droolsProperties.getOlderVersionJenkinsReportLocation() == JenkinsReportLocation.DRIVE) {
            droolsProperties.setOlderVersionJenkinsReportLocation(JenkinsReportLocation.LOCAL);
            droolsProperties.setOlderVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.BUILDTIME.getFileType(), droolsProperties.getOlderVersionBuildtimePath(), JenkinsReportVersion.OLDER, droolsProperties.getOlderVersionJenkinsReportFileExtension()));
            droolsProperties.setOlderVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.RUNTIME.getFileType(), droolsProperties.getOlderVersionRuntimePath(), JenkinsReportVersion.OLDER, droolsProperties.getOlderVersionJenkinsReportFileExtension()));
        }
    }

    /*Verifies if any code change affects the hashcode ordering strategy. A local config file is loaded to
     * setup local json source files to compare with a expected hashcode list*/
    @Test
    public void resultNewVersionDataHashcodeCodeChangesTest() {
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport(JenkinsReportVersion.NEW, droolsProperties.getNewVersionJenkinsReportLocation(), droolsProperties);
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport(JenkinsReportVersion.NEW, droolsProperties.getNewVersionJenkinsReportLocation(), droolsProperties);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData();
        testResultData.addAll(droolsRuntimeJenkinsReport.getData());

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
    public void resultPreviousVersionDataHashcodeCodeChangesTest() {
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport(JenkinsReportVersion.PREVIOUS, droolsProperties.getPreviousVersionJenkinsReportLocation(), droolsProperties);
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport(JenkinsReportVersion.PREVIOUS, droolsProperties.getPreviousVersionJenkinsReportLocation(), droolsProperties);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData();
        testResultData.addAll(droolsRuntimeJenkinsReport.getData());

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
    public void resultOlderVersionDataHashcodeCodeChangesTest() {
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport(JenkinsReportVersion.OLDER, droolsProperties.getOlderVersionJenkinsReportLocation(), droolsProperties);
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport(JenkinsReportVersion.OLDER, droolsProperties.getOlderVersionJenkinsReportLocation(), droolsProperties);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData();
        testResultData.addAll(droolsRuntimeJenkinsReport.getData());

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
