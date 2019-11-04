package org.benchmarks.drools.reports.builder;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.*;
import org.benchmarks.drools.reports.data.DroolsProperties;
import org.benchmarks.reports.builder.FileDoc;

import java.util.ArrayList;
import java.util.List;

public class DroolsFileDoc extends FileDoc {

    public DroolsFileDoc(String docNewId, DroolsProperties reportProperties, Docs docService){
        super(docNewId, reportProperties, docService);
    }

    @Override
    public List<Request> getUpdateRequestsBody() {
        List<Request> requests = new ArrayList<>();

        requests.add(getReplaceDocBodyRequest("{{new_version}}", this.reportProperties.getNewVersion()));
        requests.add(getReplaceDocBodyRequest("{{old_version}}", this.reportProperties.getOldVersion()));
        requests.add(getReplaceDocBodyRequest("{{author}}", this.reportProperties.getAuthor()));
        requests.add(getReplaceDocBodyRequest("{{email_author}}", this.reportProperties.getEmailAuthor()));
        requests.add(getReplaceDocBodyRequest("{{report_date}}", this.reportProperties.getReportDate()));
        requests.add(getReplaceDocBodyRequest("{{buildtime_results}}", this.reportProperties.getBuildtimeResults()));
        requests.add(getReplaceDocBodyRequest("{{runtime_results}}", this.reportProperties.getRuntimeResults()));

        return requests;
    }
}
