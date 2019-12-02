package org.benchmarks.drools.integration;

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

public class DroolsCodeChangesCsvTest {

    private DroolsPropertiesLoader droolsPropertiesLoader;

    @Before
    public void setUp() {
        droolsPropertiesLoader = new DroolsPropertiesLoader("/drools-reports-code-changes-csv.properties");
    }

    /*Verifies if any code change affects the hashcode ordering strategy. A local config file is loaded to
     * setup local csv source files to compare with a expected hashcode list*/
    @Test
    public void resultNewVersionDataHashcodeCodeChangesTest() {
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport(JenkinsReportVersion.NEW, droolsPropertiesLoader.getNewVersionJenkinsReportLocation(), droolsPropertiesLoader);
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport(JenkinsReportVersion.NEW, droolsPropertiesLoader.getNewVersionJenkinsReportLocation(), droolsPropertiesLoader);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData();
        testResultData.addAll(droolsRuntimeJenkinsReport.getData());

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
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport(JenkinsReportVersion.PREVIOUS, droolsPropertiesLoader.getNewVersionJenkinsReportLocation(), droolsPropertiesLoader);
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport(JenkinsReportVersion.PREVIOUS, droolsPropertiesLoader.getPreviousVersionJenkinsReportLocation(), droolsPropertiesLoader);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData();
        testResultData.addAll(droolsRuntimeJenkinsReport.getData());

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
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport(JenkinsReportVersion.OLDER, droolsPropertiesLoader.getNewVersionJenkinsReportLocation(), droolsPropertiesLoader);
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport(JenkinsReportVersion.OLDER, droolsPropertiesLoader.getOlderVersionJenkinsReportLocation(), droolsPropertiesLoader);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData();
        testResultData.addAll(droolsRuntimeJenkinsReport.getData());

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
