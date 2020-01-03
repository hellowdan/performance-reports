package org.benchmarks.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.benchmarks.definitions.DroolsSheetPositionsTest;
import org.benchmarks.definitions.JenkinsReportFileExtension;
import org.benchmarks.definitions.JenkinsReportLocation;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DroolsBuildtimeJenkinsReportTest {

    private static String currentVersionBuildtimePath = "/buildtimeCurrentVersion.json";
    private static String previousVersionBuildtimePath = "/buildtimePreviousVersion.json";
    private static String olderVersionBuildtimePath = "/buildtimeOlderVersion.json";

    @Test
    public void getDataCurrentVersionTest() throws IOException {
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport();

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(currentVersionBuildtimePath, JenkinsReportFileExtension.JSON, JenkinsReportLocation.CLASSPATH);

        for (Integer key : DroolsSheetPositionsTest.droolsBuildtimeSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(DroolsSheetPositionsTest.droolsBuildtimeSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(DroolsSheetPositionsTest.droolsBuildtimeSheetPositions.size()));
    }

    @Test
    public void getDataPreviousVersionTest() throws IOException {
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport();

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(previousVersionBuildtimePath, JenkinsReportFileExtension.JSON, JenkinsReportLocation.CLASSPATH);

        for (Integer key : DroolsSheetPositionsTest.droolsBuildtimeSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(DroolsSheetPositionsTest.droolsBuildtimeSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(DroolsSheetPositionsTest.droolsBuildtimeSheetPositions.size()));
    }

    @Test
    public void getDataOlderVersionTest() throws IOException {
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport();

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(olderVersionBuildtimePath, JenkinsReportFileExtension.JSON, JenkinsReportLocation.CLASSPATH);

        for (Integer key : DroolsSheetPositionsTest.droolsBuildtimeSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(DroolsSheetPositionsTest.droolsBuildtimeSheetPositions.get(key))) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(DroolsSheetPositionsTest.droolsBuildtimeSheetPositions.size()));
    }
}