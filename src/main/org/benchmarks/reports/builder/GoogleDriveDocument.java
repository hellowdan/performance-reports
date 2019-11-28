package org.benchmarks.reports.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.BatchUpdateDocumentRequest;
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
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import org.benchmarks.reports.data.DocElementPosition;
import org.benchmarks.reports.util.JsonLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public abstract class GoogleDriveDocument {

    protected String docNewId;
    protected String spreadSheetNewId;
    protected Docs docService;
    protected Sheets sheetService;
    protected Spreadsheet spreadsheetMetadata;
    protected Document documentMetadata;

    public GoogleDriveDocument(String docNewId, String spreadSheetNewId, Docs docService, Sheets sheetService) {
        this.docNewId = docNewId;
        this.spreadSheetNewId = spreadSheetNewId;
        this.docService = docService;
        this.sheetService = sheetService;
    }

    protected abstract List<Request> getReplaceAllBody();

    public BatchUpdateDocumentResponse requestsExecute(List<Request> requests) throws IOException {
        BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest();
        BatchUpdateDocumentResponse response = this.docService.documents().batchUpdate(this.docNewId, body.setRequests(requests)).execute();

        return response;
    }

    protected BatchUpdateDocumentResponse singleRequestExecute(Request request) throws IOException {
        BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest();
        BatchUpdateDocumentResponse response = this.docService.documents().batchUpdate(this.docNewId, body.setRequests(Arrays.asList(request))).execute();

        return response;
    }

    protected Request getReplaceTextBodyRequest(String placeHolder, String newValue) {
        Request request = new Request().
                setReplaceAllText(new ReplaceAllTextRequest().
                        setContainsText(new SubstringMatchCriteria().
                                setText(placeHolder).
                                setMatchCase(true)).
                        setReplaceText(newValue));

        return request;
    }

    protected Request getReplaceLinkBodyRequest(String title, String linkValue, String IndexOf) {
        Request request = new Request()
                .setUpdateTextStyle(new UpdateTextStyleRequest()
                                            .setRange(new Range()
                                                              .setStartIndex(Integer.parseInt(IndexOf))
                                                              .setEndIndex(Integer.parseInt(IndexOf) + title.length()))
                                            .setTextStyle(new TextStyle()
                                                                  .setLink(new Link()
                                                                                   .setUrl(linkValue)))
                                            .setFields("link"));

        return request;
    }

    protected Request getImageUpdateRequest(String locationIndex, String Uri) {
        Request request = new Request().
                setInsertInlineImage(new InsertInlineImageRequest()
                                             .setUri(Uri)
                                             .setLocation(new Location().setIndex(Integer.parseInt(locationIndex)))
                );

        return request;
    }

    protected void loadMetadataFromSpreadSheet() throws IOException {
        Sheets.Spreadsheets.Get request = this.sheetService.spreadsheets().get(this.spreadSheetNewId);
        Spreadsheet response = request.execute();

        this.spreadsheetMetadata = response;
    }

    protected void loadMetadataFromDocument() throws IOException {
        Docs.Documents.Get request = this.docService.documents().get(this.docNewId);
        Document response = request.execute();

        this.documentMetadata = response;
    }

    protected String getSpreadSheetTabUrl(String SpreadSheetID, String tabID) {
        return "https://docs.google.com/spreadsheets/d/" + SpreadSheetID + "/edit#gid=" + tabID;
    }

    protected String getSpreadSheetChartUrl(String SpreadSheetID, String chartID) {
        return "https://docs.google.com/spreadsheets/d/" + SpreadSheetID + "/pubchart?oid=" + chartID + "&format=image";
    }

    protected BatchUpdateDocumentResponse executeChartRequests(String chartTitle, String tabUrl, String chartUrl) throws IOException {
        loadMetadataFromDocument();
        List<Request> requests = new ArrayList<>();
        DocElementPosition docElementPosition = jsonSearchForChartElement(this.documentMetadata.getBody().getContent().toString(), chartTitle);
        if (docElementPosition != null) {
            requests.add(getReplaceLinkBodyRequest(chartTitle, tabUrl, docElementPosition.getStartIndex()));
            requests.add(getImageUpdateRequest(docElementPosition.getEndIndex(), chartUrl));

            return requestsExecute(requests);
        } else {
            return null;
        }
    }

    public List<BatchUpdateDocumentResponse> updateChartWithLink() throws IOException {
        loadMetadataFromSpreadSheet();
        List<BatchUpdateDocumentResponse> responses = new ArrayList<>();

        for (int i = 0; i < spreadsheetMetadata.getSheets().size(); i++) {
            if (spreadsheetMetadata.getSheets().get(i).getCharts() != null) {
                for (int j = 0; j < spreadsheetMetadata.getSheets().get(i).getCharts().size(); j++) {
                    String chartID = spreadsheetMetadata.getSheets().get(i).getCharts().get(j).getChartId().toString();
                    String chartUrl = getSpreadSheetChartUrl(this.spreadSheetNewId, chartID);
                    String chartTitle = spreadsheetMetadata.getSheets().get(i).getCharts().get(j).getSpec().getTitle();
                    String tabID = spreadsheetMetadata.getSheets().get(i).getProperties().getSheetId().toString();
                    String tabUrl = this.getSpreadSheetTabUrl(this.spreadSheetNewId, tabID);

                    responses.add(executeChartRequests(chartTitle, tabUrl, chartUrl));
                }
            }
        }

        return responses;
    }

    public DocElementPosition jsonSearchForChartElement(String json, String chartTitle) {
        DocElementPosition found = null;

        if (!JsonLoader.isJSONValid(json)) {
            return null;
        }

        try {

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(json);
            JSONArray jsonArray = (JSONArray) obj;

            for (int i = 0; i < jsonArray.size(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                Set keys = jsonObject.keySet();
                Iterator a = keys.iterator();
                while (a.hasNext()) {
                    String key = (String) a.next();
                    String value = jsonObject.get(key).toString();

                    if (value.contains(chartTitle)) {
                        JSONObject jsonObjectParagraph = (JSONObject) jsonObject.get(key);
                        JSONArray jsonArrayElement = (JSONArray) jsonObjectParagraph.get("elements");
                        JSONObject jsonObjectElement = (JSONObject) jsonArrayElement.get(0);
                        String startIndex = jsonObjectElement.get("startIndex").toString();
                        String endIndex = jsonObjectElement.get("endIndex").toString();

                        found = new DocElementPosition(startIndex, endIndex);
                        break;
                    }
                }

                if (found != null) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return found;
    }
}
