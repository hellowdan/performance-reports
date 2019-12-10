package org.benchmarks.drools.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.benchmarks.commons.data.JenkinsReportRow;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.drools.data.DroolsBuildtimeJenkinsReport;
import org.benchmarks.drools.data.DroolsRuntimeJenkinsReport;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.benchmarks.drools.definitions.DroolsSheetPositionsTest;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DroolsCodeChangesJsonTest {

    private DroolsPropertiesLoader droolsPropertiesLoader;

    @Before
    public void setUp() throws IOException {
        droolsPropertiesLoader = new DroolsPropertiesLoader("/drools-reports-code-changes-json.properties");
    }

    /*Verifies if any code change affects the hashcode ordering strategy. A local config file is loaded to
     * setup local csv source files to compare with a expected hashcode list*/
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
