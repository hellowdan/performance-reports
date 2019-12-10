package org.benchmarks.drools.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.benchmarks.commons.data.JenkinsReportRow;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.benchmarks.drools.definitions.DroolsSheetPositionsTest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DroolsRuntimeJenkinsReportTest {

    private DroolsPropertiesLoader droolsPropertiesLoader;

    @Before
    public void setUp() throws Exception {
        droolsPropertiesLoader = new DroolsPropertiesLoader("/drools-reports-code-changes-json.properties");
    }

    @Test
    public void getDataCurrentVersionTest() throws IOException {
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport();

        String runtimePath = droolsRuntimeJenkinsReport.getDataSourcePath(JenkinsReportVersion.NEW, droolsPropertiesLoader);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsRuntimeJenkinsReport.getData(runtimePath, droolsPropertiesLoader.getCurrentVersionJenkinsReportFileExtension(), droolsPropertiesLoader.getCurrentVersionJenkinsReportLocation());

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

        String runtimePath = droolsRuntimeJenkinsReport.getDataSourcePath(JenkinsReportVersion.PREVIOUS, droolsPropertiesLoader);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsRuntimeJenkinsReport.getData(runtimePath, droolsPropertiesLoader.getPreviousVersionJenkinsReportFileExtension(), droolsPropertiesLoader.getPreviousVersionJenkinsReportLocation());

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

        String runtimePath = droolsRuntimeJenkinsReport.getDataSourcePath(JenkinsReportVersion.OLDER, droolsPropertiesLoader);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsRuntimeJenkinsReport.getData(runtimePath, droolsPropertiesLoader.getOlderVersionJenkinsReportFileExtension(), droolsPropertiesLoader.getOlderVersionJenkinsReportLocation());

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