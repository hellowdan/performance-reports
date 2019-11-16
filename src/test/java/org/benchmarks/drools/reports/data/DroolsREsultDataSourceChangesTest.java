package org.benchmarks.drools.reports.data;

import org.benchmarks.drools.reports.resources.DroolsSheetPositionsTest;
import org.benchmarks.reports.data.ResultRow;
import org.benchmarks.reports.data.Version;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DroolsREsultDataSourceChangesTest {

    private DroolsProperties droolsProperties;

    /*Verifies if any code change affects the hashcode ordering strategy. A local config file is loaded to
     * setup local json source files to compare with a expected hashcode list*/
    @Test
    public void resultNewVersionDataHashcodeCodeChangesTest() throws IOException, ParseException {
        DroolsResultData droolsResultData = new DroolsResultData(Version.NEW);

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
        DroolsResultData droolsResultData = new DroolsResultData(Version.PREVIOUS);

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
        DroolsResultData droolsResultData = new DroolsResultData(Version.OLDER);

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
