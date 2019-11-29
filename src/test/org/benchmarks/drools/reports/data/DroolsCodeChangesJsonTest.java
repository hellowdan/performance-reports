package org.benchmarks.drools.reports.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.benchmarks.drools.reports.definitions.DroolsSheetPositionsTest;
import org.benchmarks.reports.data.ResultRow;
import org.benchmarks.reports.data.Version;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DroolsCodeChangesJsonTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsCodeChangesJsonTest.class);

    private DroolsProperties droolsProperties;

    @Before
    public void getPropertiesInstance() {
        try {
            droolsProperties = new DroolsProperties("/drools-reports-code-changes-json.properties");
        } catch (IOException e) {
            LOGGER.debug("File cannot be read.", e);
        }
    }

    /*Verifies if any code change affects the hashcode ordering strategy. A local config file is loaded to
     * setup local csv source files to compare with a expected hashcode list*/
    @Test
    public void resultNewVersionDataHashcodeCodeChangesTest() {
        DroolsBuildtimeData droolsBuildtimeData = new DroolsBuildtimeData(Version.NEW, droolsProperties.getNewVersionFileLocation(), droolsProperties);
        DroolsRuntimeData droolsRuntimeData = new DroolsRuntimeData(Version.NEW, droolsProperties.getNewVersionFileLocation(), droolsProperties);

        List values = new ArrayList();
        List<ResultRow> testResultData = droolsBuildtimeData.getData();
        testResultData.addAll(droolsRuntimeData.getData());

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
        DroolsBuildtimeData droolsBuildtimeData = new DroolsBuildtimeData(Version.PREVIOUS, droolsProperties.getNewVersionFileLocation(), droolsProperties);
        DroolsRuntimeData droolsRuntimeData = new DroolsRuntimeData(Version.PREVIOUS, droolsProperties.getPreviousVersionFileLocation(), droolsProperties);

        List values = new ArrayList();
        List<ResultRow> testResultData = droolsBuildtimeData.getData();
        testResultData.addAll(droolsRuntimeData.getData());

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
        DroolsBuildtimeData droolsBuildtimeData = new DroolsBuildtimeData(Version.OLDER, droolsProperties.getNewVersionFileLocation(), droolsProperties);
        DroolsRuntimeData droolsRuntimeData = new DroolsRuntimeData(Version.OLDER, droolsProperties.getOlderVersionFileLocation(), droolsProperties);

        List values = new ArrayList();
        List<ResultRow> testResultData = droolsBuildtimeData.getData();
        testResultData.addAll(droolsRuntimeData.getData());

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
