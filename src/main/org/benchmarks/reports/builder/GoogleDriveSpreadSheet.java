package org.benchmarks.reports.builder;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import org.benchmarks.reports.data.FileLocation;
import org.benchmarks.reports.data.Version;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public abstract class GoogleDriveSpreadSheet {

    protected String spreadSheetNewId;
    protected Sheets sheetService;

    public GoogleDriveSpreadSheet(String spreadSheetNewId, Sheets sheetService) {
        this.spreadSheetNewId = spreadSheetNewId;
        this.sheetService = sheetService;
    }

    protected abstract List<Request> getUpdateInfoRequestsBody();

    protected abstract ValueRange getUpdateDataRequestsBody(Version version, FileLocation fileLocation) throws IOException, ParseException;

    public BatchUpdateSpreadsheetResponse updateSpreadSheetInfo() throws IOException {
        List<Request> requests = getUpdateInfoRequestsBody();

        BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest().setRequests(requests);
        return this.sheetService.spreadsheets().batchUpdate(this.spreadSheetNewId, body).execute();
    }

    public UpdateValuesResponse updateSpreadSheetValues(Version version, FileLocation fileLocation, String startingCell) throws IOException, ParseException {
        ValueRange body = getUpdateDataRequestsBody(version, fileLocation);

        return this.sheetService.spreadsheets().values()
                .update(this.spreadSheetNewId, startingCell, body)
                .setValueInputOption("USER_ENTERED")
                .execute();
    }

    protected Request getReplaceSpreadSheetBodyRequest(String placeHolder, String newValue) {
        return new Request().
                setFindReplace(new FindReplaceRequest().
                        setFind(placeHolder).
                        setReplacement(newValue).
                        setAllSheets(true));
    }
}
