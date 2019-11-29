package org.benchmarks.drools.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.drools.definitions.DroolsSheetPositionsTest;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.benchmarks.commons.data.JenkinsReportRow;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DroolsCodeChangesJsonTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsCodeChangesJsonTest.class);

    private DroolsPropertiesLoader droolsProperties;

    @Before
    public void getPropertiesInstance() {
        try {
            droolsProperties = new DroolsPropertiesLoader("/org/benchmarks/drools/resources/drools-reports-code-changes-json.properties");
        } catch (IOException e) {
            LOGGER.debug("File cannot be read.", e);
        }
    }

    /*Verifies if any code change affects the hashcode ordering strategy. A local config file is loaded to
     * setup local csv source files to compare with a expected hashcode list*/
    @Test
    public void resultNewVersionDataHashcodeCodeChangesTest() {
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport(JenkinsReportVersion.NEW, droolsProperties.getNewVersionJenkinsReportLocation(), droolsProperties);
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport(JenkinsReportVersion.NEW, droolsProperties.getNewVersionJenkinsReportLocation(), droolsProperties);

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
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport(JenkinsReportVersion.PREVIOUS, droolsProperties.getNewVersionJenkinsReportLocation(), droolsProperties);
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport(JenkinsReportVersion.PREVIOUS, droolsProperties.getPreviousVersionJenkinsReportLocation(), droolsProperties);

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
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport(JenkinsReportVersion.OLDER, droolsProperties.getNewVersionJenkinsReportLocation(), droolsProperties);
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport(JenkinsReportVersion.OLDER, droolsProperties.getOlderVersionJenkinsReportLocation(), droolsProperties);

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
