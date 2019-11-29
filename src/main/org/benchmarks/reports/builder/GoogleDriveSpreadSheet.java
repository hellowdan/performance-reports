package org.benchmarks.reports.builder;

import java.io.IOException;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse;
import com.google.api.services.sheets.v4.model.FindReplaceRequest;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.benchmarks.reports.data.FileLocation;
import org.benchmarks.reports.data.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GoogleDriveSpreadSheet {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveSpreadSheet.class);

    protected String spreadSheetNewId;
    protected Sheets sheetService;

    public GoogleDriveSpreadSheet(String spreadSheetNewId, Sheets sheetService) {
        this.spreadSheetNewId = spreadSheetNewId;
        this.sheetService = sheetService;
    }

    protected abstract List<Request> getUpdateInfoRequestsBody();

    protected abstract ValueRange getUpdateDataRequestsBody(Version version, FileLocation fileLocation);

    public BatchUpdateSpreadsheetResponse updateSpreadSheetInfo() {
        BatchUpdateSpreadsheetResponse response = null;

        try {
            List<Request> requests = getUpdateInfoRequestsBody();
            BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest().setRequests(requests);
            response = this.sheetService.spreadsheets().batchUpdate(this.spreadSheetNewId, body).execute();
        } catch (IOException e) {
            LOGGER.debug("Failed updating SpreadSheet text on Google Drive.", e);
        }

        return response;
    }

    public UpdateValuesResponse updateSpreadSheetValues(Version version, FileLocation fileLocation, String startingCell) {
        UpdateValuesResponse response = null;

        try {
            ValueRange body = getUpdateDataRequestsBody(version, fileLocation);

            response = this.sheetService.spreadsheets().values()
                    .update(this.spreadSheetNewId, startingCell, body)
                    .setValueInputOption("USER_ENTERED")
                    .execute();
        } catch (IOException e) {
            LOGGER.debug("Failed updating SpreadSheet numeric values on Google Drive.", e);
        }

        return response;
    }

    protected Request getReplaceSpreadSheetBodyRequest(String placeHolder, String newValue) {
        return new Request().
                setFindReplace(new FindReplaceRequest().
                        setFind(placeHolder).
                        setReplacement(newValue).
                        setAllSheets(true));
    }
}
