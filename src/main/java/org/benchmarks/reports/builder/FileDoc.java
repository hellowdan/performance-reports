package org.benchmarks.reports.builder;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.*;
import org.benchmarks.drools.reports.data.DroolsProperties;

import java.io.IOException;
import java.util.List;

public abstract class FileDoc {

    protected String docNewId;
    protected DroolsProperties reportProperties;
    protected Docs docService;

    public FileDoc(String docNewId, DroolsProperties reportProperties, Docs docService){
        this.docNewId = docNewId;
        this.reportProperties = reportProperties;
        this.docService = docService;
    }

    public BatchUpdateDocumentResponse requestUpdates() throws IOException{
        List<Request> requests = getUpdateRequestsBody();

        BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest();
        BatchUpdateDocumentResponse response = this.docService.documents().batchUpdate(this.docNewId, body.setRequests(requests)).execute();
        return response;
    }

    protected abstract List<Request> getUpdateRequestsBody();

    protected Request getReplaceDocBodyRequest(String placeHolder, String newValue) {
        Request request = new Request().
                setReplaceAllText(new ReplaceAllTextRequest().
                        setContainsText(new SubstringMatchCriteria().
                                setText(placeHolder).
                                setMatchCase(true)).
                        setReplaceText(newValue));

        return request;
    }

}
