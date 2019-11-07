package org.benchmarks.drools.reports.builder;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.BatchUpdateDocumentResponse;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.sheets.v4.Sheets;
import org.benchmarks.drools.reports.data.DroolsProperties;
import org.benchmarks.reports.builder.FileDoc;
import org.benchmarks.reports.data.DocElementPosition;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DroolsFileDoc extends FileDoc {

    static String newVersion = "{{new_version}}";
    static String oldVersion = "{{old_version}}";
    static String author = "{{author}}";
    static String emailAuthor = "{{email_author}}";
    static String reportDate = "{{report_date}}";
    static String buildtimeResults = "{{buildtime_results}}";
    static String runtimeResults = "{{runtime_results}}";

    public DroolsFileDoc(String docNewId, String spreadSheetNewId, DroolsProperties reportProperties, Docs docService, Sheets sheetService) throws IOException {
        super(docNewId, spreadSheetNewId, reportProperties, docService, sheetService);
    }

    @Override
    protected List<Request> getReplaceAllBody() {
        List<Request> requests = new ArrayList<>();

        requests.add(getReplaceTextBodyRequest(newVersion, this.reportProperties.getNewVersion()));
        requests.add(getReplaceTextBodyRequest(oldVersion, this.reportProperties.getOldVersion()));
        requests.add(getReplaceTextBodyRequest(author, this.reportProperties.getAuthor()));
        requests.add(getReplaceTextBodyRequest(emailAuthor, this.reportProperties.getEmailAuthor()));
        requests.add(getReplaceTextBodyRequest(reportDate, this.reportProperties.getReportDate()));
        requests.add(getReplaceTextBodyRequest(buildtimeResults, this.reportProperties.getBuildtimeResults()));
        requests.add(getReplaceTextBodyRequest(runtimeResults, this.reportProperties.getRuntimeResults()));

        return requests;
    }

}
