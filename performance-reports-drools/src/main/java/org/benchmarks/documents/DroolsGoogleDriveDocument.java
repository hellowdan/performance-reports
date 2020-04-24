package org.benchmarks.documents;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.Document;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.docs.v1.model.RgbColor;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.GoogleDocumentElementPosition;
import org.benchmarks.exceptions.FileCannotBeReadException;
import org.benchmarks.helper.GoogleDriveDocument;
import org.benchmarks.util.ReportProperties;

public class DroolsGoogleDriveDocument extends GoogleDriveDocument {

    static String nextVersion = "{{next_version}}";
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
    protected List<Request> getReplaceAllBody(ReportProperties reportProperties) {
        DroolsReportProperties droolsReportProperties = (DroolsReportProperties) reportProperties;

        List<Request> requests = new ArrayList<>();

        requests.add(getReplaceTextBodyRequest(nextVersion, droolsReportProperties.getNextVersion()));
        requests.add(getReplaceTextBodyRequest(currentVersion, droolsReportProperties.getCurrentVersion()));
        requests.add(getReplaceTextBodyRequest(previousVersion, droolsReportProperties.getPreviousVersion()));
        requests.add(getReplaceTextBodyRequest(olderVersion, droolsReportProperties.getOlderVersion()));
        requests.add(getReplaceTextBodyRequest(author, droolsReportProperties.getAuthor()));
        requests.add(getReplaceTextBodyRequest(emailAuthor, droolsReportProperties.getEmailAuthor()));
        requests.add(getReplaceTextBodyRequest(reportDate, droolsReportProperties.getReportDate()));

        return requests;
    }

    public List<Request> getFormatAllTables(Docs docsService, String docID, ValueRange buildTimeValueRange, ValueRange runTimeValueRange) throws IOException {
        List<Request> requests = new ArrayList<>();
        requests.addAll(getTableUpdateCellColorRequests(docID, docsService, buildTimeValueRange));
        requests.addAll(getTableUpdateCellColorRequests(docID, docsService, runTimeValueRange));

        return requests;
    }

    public List<Request> getReplaceAllTables(ValueRange buildTimeValueRange, ValueRange runTimeValueRange) throws IOException {
        final String buildtimeTableName = "Buildtime_Test_Result_Table";
        final int buildtimeStartingCol = 1;
        final int buildtimeStartingRow = 3;
        final String runtimeTableName = "Runtime_Test_Result_Table";
        final int runtimeStartingCol = 1;
        final int runtimeStartingRow = 3;
        final String cellFormat = "%s_%d_%d";

        List<Request> requests = new ArrayList<>();
        requests.addAll(getTableUpdateRequest(buildTimeValueRange, cellFormat, buildtimeTableName, buildtimeStartingCol, buildtimeStartingRow));
        requests.addAll(getTableUpdateRequest(runTimeValueRange, cellFormat, runtimeTableName, runtimeStartingCol, runtimeStartingRow));

        return requests;
    }

    public List<Request> getTableUpdateCellColorRequests(String docNewId, Docs docService, ValueRange jsonTableFromSheets) throws IOException {
        List<Request> requests = new ArrayList<>();
        Document documentMetadata = loadMetadataFromDocument(docNewId, docService);

        try {
            for (int i = 0; i < jsonTableFromSheets.getValues().size(); i++) {
                for (int j = 4; j < jsonTableFromSheets.getValues().get(i).size(); j++) {
                    String stringValue = jsonTableFromSheets.getValues().get(i).get(j).toString();

                    BigDecimal value = new BigDecimal(stringValue.trim().replace("%", "")).divide(BigDecimal.valueOf(100));
                    stringValue += "\n";

                    if (value.doubleValue() > 0.05) {
                        GoogleDocumentElementPosition googleDocumentElementPosition = searchForTableCellContent(documentMetadata.getBody().getContent(), stringValue);
                        if (googleDocumentElementPosition != null) {
                            requests.add(this.getFormatTextColorRequest(stringValue, new RgbColor().setBlue(0.11F).setGreen(0.46F).setRed(0.22F), googleDocumentElementPosition.getStartIndex()));
                        }
                    } else if (value.doubleValue() < -0.05) {
                        GoogleDocumentElementPosition googleDocumentElementPosition = searchForTableCellContent(documentMetadata.getBody().getContent(), stringValue);
                        if (googleDocumentElementPosition != null) {
                            requests.add(this.getFormatTextColorRequest(stringValue, new RgbColor().setBlue(0.0F).setGreen(0.0F).setRed(1.0F), googleDocumentElementPosition.getStartIndex()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new FileCannotBeReadException(e);
        }

        return requests;
    }
}
