package org.benchmarks.documents;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.docs.v1.model.Request;
import org.benchmarks.definitions.DroolsReportProperties;
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
}
