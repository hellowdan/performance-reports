package org.benchmarks.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.benchmarks.definitions.SheetPositionsTest;
import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.definitions.SourceFileLocation;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RuntimeReportDataTest {

    private static String currentVersionRuntimePath = "/runtimeCurrentVersion.json";
    private static String previousVersionRuntimePath = "/runtimePreviousVersion.json";
    private static String olderVersionRuntimePath = "/runtimeOlderVersion.json";
    @Test
    public void getDataCurrentVersionTest() throws IOException {
        RuntimeReportData droolsRuntimeJenkinsReport = new RuntimeReportData();

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsRuntimeJenkinsReport.getData(currentVersionRuntimePath, SourceFileExtension.JSON, SourceFileLocation.CLASSPATH);

        for (Integer key : SheetPositionsTest.droolsRuntimeSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(SheetPositionsTest.droolsRuntimeSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(SheetPositionsTest.droolsRuntimeSheetPositions.size()));
    }

    @Test
    public void getDataPreviousVersionTest() throws IOException {
        RuntimeReportData droolsRuntimeJenkinsReport = new RuntimeReportData();

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsRuntimeJenkinsReport.getData(previousVersionRuntimePath, SourceFileExtension.JSON, SourceFileLocation.CLASSPATH);

        for (Integer key : SheetPositionsTest.droolsRuntimeSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(SheetPositionsTest.droolsRuntimeSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(SheetPositionsTest.droolsRuntimeSheetPositions.size()));
    }

    @Test
    public void getDataOlderVersionTest() throws IOException {
        RuntimeReportData droolsRuntimeJenkinsReport = new RuntimeReportData();

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsRuntimeJenkinsReport.getData(olderVersionRuntimePath, SourceFileExtension.JSON, SourceFileLocation.CLASSPATH);

        for (Integer key : SheetPositionsTest.droolsRuntimeSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(SheetPositionsTest.droolsRuntimeSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(SheetPositionsTest.droolsRuntimeSheetPositions.size()));
    }
}