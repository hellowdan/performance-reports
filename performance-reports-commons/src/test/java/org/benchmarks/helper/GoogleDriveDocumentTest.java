package org.benchmarks.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.BatchUpdateDocumentResponse;
import com.google.api.services.docs.v1.model.Document;
import com.google.api.services.docs.v1.model.InsertInlineImageRequest;
import com.google.api.services.docs.v1.model.Link;
import com.google.api.services.docs.v1.model.Location;
import com.google.api.services.docs.v1.model.Range;
import com.google.api.services.docs.v1.model.ReplaceAllTextRequest;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.docs.v1.model.SubstringMatchCriteria;
import com.google.api.services.docs.v1.model.TextStyle;
import com.google.api.services.docs.v1.model.UpdateTextStyleRequest;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Spreadsheet;

import org.benchmarks.util.PropertiesLoader;
import org.benchmarks.definitions.GoogleDocumentElementPosition;
import org.json.simple.parser.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GoogleDriveDocumentTest {

    GoogleDriveDocument googleDriveDocument;
    private Drive driveService;
    private Docs docsService;
    private Sheets sheetsService;
    private String docTestId = "1094iiurK5lwlcVHR4zRluW7YZOeeXm9VLxBRADpvSOo";
    private String sheetTestId = "1V3ITyMR7aiHAvSbCy5aOv6U3xtu5KGWAVaLFiYqOoIU";

    @Before
    public void setUp() throws Exception {
        GoogleDriveService googleDriveService = new GoogleDriveService("/service_account.json");
        this.driveService = googleDriveService.getDrive();
        this.docsService = googleDriveService.getDocs();
        this.sheetsService = googleDriveService.getSheets();

        this.googleDriveDocument = new GoogleDriveDocument() {
            @Override
            protected List<Request> getReplaceAllBody(PropertiesLoader propertiesLoader) {
                return null;
            }
        };
    }

    @Test
    public void requestsExecuteTest() throws IOException {

        String placeHolderRequest1 = "requestsExecuteTestRequest1";
        String newValueRequest1 = "requestsExecuteTestRequest1Success";
        Request request1 = new Request().setReplaceAllText(new ReplaceAllTextRequest().setContainsText(new SubstringMatchCriteria().
                setText(placeHolderRequest1).
                setMatchCase(true)).
                setReplaceText(newValueRequest1));

        String placeHolderRequest2 = "requestsExecuteTestRequest2";
        String newValueRequest2 = "requestsExecuteTestRequest2Success";
        Request request2 = new Request().setReplaceAllText(new ReplaceAllTextRequest().setContainsText(new SubstringMatchCriteria().
                setText(placeHolderRequest2).
                setMatchCase(true)).
                setReplaceText(newValueRequest2));

        List<Request> requests = new ArrayList<>();
        requests.add(request1);
        requests.add(request2);

        BatchUpdateDocumentResponse response = this.googleDriveDocument.requestsExecute(requests, this.docTestId, this.docsService);

        assertThat(response.getReplies().size(), is(2));
    }

    @Test
    public void getReplaceTextBodyRequestTest() {
        String placeHolderRequest = "requestsExecuteTestRequest";
        String newValueRequest = "requestsExecuteTestRequestSuccess";
        Request request1 = new Request().setReplaceAllText(new ReplaceAllTextRequest().setContainsText(new SubstringMatchCriteria().
                setText(placeHolderRequest).
                setMatchCase(true)).
                setReplaceText(newValueRequest));

        Request request2 = this.googleDriveDocument.getReplaceTextBodyRequest(placeHolderRequest, newValueRequest);

        assertEquals(request1, request2);
    }

    @Test
    public void getReplaceLinkBodyRequestTest() {
        String title = "requestsExecuteTestRequest";
        String indexOf = "10";
        String linkValue = "http://localhost:9090/requestsExecuteTestRequest.htm";

        Request request1 = new Request()
                .setUpdateTextStyle(new UpdateTextStyleRequest()
                                            .setRange(new Range()
                                                              .setStartIndex(Integer.parseInt(indexOf))
                                                              .setEndIndex(Integer.parseInt(indexOf) + title.length()))
                                            .setTextStyle(new TextStyle()
                                                                  .setLink(new Link()
                                                                                   .setUrl(linkValue)))
                                            .setFields("link"));

        Request request2 = this.googleDriveDocument.getReplaceLinkBodyRequest(title, linkValue, indexOf);

        assertEquals(request1, request2);
    }

    @Test
    public void getImageUpdateRequestTest() {
        String Uri = "http://localhost:9090/requestsExecuteTestImageRequest.png";
        String locationIndex = "10";

        Request request1 = new Request().
                setInsertInlineImage(new InsertInlineImageRequest()
                                             .setUri(Uri)
                                             .setLocation(new Location().setIndex(Integer.parseInt(locationIndex)))
                );

        Request request2 = this.googleDriveDocument.getImageUpdateRequest(locationIndex, Uri);

        assertEquals(request1, request2);
    }

    @Test
    public void loadMetadataFromSpreadSheetTest() throws IOException {
        Spreadsheet spreadsheetMetadata = this.googleDriveDocument.loadMetadataFromSpreadSheet(this.sheetTestId, this.sheetsService);

        assertThat(spreadsheetMetadata.getSheets().size(), is(4));
    }

    @Test
    public void loadMetadataFromDocumentTest() throws IOException {
        Document documentMetadata = this.googleDriveDocument.loadMetadataFromDocument(this.docTestId, this.docsService);

        assertThat(documentMetadata.getBody().getContent().size(), is(183));
    }

    @Test
    public void jsonSearchForChartElementTest() throws IOException, ParseException {
        String chartTitle = "Basic BRE runtime gain vs loss performance benchmarks";
        Document documentMetadata = this.googleDriveDocument.loadMetadataFromDocument(this.docTestId, this.docsService);

        GoogleDocumentElementPosition googleDocumentElementPosition = this.googleDriveDocument.jsonSearchForElement(documentMetadata.getBody().getContent().toString(), chartTitle);

        Assert.assertNotNull(googleDocumentElementPosition);
    }

    @Test
    public void updateChartWithLinkTest() throws IOException, ParseException {
        List<BatchUpdateDocumentResponse> responses = null;

        if(GoogleDriveHelper.setPublishFile(this.driveService, docTestId)) {
            responses = this.googleDriveDocument.updateChartsWithLinks(docTestId, docsService, sheetsService, sheetTestId);
        }

        assertThat(responses.size(), is(4));
    }
}