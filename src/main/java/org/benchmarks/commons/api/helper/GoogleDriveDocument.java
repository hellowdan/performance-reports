package org.benchmarks.commons.api.helper;

import java.io.IOException;
import java.util.ArrayList;
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
import org.benchmarks.commons.util.PropertiesLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.benchmarks.commons.definitions.GoogleDocumentElementPosition;
import org.benchmarks.commons.util.JsonLoader;

public abstract class GoogleDriveDocument {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveDocument.class);

    public GoogleDriveDocument() {
    }

    protected abstract List<Request> getReplaceAllBody(PropertiesLoader propertiesLoader);

    public BatchUpdateDocumentResponse requestsExecute(List<Request> requests, String docNewId, Docs docService) throws IOException {
        BatchUpdateDocumentResponse response = null;

        try {
            BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest();
            response = docService.documents().batchUpdate(docNewId, body.setRequests(requests)).execute();
        } catch (IOException e) {
            LOGGER.debug("File cannot be read.", e);
            throw new IOException("File cannot be read.", e);
        }

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

    protected Spreadsheet loadMetadataFromSpreadSheet(String spreadSheetNewId, Sheets sheetsService) throws IOException {
        Spreadsheet response = null;

        try {
            Sheets.Spreadsheets.Get request = sheetsService.spreadsheets().get(spreadSheetNewId);
            response = request.execute();
        } catch (IOException e) {
            LOGGER.debug("File cannot be read.", e);
            throw new IOException("File cannot be read.", e);
        }

        return response;
    }

    protected Document loadMetadataFromDocument(String docNewId, Docs docService) throws IOException {
        Document response = null;

        try {
            Docs.Documents.Get request = docService.documents().get(docNewId);
            response = request.execute();
        } catch (IOException e) {
            LOGGER.debug("File cannot be read.", e);
            throw new IOException("File cannot be read.", e);
        }

        return response;
    }

    protected String getSpreadSheetTabUrl(String SpreadSheetID, String tabID) {
        return "https://docs.google.com/spreadsheets/d/" + SpreadSheetID + "/edit#gid=" + tabID;
    }

    protected String getSpreadSheetChartUrl(String SpreadSheetID, String chartID) {
        return "https://docs.google.com/spreadsheets/d/" + SpreadSheetID + "/pubchart?oid=" + chartID + "&format=image";
    }

    private BatchUpdateDocumentResponse executeChartRequests(String docNewId, Docs docService, String chartTitle, String tabUrl, String chartUrl) throws IOException, ParseException {
        Document documentMetadata = loadMetadataFromDocument(docNewId, docService);
        List<Request> requests = new ArrayList<>();
        GoogleDocumentElementPosition googleDocumentElementPosition = jsonSearchForChartElement(documentMetadata.getBody().getContent().toString(), chartTitle);
        if (googleDocumentElementPosition != null) {
            requests.add(getReplaceLinkBodyRequest(chartTitle, tabUrl, googleDocumentElementPosition.getStartIndex()));
            requests.add(getImageUpdateRequest(googleDocumentElementPosition.getEndIndex(), chartUrl));

            return requestsExecute(requests, docNewId, docService);
        } else {
            return null;
        }
    }

    public List<BatchUpdateDocumentResponse> updateChartWithLink(String docNewId, Docs docsService, Sheets sheetsService, String spreadSheetNewId) throws IOException, ParseException {
        Spreadsheet spreadsheetMetadata = loadMetadataFromSpreadSheet(spreadSheetNewId, sheetsService);
        List<BatchUpdateDocumentResponse> responses = new ArrayList<>();

        for (int i = 0; i < spreadsheetMetadata.getSheets().size(); i++) {
            if (spreadsheetMetadata.getSheets().get(i).getCharts() != null) {
                for (int j = 0; j < spreadsheetMetadata.getSheets().get(i).getCharts().size(); j++) {
                    String chartID = spreadsheetMetadata.getSheets().get(i).getCharts().get(j).getChartId().toString();
                    String chartUrl = getSpreadSheetChartUrl(spreadSheetNewId, chartID);
                    String chartTitle = spreadsheetMetadata.getSheets().get(i).getCharts().get(j).getSpec().getTitle();
                    String tabID = spreadsheetMetadata.getSheets().get(i).getProperties().getSheetId().toString();
                    String tabUrl = this.getSpreadSheetTabUrl(spreadSheetNewId, tabID);

                    responses.add(executeChartRequests(docNewId, docsService, chartTitle, tabUrl, chartUrl));
                }
            }
        }

        return responses;
    }

    protected GoogleDocumentElementPosition jsonSearchForChartElement(String json, String chartTitle) throws ParseException {
        GoogleDocumentElementPosition found = null;

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

                        found = new GoogleDocumentElementPosition(startIndex, endIndex);
                        break;
                    }
                }

                if (found != null) {
                    break;
                }
            }
        } catch (ParseException e) {
            LOGGER.debug("File cannot be parsed.", e);
            throw new ParseException(e.getErrorType());
        }

        return found;
    }
}
