package org.benchmarks.drools.documents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.sheets.v4.Sheets;
import org.benchmarks.commons.api.helper.GoogleDriveDocument;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;

public class DroolsGoogleDriveDocument extends GoogleDriveDocument {

    static String newVersion = "{{new_version}}";
    static String previousVersion = "{{previous_version}}";
    static String olderVersion = "{{older_version}}";
    static String author = "{{author}}";
    static String emailAuthor = "{{email_author}}";
    static String reportDate = "{{report_date}}";

    protected DroolsPropertiesLoader droolsPropertiesLoader;

    public DroolsGoogleDriveDocument(String docNewId, String spreadSheetNewId, DroolsPropertiesLoader droolsPropertiesLoader, Docs docService, Sheets sheetService) throws IOException {
        super(docNewId, spreadSheetNewId, docService, sheetService);

        this.droolsPropertiesLoader = droolsPropertiesLoader;
    }

    @Override
    protected List<Request> getReplaceAllBody() {
        List<Request> requests = new ArrayList<>();

        requests.add(getReplaceTextBodyRequest(newVersion, this.droolsPropertiesLoader.getNewVersion()));
        requests.add(getReplaceTextBodyRequest(previousVersion, this.droolsPropertiesLoader.getPreviousVersion()));
        requests.add(getReplaceTextBodyRequest(olderVersion, this.droolsPropertiesLoader.getOlderVersion()));
        requests.add(getReplaceTextBodyRequest(author, this.droolsPropertiesLoader.getAuthor()));
        requests.add(getReplaceTextBodyRequest(emailAuthor, this.droolsPropertiesLoader.getEmailAuthor()));
        requests.add(getReplaceTextBodyRequest(reportDate, this.droolsPropertiesLoader.getReportDate()));

        return requests;
    }
}
