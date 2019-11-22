package org.benchmarks.drools.reports.data;

import org.benchmarks.drools.reports.resources.DroolsSheetPositionsTest;
import org.benchmarks.reports.data.ResultRow;
import org.benchmarks.reports.data.Version;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DroolsResultDataCodeChangesCsvTest {

    private DroolsProperties droolsProperties;

    @Before
    public void getPropertiesInstance() throws IOException {
        droolsProperties = new DroolsProperties("/home/drosa/Documents/Workspace/performance-reports-new-requirements/performance-reports/src/test/java/org/benchmarks/drools/reports/resources/drools-reports-test-csv.properties");
    }

    /*Verifies if any code change affects the hashcode ordering strategy. A local config file is loaded to
     * setup local csv source files to compare with a expected hashcode list*/
    @Test
    public void resultNewVersionDataHashcodeCodeChangesTest() throws IOException, ParseException {
        DroolsResultData droolsResultData = new DroolsResultData(Version.NEW, droolsProperties);

        List values = new ArrayList();
        List<ResultRow> testResultData = droolsResultData.getTestResultData();

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
    public void resultPreviousVersionDataHashcodeCodeChangesTest() throws IOException, ParseException {
        DroolsResultData droolsResultData = new DroolsResultData(Version.PREVIOUS, droolsProperties);

        List values = new ArrayList();
        List<ResultRow> testResultData = droolsResultData.getTestResultData();

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
    public void resultOlderVersionDataHashcodeCodeChangesTest() throws IOException, ParseException {
        DroolsResultData droolsResultData = new DroolsResultData(Version.OLDER, droolsProperties);

        List values = new ArrayList();
        List<ResultRow> testResultData = droolsResultData.getTestResultData();

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
