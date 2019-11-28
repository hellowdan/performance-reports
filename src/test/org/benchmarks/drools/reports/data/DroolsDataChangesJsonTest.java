package org.benchmarks.drools.reports.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.drive.Drive;
import org.benchmarks.drools.reports.definitions.DroolsSheetPositionsTest;
import org.benchmarks.reports.builder.GoogleDriveHelper;
import org.benchmarks.reports.builder.GoogleDriveService;
import org.benchmarks.reports.data.FileLocation;
import org.benchmarks.reports.data.InputFileType;
import org.benchmarks.reports.data.ResultRow;
import org.benchmarks.reports.data.Version;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DroolsDataChangesJsonTest {

    private DroolsProperties droolsProperties;
    private Drive driveService;

    @Before
    public void init() throws IOException {
        droolsProperties = new DroolsProperties("/drools-reports-data-changes-json.properties");

        GoogleDriveService googleDriveService = new GoogleDriveService();
        driveService = googleDriveService.getDrive();

        if (droolsProperties.getNewVersionFileLocation() == FileLocation.DRIVE) {
            droolsProperties.setNewVersionFileLocation(FileLocation.LOCAL);
            droolsProperties.setNewVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, InputFileType.BUILDTIME.getFileType(), droolsProperties.getNewVersionBuildtimePath(), Version.NEW, droolsProperties.getNewVersionFileExtension()));
            droolsProperties.setNewVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, InputFileType.RUNTIME.getFileType(), droolsProperties.getNewVersionRuntimePath(), Version.NEW, droolsProperties.getNewVersionFileExtension()));
        }

        if (droolsProperties.getPreviousVersionFileLocation() == FileLocation.DRIVE) {
            droolsProperties.setPreviousVersionFileLocation(FileLocation.LOCAL);
            droolsProperties.setPreviousVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, InputFileType.BUILDTIME.getFileType(), droolsProperties.getPreviousVersionBuildtimePath(), Version.PREVIOUS, droolsProperties.getPreviousVersionFileExtension()));
            droolsProperties.setPreviousVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, InputFileType.RUNTIME.getFileType(), droolsProperties.getPreviousVersionRuntimePath(), Version.PREVIOUS, droolsProperties.getPreviousVersionFileExtension()));
        }

        if (droolsProperties.getOlderVersionFileLocation() == FileLocation.DRIVE) {
            droolsProperties.setOlderVersionFileLocation(FileLocation.LOCAL);
            droolsProperties.setOlderVersionBuildtimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, InputFileType.BUILDTIME.getFileType(), droolsProperties.getOlderVersionBuildtimePath(), Version.OLDER, droolsProperties.getOlderVersionFileExtension()));
            droolsProperties.setOlderVersionRuntimePath(GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, InputFileType.RUNTIME.getFileType(), droolsProperties.getOlderVersionRuntimePath(), Version.OLDER, droolsProperties.getOlderVersionFileExtension()));
        }
    }

    /*Verifies if any code change affects the hashcode ordering strategy. A local config file is loaded to
    * setup local json source files to compare with a expected hashcode list*/
    @Test
    public void resultNewVersionDataHashcodeCodeChangesTest() {
        DroolsBuildtimeData droolsBuildtimeData = new DroolsBuildtimeData(Version.NEW, droolsProperties.getNewVersionFileLocation(), droolsProperties);
        DroolsRuntimeData droolsRuntimeData = new DroolsRuntimeData(Version.NEW, droolsProperties.getNewVersionFileLocation(), droolsProperties);

        List values = new ArrayList();
        List<ResultRow> testResultData = droolsBuildtimeData.getData();
        testResultData.addAll(droolsRuntimeData.getData());

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
        DroolsBuildtimeData droolsBuildtimeData = new DroolsBuildtimeData(Version.PREVIOUS, droolsProperties.getPreviousVersionFileLocation(), droolsProperties);
        DroolsRuntimeData droolsRuntimeData = new DroolsRuntimeData(Version.PREVIOUS, droolsProperties.getPreviousVersionFileLocation(), droolsProperties);

        List values = new ArrayList();
        List<ResultRow> testResultData = droolsBuildtimeData.getData();
        testResultData.addAll(droolsRuntimeData.getData());

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
        DroolsBuildtimeData droolsBuildtimeData = new DroolsBuildtimeData(Version.OLDER, droolsProperties.getOlderVersionFileLocation(), droolsProperties);
        DroolsRuntimeData droolsRuntimeData = new DroolsRuntimeData(Version.OLDER, droolsProperties.getOlderVersionFileLocation(), droolsProperties);

        List values = new ArrayList();
        List<ResultRow> testResultData = droolsBuildtimeData.getData();
        testResultData.addAll(droolsRuntimeData.getData());

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
