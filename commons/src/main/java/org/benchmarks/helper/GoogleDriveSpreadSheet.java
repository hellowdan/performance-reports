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
import org.benchmarks.definitions.JenkinsReportFileExtension;
import org.benchmarks.definitions.JenkinsReportLocation;
import org.benchmarks.definitions.JenkinsReportVersion;
import org.benchmarks.exceptions.GoogleSpreadsheetUpdateTextException;
import org.benchmarks.exceptions.GoogleSpreadsheetUpdateValuesException;
import org.benchmarks.util.PropertiesLoader;

public abstract class GoogleDriveSpreadSheet {

    public GoogleDriveSpreadSheet() {
    }

    protected abstract List<Request> getUpdateInfoRequestsBody(PropertiesLoader propertiesLoader);

    protected abstract ValueRange getUpdateDataRequestsBody(JenkinsReportFileExtension jenkinsReportFileExtension, JenkinsReportVersion jenkinsReportVersion, JenkinsReportLocation jenkinsReportLocation, PropertiesLoader propertiesLoader) throws IOException;

    public BatchUpdateSpreadsheetResponse updateSpreadSheetInfo(PropertiesLoader propertiesLoader, Sheets sheetsService, String spreadSheetNewId) throws IOException {
        BatchUpdateSpreadsheetResponse response = null;

        try {
            List<Request> requests = getUpdateInfoRequestsBody(propertiesLoader);
            BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest().setRequests(requests);
            response = sheetsService.spreadsheets().batchUpdate(spreadSheetNewId, body).execute();
        } catch (IOException e) {
            throw new GoogleSpreadsheetUpdateTextException(e);
        }

        return response;
    }

    public UpdateValuesResponse updateSpreadSheetValues(JenkinsReportFileExtension jenkinsReportFileExtension, JenkinsReportVersion jenkinsReportVersion, JenkinsReportLocation jenkinsReportLocation, String startingCell, PropertiesLoader propertiesLoader, Sheets sheetsService, String spreadSheetNewId) throws IOException {
        UpdateValuesResponse response = null;

        try {
            ValueRange body = getUpdateDataRequestsBody(jenkinsReportFileExtension, jenkinsReportVersion, jenkinsReportLocation, propertiesLoader);

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
