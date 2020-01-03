package org.benchmarks.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.benchmarks.data.DroolsBuildtimeJenkinsReport;
import org.benchmarks.data.DroolsRuntimeJenkinsReport;
import org.benchmarks.data.JenkinsReportRow;
import org.benchmarks.definitions.DroolsSheetPositionsTest;
import org.benchmarks.definitions.JenkinsReportFileExtension;
import org.benchmarks.definitions.JenkinsReportLocation;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DroolsCodeChangesJsonTest {

    private static String currentVersionBuildtimePath = "/buildtimeCurrentVersion.json";
    private static String currentVersionRuntimePath = "/runtimeCurrentVersion.json";
    private static String previousVersionBuildtimePath = "/buildtimePreviousVersion.json";
    private static String previousVersionRuntimePath = "/runtimePreviousVersion.json";
    private static String olderVersionBuildtimePath = "/buildtimeOlderVersion.json";
    private static String olderVersionRuntimePath = "/runtimeOlderVersion.json";

    /*Verifies if any code change affects the hashcode ordering strategy. A local config file is loaded to
     * setup local csv source files to compare with a expected hashcode list*/
    @Test
    public void resultCurrentVersionDataHashcodeCodeChangesTest() throws IOException {
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport();
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport();

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(currentVersionBuildtimePath, JenkinsReportFileExtension.JSON, JenkinsReportLocation.CLASSPATH);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(currentVersionRuntimePath, JenkinsReportFileExtension.JSON, JenkinsReportLocation.CLASSPATH));

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
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(previousVersionBuildtimePath, JenkinsReportFileExtension.JSON, JenkinsReportLocation.CLASSPATH);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(previousVersionRuntimePath, JenkinsReportFileExtension.JSON, JenkinsReportLocation.CLASSPATH));

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

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(olderVersionBuildtimePath, JenkinsReportFileExtension.JSON, JenkinsReportLocation.CLASSPATH);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(olderVersionRuntimePath, JenkinsReportFileExtension.JSON, JenkinsReportLocation.CLASSPATH));

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
