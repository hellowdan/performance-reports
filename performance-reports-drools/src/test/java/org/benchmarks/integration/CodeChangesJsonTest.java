package org.benchmarks.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.benchmarks.data.turtle.buildtime.BuildtimeReportData;
import org.benchmarks.data.turtle.runtime.RuntimeReportData;
import org.benchmarks.data.ReportRow;
import org.benchmarks.definitions.SheetPositionsTest;
import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.definitions.SourceFileLocation;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CodeChangesJsonTest {

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
        BuildtimeReportData droolsBuildtimeJenkinsReport = new BuildtimeReportData();
        RuntimeReportData droolsRuntimeJenkinsReport = new RuntimeReportData();

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(currentVersionBuildtimePath, SourceFileExtension.JSON, SourceFileLocation.CLASSPATH);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(currentVersionRuntimePath, SourceFileExtension.JSON, SourceFileLocation.CLASSPATH));

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
        List<ReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(previousVersionBuildtimePath, SourceFileExtension.JSON, SourceFileLocation.CLASSPATH);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(previousVersionRuntimePath, SourceFileExtension.JSON, SourceFileLocation.CLASSPATH));

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

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(olderVersionBuildtimePath, SourceFileExtension.JSON, SourceFileLocation.CLASSPATH);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(olderVersionRuntimePath, SourceFileExtension.JSON, SourceFileLocation.CLASSPATH));

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
