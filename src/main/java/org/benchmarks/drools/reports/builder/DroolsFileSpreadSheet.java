package org.benchmarks.drools.reports.builder;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import org.benchmarks.drools.reports.data.DroolsResultData;
import org.benchmarks.drools.reports.data.DroolsProperties;
import org.benchmarks.drools.reports.resources.DroolsSheetPositions;
import org.benchmarks.reports.data.ResultRow;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DroolsFileSpreadSheet {

    private String spreadSheetNewId;
    private DroolsProperties reportProperties;
    private Sheets sheetService;

    public DroolsFileSpreadSheet(String spreadSheetNewId, DroolsProperties reportProperties, Sheets sheetService){
        this.spreadSheetNewId = spreadSheetNewId;
        this.reportProperties = reportProperties;
        this.sheetService = sheetService;
    }

    public void updateFile() throws IOException, ParseException {
        updateSpreadSheetInfo();
        updateSpreadSheetData();
    }

    private BatchUpdateSpreadsheetResponse updateSpreadSheetInfo() throws IOException {
        List<com.google.api.services.sheets.v4.model.Request> requests = new ArrayList<>();

        getReplaceSpreadSheetBodyRequest(requests, "{{new_version}}", this.reportProperties.getNewVersion());
        getReplaceSpreadSheetBodyRequest(requests, "{{old_version}}", this.reportProperties.getOldVersion());

        BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest().setRequests(requests);
        BatchUpdateSpreadsheetResponse response = this.sheetService.spreadsheets().batchUpdate(this.spreadSheetNewId, body).execute();

        return response;
    }

    public void updateSpreadSheetData() throws IOException, ParseException {
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
        UpdateValuesResponse result = this.sheetService.spreadsheets().values()
                .update(this.spreadSheetNewId, "J2", body)
                .setValueInputOption("USER_ENTERED")
                .execute();
    }

    private void getReplaceSpreadSheetBodyRequest(List<com.google.api.services.sheets.v4.model.Request> request, String placeHolder, String newValue) {
        request.add(new com.google.api.services.sheets.v4.model.Request().
                setFindReplace(new FindReplaceRequest().
                        setFind(placeHolder).
                        setReplacement(newValue).
                        setAllSheets(true)));
    }
}
