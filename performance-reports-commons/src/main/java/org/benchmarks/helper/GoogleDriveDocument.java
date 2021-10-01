package org.benchmarks.helper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.BatchUpdateDocumentRequest;
import com.google.api.services.docs.v1.model.BatchUpdateDocumentResponse;
import com.google.api.services.docs.v1.model.Color;
import com.google.api.services.docs.v1.model.Document;
import com.google.api.services.docs.v1.model.InsertInlineImageRequest;
import com.google.api.services.docs.v1.model.Link;
import com.google.api.services.docs.v1.model.Location;
import com.google.api.services.docs.v1.model.OptionalColor;
import com.google.api.services.docs.v1.model.Range;
import com.google.api.services.docs.v1.model.ReplaceAllTextRequest;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.docs.v1.model.RgbColor;
import com.google.api.services.docs.v1.model.StructuralElement;
import com.google.api.services.docs.v1.model.SubstringMatchCriteria;
import com.google.api.services.docs.v1.model.TableCell;
import com.google.api.services.docs.v1.model.TableRow;
import com.google.api.services.docs.v1.model.TextStyle;
import com.google.api.services.docs.v1.model.UpdateTextStyleRequest;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.benchmarks.definitions.GoogleDocumentElementPosition;
import org.benchmarks.exceptions.FileCannotBeParsedException;
import org.benchmarks.exceptions.FileCannotBeReadException;
import org.benchmarks.util.JsonLoader;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class GoogleDriveDocument {

    private static final String GOOGLE_SPREADSHEET_URL_PREFIX = "https://docs.google.com/spreadsheets/d/";

    public GoogleDriveDocument() {
    }

    protected abstract List<Request> getReplaceAllBody(ReportProperties reportProperties);

    public List<BatchUpdateDocumentResponse> processRequests(List<Request> requests, String docNewId, Docs docService) throws IOException {
        List<BatchUpdateDocumentResponse> responses = new ArrayList<>();

        try {
            if (requests.size() > 0) {
                responses.add(requestsBatchExecution(requests, docNewId, docService));
            }
        } catch (IOException e) {
            throw new FileCannotBeReadException(e);
        }

        return responses;
    }

    public BatchUpdateDocumentResponse requestsBatchExecution(List<Request> requests, String docNewId, Docs docService) throws IOException {
        BatchUpdateDocumentResponse response = null;

        if (requests.size() > 0) {
            try {
                BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest();
                response = docService.documents().batchUpdate(docNewId, body.setRequests(requests)).execute();
            } catch (IOException e) {
                throw new FileCannotBeReadException(e);
            }
        }

        return response;
    }

    protected Request getReplaceTextBodyRequest(String placeHolder, String newValue) {
        return new Request()
                .setReplaceAllText(new ReplaceAllTextRequest().
                                           setContainsText(new SubstringMatchCriteria().
                                                                   setText(placeHolder).
                                                                   setMatchCase(true)).
                                           setReplaceText(newValue));
    }

    protected Request getFormatTextColorRequest(String text, RgbColor color, String indexOf) {
        return new Request()
                .setUpdateTextStyle(new UpdateTextStyleRequest()
                                            .setRange(new Range()
                                                              .setStartIndex(Integer.parseInt(indexOf))
                                                              .setEndIndex(Integer.parseInt(indexOf) + text.length()+1))
                                            .setTextStyle(new TextStyle()
                                                                  .setForegroundColor(new OptionalColor()
                                                                                              .setColor(new Color().setRgbColor(color))))
                                            .setFields("foregroundColor"));
    }

    protected Request getReplaceLinkBodyRequest(String title, String linkValue, String indexOf) {
        return new Request()
                .setUpdateTextStyle(new UpdateTextStyleRequest()
                                            .setRange(new Range()
                                                              .setStartIndex(Integer.parseInt(indexOf))
                                                              .setEndIndex(Integer.parseInt(indexOf) + title.length()))
                                            .setTextStyle(new TextStyle()
                                                                  .setLink(new Link()
                                                                                   .setUrl(linkValue)))
                                            .setFields("link"));
    }

    protected Request getImageUpdateRequest(String locationIndex, String uri) {
        return new Request().
                setInsertInlineImage(new InsertInlineImageRequest()
                                             .setUri(uri)
                                             .setLocation(new Location()
                                                                  .setIndex(Integer.parseInt(locationIndex))));
    }

    protected Spreadsheet loadMetadataFromSpreadSheet(String spreadSheetNewId, Sheets sheetsService) throws IOException {
        Spreadsheet response;

        try {
            Sheets.Spreadsheets.Get request = sheetsService.spreadsheets().get(spreadSheetNewId);
            response = request.execute();
        } catch (IOException e) {
            throw new FileCannotBeReadException(e);
        }

        return response;
    }

    protected Document loadMetadataFromDocument(String docNewId, Docs docService) throws IOException {
        Document response = null;

        try {
            Docs.Documents.Get request = docService.documents().get(docNewId);
            response = request.execute();
        } catch (IOException e) {
            throw new FileCannotBeReadException(e);
        }

        return response;
    }

    protected String getSpreadSheetTabUrl(String spreadSheetID, String tabID) {
        return GOOGLE_SPREADSHEET_URL_PREFIX + spreadSheetID + "/edit#gid=" + tabID;
    }

    protected String getSpreadSheetChartUrl(String spreadSheetID, String chartID) {
        return GOOGLE_SPREADSHEET_URL_PREFIX + spreadSheetID + "/pubchart?oid=" + chartID + "&format=image";
    }

    private List<BatchUpdateDocumentResponse> executeChartRequests(String docNewId, Docs docService, String chartTitle, String tabUrl, String chartUrl) throws IOException {
        List<BatchUpdateDocumentResponse> results = new ArrayList<>();

        try {
            Document documentMetadata = loadMetadataFromDocument(docNewId, docService);
            List<Request> requests = new ArrayList<>();
            GoogleDocumentElementPosition googleDocumentElementPosition = jsonSearchForElement(documentMetadata.getBody().getContent().toString(), chartTitle);
            if (googleDocumentElementPosition != null) {
                requests.add(getReplaceLinkBodyRequest(chartTitle, tabUrl, googleDocumentElementPosition.getStartIndex()));
                requests.add(getImageUpdateRequest(googleDocumentElementPosition.getEndIndex(), chartUrl));

                results = processRequests(requests, docNewId, docService);
            }
        } catch (IOException e) {
            throw new FileCannotBeReadException(e);
        }

        return results;
    }

    public ValueRange getValueRangeFromSheet(Sheets sheetsService, String spreadSheetID, String sheetName, String rangeCells) throws IOException {
        String range = sheetName + "!" + rangeCells;
        String valueRenderOption = "FORMATTED_VALUE";
        String dateTimeRenderOption = "SERIAL_NUMBER";
        ValueRange response = null;

        try {
            Sheets.Spreadsheets.Values.Get request = sheetsService.spreadsheets().values().get(spreadSheetID, range);
            request.setValueRenderOption(valueRenderOption);
            request.setDateTimeRenderOption(dateTimeRenderOption);

            response = request.execute();
        } catch (IOException e) {
            throw new FileCannotBeReadException(e);
        }

        return response;
    }

    public List<Request> getTableUpdateRequest(ValueRange jsonTableFromSheets, String cellFormat, String tableName, int startingCol, int startingRow) throws IOException {
        int row = startingRow + jsonTableFromSheets.getValues().size() - 1;
        List<Request> requests = new ArrayList<>();

        try {
            for (int i = 0; i < jsonTableFromSheets.getValues().size(); i++) {
                int col = startingCol;
                for (int j = 0; j < jsonTableFromSheets.getValues().get(i).size(); j++) {
                    String cellToFind = String.format(cellFormat, tableName, col, row);
                    requests.add(this.getReplaceTextBodyRequest(cellToFind, jsonTableFromSheets.getValues().get(i).get(j).toString()));
                    col++;
                }
                row--;
            }
        } catch (Exception e) {
            throw new FileCannotBeReadException(e);
        }

        return requests;
    }

    public List<Request> getTableUpdateCellColorRequests(String docNewId, Docs docService, ValueRange jsonTableFromSheets) throws IOException {
        List<Request> requests = new ArrayList<>();
        Document documentMetadata = loadMetadataFromDocument(docNewId, docService);

        try {
            for (int i = 0; i < jsonTableFromSheets.getValues().size(); i++) {
                for (int j = 4; j < jsonTableFromSheets.getValues().get(i).size(); j++) {
                    String stringValue = jsonTableFromSheets.getValues().get(i).get(j).toString();

                    BigDecimal value = new BigDecimal(stringValue.trim().replace("%", "")).divide(BigDecimal.valueOf(100));
                    String stringValueWithLineBreak = stringValue + "\n";

                    if (value.doubleValue() > 0.05) {
                        GoogleDocumentElementPosition googleDocumentElementPosition = searchForTableCellContent(documentMetadata.getBody().getContent(), stringValue);
                        if (googleDocumentElementPosition == null) {
                            googleDocumentElementPosition = searchForTableCellContent(documentMetadata.getBody().getContent(), stringValueWithLineBreak);

                            if (googleDocumentElementPosition != null) {
                                requests.add(this.getFormatTextColorRequest(stringValueWithLineBreak, new RgbColor().setBlue(0.11F).setGreen(0.46F).setRed(0.22F), googleDocumentElementPosition.getStartIndex()));
                            }
                        } else {
                            requests.add(this.getFormatTextColorRequest(stringValue, new RgbColor().setBlue(0.11F).setGreen(0.46F).setRed(0.22F), googleDocumentElementPosition.getStartIndex()));
                        }
                    } else if (value.doubleValue() < -0.05) {
                        GoogleDocumentElementPosition googleDocumentElementPosition = searchForTableCellContent(documentMetadata.getBody().getContent(), stringValue);

                        if (googleDocumentElementPosition == null) {
                            googleDocumentElementPosition = searchForTableCellContent(documentMetadata.getBody().getContent(), stringValueWithLineBreak);

                            if (googleDocumentElementPosition != null) {
                                requests.add(this.getFormatTextColorRequest(stringValue, new RgbColor().setBlue(0.0F).setGreen(0.0F).setRed(1.0F), googleDocumentElementPosition.getStartIndex()));
                            }
                        } else {
                            requests.add(this.getFormatTextColorRequest(stringValue, new RgbColor().setBlue(0.0F).setGreen(0.0F).setRed(1.0F), googleDocumentElementPosition.getStartIndex()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new FileCannotBeReadException(e);
        }

        return requests;
    }

    public List<BatchUpdateDocumentResponse> updateChartsWithLinks(String docNewId, Docs docsService, Sheets sheetsService, String spreadSheetNewId) throws IOException {
        List<BatchUpdateDocumentResponse> responses = new ArrayList<>();

        try {
            Spreadsheet spreadsheetMetadata = loadMetadataFromSpreadSheet(spreadSheetNewId, sheetsService);
            for (int i = 0; i < spreadsheetMetadata.getSheets().size(); i++) {
                if (spreadsheetMetadata.getSheets().get(i).getCharts() != null) {
                    for (int j = 0; j < spreadsheetMetadata.getSheets().get(i).getCharts().size(); j++) {
                        String chartID = spreadsheetMetadata.getSheets().get(i).getCharts().get(j).getChartId().toString();
                        String chartUrl = getSpreadSheetChartUrl(spreadSheetNewId, chartID);
                        String chartTitle = spreadsheetMetadata.getSheets().get(i).getCharts().get(j).getSpec().getTitle();
                        String tabID = spreadsheetMetadata.getSheets().get(i).getProperties().getSheetId().toString();
                        String tabUrl = this.getSpreadSheetTabUrl(spreadSheetNewId, tabID);

                        responses = executeChartRequests(docNewId, docsService, chartTitle, tabUrl, chartUrl);
                    }
                }
            }
        } catch (IOException e) {
            throw new FileCannotBeReadException(e);
        }

        return responses;
    }

    protected GoogleDocumentElementPosition searchForTableCellContent(List<StructuralElement> elements, String textToSearch) {
        GoogleDocumentElementPosition found = null;

        for (StructuralElement element : elements) {
            if (element.getTable() != null) {
                for (TableRow row : element.getTable().getTableRows()) {
                    for (TableCell cell : row.getTableCells()) {
                        if (textToSearch.equals(cell.getContent().get(0).getParagraph().getElements().get(0).getTextRun().getContent())) {
                            String startIndex = cell.getStartIndex().toString();
                            String endIndex = cell.getEndIndex().toString();

                            found = new GoogleDocumentElementPosition(startIndex, endIndex);
                            break;
                        }
                    }
                }
            }
        }
        return found;
    }

    protected GoogleDocumentElementPosition jsonSearchForElement(String json, String elementTitle) {
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

                    if (value.contains(elementTitle)) {
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
            throw new FileCannotBeParsedException(e.toString());
        }

        return found;
    }
}


