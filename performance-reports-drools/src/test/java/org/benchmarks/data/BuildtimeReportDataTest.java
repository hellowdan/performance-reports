package org.benchmarks.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.benchmarks.data.turtle.buildtime.BuildtimeReportData;
import org.benchmarks.definitions.SheetPositionsTest;
import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.definitions.SourceFileLocation;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BuildtimeReportDataTest {

    private static String currentVersionBuildtimePath = "/buildtimeCurrentVersion.json";
    private static String previousVersionBuildtimePath = "/buildtimePreviousVersion.json";
    private static String olderVersionBuildtimePath = "/buildtimeOlderVersion.json";

    @Test
    public void getDataCurrentVersionTest() throws IOException {
        BuildtimeReportData droolsBuildtimeJenkinsReport = new BuildtimeReportData();

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(currentVersionBuildtimePath, SourceFileExtension.JSON, SourceFileLocation.CLASSPATH);

        for (Integer key : SheetPositionsTest.droolsBuildtimeSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(SheetPositionsTest.droolsBuildtimeSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(SheetPositionsTest.droolsBuildtimeSheetPositions.size()));
    }

    @Test
    public void getDataPreviousVersionTest() throws IOException {
        BuildtimeReportData droolsBuildtimeJenkinsReport = new BuildtimeReportData();

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(previousVersionBuildtimePath, SourceFileExtension.JSON, SourceFileLocation.CLASSPATH);

        for (Integer key : SheetPositionsTest.droolsBuildtimeSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(SheetPositionsTest.droolsBuildtimeSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(SheetPositionsTest.droolsBuildtimeSheetPositions.size()));
    }

    @Test
    public void getDataOlderVersionTest() throws IOException {
        BuildtimeReportData droolsBuildtimeJenkinsReport = new BuildtimeReportData();

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(olderVersionBuildtimePath, SourceFileExtension.JSON, SourceFileLocation.CLASSPATH);

        for (Integer key : SheetPositionsTest.droolsBuildtimeSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(SheetPositionsTest.droolsBuildtimeSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(SheetPositionsTest.droolsBuildtimeSheetPositions.size()));
    }
}