package org.benchmarks.documents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.sheets.v4.Sheets;
import org.benchmarks.definitions.DroolsPropertiesLoader;
import org.benchmarks.helper.GoogleDriveDocument;
import org.benchmarks.util.PropertiesLoader;

public class DroolsGoogleDriveDocument extends GoogleDriveDocument {

    static String currentVersion = "{{current_version}}";
    static String previousVersion = "{{previous_version}}";
    static String olderVersion = "{{older_version}}";
    static String author = "{{author}}";
    static String emailAuthor = "{{email_author}}";
    static String reportDate = "{{report_date}}";

    public DroolsGoogleDriveDocument() {
        super();
    }

    @Override
    protected List<Request> getReplaceAllBody(PropertiesLoader propertiesLoader) {
        DroolsPropertiesLoader droolsPropertiesLoader = (DroolsPropertiesLoader) propertiesLoader;

        List<Request> requests = new ArrayList<>();

        requests.add(getReplaceTextBodyRequest(currentVersion, droolsPropertiesLoader.getCurrentVersion()));
        requests.add(getReplaceTextBodyRequest(previousVersion, droolsPropertiesLoader.getPreviousVersion()));
        requests.add(getReplaceTextBodyRequest(olderVersion, droolsPropertiesLoader.getOlderVersion()));
        requests.add(getReplaceTextBodyRequest(author, droolsPropertiesLoader.getAuthor()));
        requests.add(getReplaceTextBodyRequest(emailAuthor, droolsPropertiesLoader.getEmailAuthor()));
        requests.add(getReplaceTextBodyRequest(reportDate, droolsPropertiesLoader.getReportDate()));

        return requests;
    }

    public List<Request> getReplaceAllTables(Sheets sheetsService, String spreadSheetID) throws IOException {
        final String spreadsheetBuildtimeSheetName = "Charts - buildtime Oracle JDK8";
        final String spreadsheetBuildtimeRange = "A3:F24";
        final String buildtimeTableName = "Buildtime_Test_Result_Table";
        final int buildtimeStartingCol = 1;
        final int buildtimeStartingRow = 3;
        final String spreadsheetRuntimeSheetName = "Charts - runtime Oracle JDK8";
        final String spreadsheetRuntimeRange = "A3:F25";
        final String runtimeTableName = "Runtime_Test_Result_Table";
        final int runtimeStartingCol = 1;
        final int runtimeStartingRow = 3;

        List<Request> requests = new ArrayList<>();

        requests.addAll(getTableUpdateRequest(getValueRangeFromSheet(sheetsService, spreadSheetID, spreadsheetBuildtimeSheetName, spreadsheetBuildtimeRange),
                                                     buildtimeTableName, buildtimeStartingCol, buildtimeStartingRow));
        requests.addAll(getTableUpdateRequest(getValueRangeFromSheet(sheetsService, spreadSheetID, spreadsheetRuntimeSheetName, spreadsheetRuntimeRange),
                                                     runtimeTableName, runtimeStartingCol, runtimeStartingRow));

        return requests;
    }

}
