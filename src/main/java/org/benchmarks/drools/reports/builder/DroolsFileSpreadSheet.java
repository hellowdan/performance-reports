package org.benchmarks.drools.reports.builder;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import org.benchmarks.drools.reports.data.DroolsResultData;
import org.benchmarks.drools.reports.data.DroolsProperties;
import org.benchmarks.drools.reports.metadata.DroolsSpreadSheetReportChartMetadata;
import org.benchmarks.drools.reports.resources.DroolsSheetPositions;
import org.benchmarks.reports.builder.FileSpreadSheet;
import org.benchmarks.reports.data.ResultRow;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DroolsFileSpreadSheet extends FileSpreadSheet {

    private Spreadsheet spreadsheetMetadata;

    public DroolsFileSpreadSheet(String spreadSheetNewId, DroolsProperties reportProperties, Sheets sheetService){
        super(spreadSheetNewId, reportProperties, sheetService);
    }

    @Override
    protected List<Request> getUpdateInfoRequestsBody() {
        List<Request> requests = new ArrayList<>();

        requests.add(getReplaceSpreadSheetBodyRequest("{{new_version}}", this.reportProperties.getNewVersion()));
        requests.add(getReplaceSpreadSheetBodyRequest("{{old_version}}", this.reportProperties.getOldVersion()));

        return requests;
    }

    @Override
    protected ValueRange getUpdateDataRequestsBody() throws IOException, ParseException {
        DroolsResultData droolsResultData = new DroolsResultData();
        ValueRange body = new ValueRange();

        List values = new ArrayList();
        List<ResultRow> testResultData = droolsResultData.getTestResultData();

        for (Integer key : DroolsSheetPositions.droolsSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getHashCode() == DroolsSheetPositions.droolsSheetPositions.get(key)) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        body.setValues(values);
        return body;
    }
}
