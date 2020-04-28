package org.benchmarks.helper;

import java.io.IOException;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse;
import com.google.api.services.sheets.v4.model.FindReplaceRequest;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.benchmarks.definitions.ReportType;
import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.exceptions.GoogleSpreadsheetUpdateTextException;
import org.benchmarks.exceptions.GoogleSpreadsheetUpdateValuesException;
import org.benchmarks.util.ReportProperties;

public abstract class GoogleDriveSpreadSheet {

    public GoogleDriveSpreadSheet() {
    }

    protected abstract List<Request> getUpdateInfoRequestsBody(ReportProperties reportProperties);

    protected abstract ValueRange getUpdateDataRequestsBody(SourceFileExtension sourceFileExtension, StaticVersion staticVersion, SourceFileLocation sourceFileLocation, ReportProperties reportProperties, ReportType reportType) throws IOException;

    public BatchUpdateSpreadsheetResponse updateSpreadSheetInfo(ReportProperties reportProperties, Sheets sheetsService, String spreadSheetNewId) throws IOException {
        BatchUpdateSpreadsheetResponse response = null;

        try {
            List<Request> requests = getUpdateInfoRequestsBody(reportProperties);
            BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest().setRequests(requests);
            response = sheetsService.spreadsheets().batchUpdate(spreadSheetNewId, body).execute();
        } catch (IOException e) {
            throw new GoogleSpreadsheetUpdateTextException(e);
        }

        return response;
    }

    public UpdateValuesResponse updateSpreadSheetValues(SourceFileExtension sourceFileExtension, StaticVersion staticVersion, SourceFileLocation sourceFileLocation,
                                                        String startingCell, ReportProperties reportProperties, Sheets sheetsService, String spreadSheetNewId,
                                                        ReportType reportType) throws IOException {
        UpdateValuesResponse response = null;

        try {
            ValueRange body = getUpdateDataRequestsBody(sourceFileExtension, staticVersion, sourceFileLocation, reportProperties, reportType);

            response = sheetsService.spreadsheets().values()
                    .update(spreadSheetNewId, startingCell, body)
                    .setValueInputOption("USER_ENTERED")
                    .execute();
        } catch (IOException e) {
            throw new GoogleSpreadsheetUpdateValuesException(e);
        }

        return response;
    }

    public Request getReplaceSpreadSheetBodyRequest(String placeHolder, String newValue) {
        return new Request().
                setFindReplace(new FindReplaceRequest().
                        setFind(placeHolder).
                        setReplacement(newValue).
                        setAllSheets(true));
    }
}
