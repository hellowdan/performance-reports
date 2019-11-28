package org.benchmarks.drools.reports.builder;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.sheets.v4.Sheets;
import org.benchmarks.drools.reports.data.DroolsProperties;
import org.benchmarks.reports.builder.GoogleDriveDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DroolsGoogleDriveDocument extends GoogleDriveDocument {

    static String newVersion = "{{new_version}}";
    static String previousVersion = "{{previous_version}}";
    static String olderVersion = "{{older_version}}";
    static String author = "{{author}}";
    static String emailAuthor = "{{email_author}}";
    static String reportDate = "{{report_date}}";

    protected DroolsProperties reportProperties;

    public DroolsGoogleDriveDocument(String docNewId, String spreadSheetNewId, DroolsProperties reportProperties, Docs docService, Sheets sheetService) throws IOException {
        super(docNewId, spreadSheetNewId, docService, sheetService);

        this.reportProperties = reportProperties;
    }

    @Override
    protected List<Request> getReplaceAllBody() {
        List<Request> requests = new ArrayList<>();

        requests.add(getReplaceTextBodyRequest(newVersion, this.reportProperties.getNewVersion()));
        requests.add(getReplaceTextBodyRequest(previousVersion, this.reportProperties.getPreviousVersion()));
        requests.add(getReplaceTextBodyRequest(olderVersion, this.reportProperties.getOlderVersion()));
        requests.add(getReplaceTextBodyRequest(author, this.reportProperties.getAuthor()));
        requests.add(getReplaceTextBodyRequest(emailAuthor, this.reportProperties.getEmailAuthor()));
        requests.add(getReplaceTextBodyRequest(reportDate, this.reportProperties.getReportDate()));

        return requests;
    }
}
