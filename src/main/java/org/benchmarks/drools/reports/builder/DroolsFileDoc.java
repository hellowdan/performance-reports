package org.benchmarks.drools.reports.builder;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.*;
import org.benchmarks.drools.reports.data.DroolsProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DroolsFileDoc {
    private String docNewId;
    private DroolsProperties reportProperties;
    private Docs docService;

    public DroolsFileDoc(String docNewId, DroolsProperties reportProperties, Docs docService){
        this.docNewId = docNewId;
        this.reportProperties = reportProperties;
        this.docService = docService;
    }

    public void updateFile() throws IOException{
        updateDoc();
    }

    private BatchUpdateDocumentResponse updateDoc() throws IOException {
        List<Request> requests = new ArrayList<>();

        getReplaceDocBodyRequest(requests, "{{new_version}}", this.reportProperties.getNewVersion());
        getReplaceDocBodyRequest(requests, "{{old_version}}", this.reportProperties.getOldVersion());
        getReplaceDocBodyRequest(requests, "{{author}}", this.reportProperties.getAuthor());
        getReplaceDocBodyRequest(requests, "{{email_author}}", this.reportProperties.getEmailAuthor());
        getReplaceDocBodyRequest(requests, "{{report_date}}", this.reportProperties.getReportDate());
        getReplaceDocBodyRequest(requests, "{{buildtime_results}}", this.reportProperties.getBuildtimeResults());
        getReplaceDocBodyRequest(requests, "{{runtime_results}}", this.reportProperties.getRuntimeResults());

        BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest();
        BatchUpdateDocumentResponse response = this.docService.documents().batchUpdate(this.docNewId, body.setRequests(requests)).execute();
        return response;
    }

    private void getReplaceDocBodyRequest(List<Request> request, String placeHolder, String newValue) {
        request.add(new Request().
                setReplaceAllText(new ReplaceAllTextRequest().
                        setContainsText(new SubstringMatchCriteria().
                                setText(placeHolder).
                                setMatchCase(true)).
                        setReplaceText(newValue)));
    }
}
