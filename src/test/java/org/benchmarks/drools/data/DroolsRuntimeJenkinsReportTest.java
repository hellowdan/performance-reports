package org.benchmarks.drools.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.benchmarks.commons.data.JenkinsReportRow;
import org.benchmarks.commons.definitions.JenkinsReportFileExtension;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.benchmarks.drools.definitions.DroolsSheetPositionsTest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DroolsRuntimeJenkinsReportTest {

    private static String currentVersionRuntimePath = "/runtimeCurrentVersion.json";
    private static String previousVersionRuntimePath = "/runtimePreviousVersion.json";
    private static String olderVersionRuntimePath = "/runtimeOlderVersion.json";
    @Test
    public void getDataCurrentVersionTest() throws IOException {
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport();

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsRuntimeJenkinsReport.getData(currentVersionRuntimePath, JenkinsReportFileExtension.JSON, JenkinsReportLocation.CLASSPATH);

        for (Integer key : DroolsSheetPositionsTest.droolsRuntimeSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getHashCode() == DroolsSheetPositionsTest.droolsRuntimeSheetPositions.get(key)) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(DroolsSheetPositionsTest.droolsRuntimeSheetPositions.size()));
    }

    @Test
    public void getDataPreviousVersionTest() throws IOException {
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport();

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsRuntimeJenkinsReport.getData(previousVersionRuntimePath, JenkinsReportFileExtension.JSON, JenkinsReportLocation.CLASSPATH);

        for (Integer key : DroolsSheetPositionsTest.droolsRuntimeSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getHashCode() == DroolsSheetPositionsTest.droolsRuntimeSheetPositions.get(key)) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(DroolsSheetPositionsTest.droolsRuntimeSheetPositions.size()));
    }

    @Test
    public void getDataOlderVersionTest() throws IOException {
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport();

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsRuntimeJenkinsReport.getData(olderVersionRuntimePath, JenkinsReportFileExtension.JSON, JenkinsReportLocation.CLASSPATH);

        for (Integer key : DroolsSheetPositionsTest.droolsRuntimeSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getHashCode() == DroolsSheetPositionsTest.droolsRuntimeSheetPositions.get(key)) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        assertThat(values.size(), is(DroolsSheetPositionsTest.droolsRuntimeSheetPositions.size()));
    }
}