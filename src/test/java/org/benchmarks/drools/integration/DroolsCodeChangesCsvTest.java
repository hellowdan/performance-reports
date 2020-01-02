package org.benchmarks.drools.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.benchmarks.commons.data.JenkinsReportRow;
import org.benchmarks.commons.definitions.JenkinsReportFileExtension;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.drools.data.DroolsBuildtimeJenkinsReport;
import org.benchmarks.drools.data.DroolsRuntimeJenkinsReport;
import org.benchmarks.drools.definitions.DroolsSheetPositionsTest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DroolsCodeChangesCsvTest {

    private static String currentVersionBuildtimePath = "/buildtimeCurrentVersion.csv";
    private static String currentVersionRuntimePath = "/runtimeCurrentVersion.csv";
    private static String previousVersionBuildtimePath = "/buildtimePreviousVersion.csv";
    private static String previousVersionRuntimePath = "/runtimePreviousVersion.csv";
    private static String olderVersionBuildtimePath = "/buildtimeOlderVersion.csv";
    private static String olderVersionRuntimePath = "/runtimeOlderVersion.csv";

    /*Verifies if any code change affects the hashcode ordering strategy. A local config file is loaded to
     * setup local csv source files to compare with a expected hashcode list*/
    @Test
    public void resultCurrentVersionDataHashcodeCodeChangesTest() throws IOException {
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport();
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport();

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(currentVersionBuildtimePath, JenkinsReportFileExtension.CSV, JenkinsReportLocation.CLASSPATH);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(currentVersionRuntimePath, JenkinsReportFileExtension.CSV, JenkinsReportLocation.CLASSPATH));

        for (Integer key : DroolsSheetPositionsTest.droolsSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(DroolsSheetPositionsTest.droolsSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    System.out.println(testResultData.get(i).getUniqueID());
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
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(previousVersionBuildtimePath, JenkinsReportFileExtension.CSV, JenkinsReportLocation.CLASSPATH);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(previousVersionRuntimePath, JenkinsReportFileExtension.CSV, JenkinsReportLocation.CLASSPATH));

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
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(olderVersionBuildtimePath, JenkinsReportFileExtension.CSV, JenkinsReportLocation.CLASSPATH);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(olderVersionRuntimePath, JenkinsReportFileExtension.CSV, JenkinsReportLocation.CLASSPATH));

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
