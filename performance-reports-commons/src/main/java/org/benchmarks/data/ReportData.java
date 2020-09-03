package org.benchmarks.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.exceptions.ExceptionsConstants;
import org.benchmarks.exceptions.InvalidFileExtensionException;
import org.benchmarks.util.CsvLoader;
import org.benchmarks.util.JsonLoader;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ReportData {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportData.class);

    public ReportData() {
    }

    public List getData(String filePath, SourceFileExtension fileExtension, SourceFileLocation sourceFileLocation) {
        List<ReportRow> reportRows = new ArrayList<>();
        JSONArray dataJson;

        try {
            if (fileExtension.equals(SourceFileExtension.CSV)) {
                CsvLoader csvLoader = new CsvLoader();
                dataJson = csvLoader.getDataFromCSV(filePath, sourceFileLocation);
            } else if (fileExtension.equals(SourceFileExtension.JSON)) {
                JsonLoader jsonLoader = new JsonLoader();
                dataJson = jsonLoader.getDataFromJsonArray(filePath, sourceFileLocation);
            } else {
                throw new InvalidFileExtensionException(fileExtension);
            }

            dataJson.forEach(resultRow -> reportRows.add(parseReportRow((JSONObject) resultRow)));
        } catch (IOException e) {
            LOGGER.debug(String.format(ExceptionsConstants.FILE_NOT_FOUND_PARAM, e));
        }

        return reportRows;
    }

    public abstract String getDataSourcePath(StaticVersion staticVersion, ReportProperties reportProperties);

    protected abstract ReportRow parseReportRow(JSONObject testJenkinsReportRow);
}
