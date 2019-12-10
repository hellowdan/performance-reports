package org.benchmarks.drools.documents;

import java.util.ArrayList;
import java.util.List;
import com.google.api.services.docs.v1.model.Request;
import org.benchmarks.commons.api.helper.GoogleDriveDocument;
import org.benchmarks.commons.util.PropertiesLoader;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;

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
}
