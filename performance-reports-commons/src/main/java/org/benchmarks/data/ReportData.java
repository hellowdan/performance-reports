package org.benchmarks.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.exceptions.InvalidFileExtensionException;
import org.benchmarks.util.CsvLoader;
import org.benchmarks.util.JsonLoader;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public abstract class ReportData {

    public ReportData() {
    }

    public List getData(String filePath, SourceFileExtension fileExtension, SourceFileLocation sourceFileLocation) throws IOException {
        List<ReportRow> testResultData = new ArrayList<>();
        JSONArray dataJson;

        if (fileExtension.equals(SourceFileExtension.CSV)) {
            CsvLoader csvLoader = new CsvLoader();
            dataJson = csvLoader.getDataFromCSV(filePath, sourceFileLocation);
        } else if (fileExtension.equals(SourceFileExtension.JSON)) {
            JsonLoader jsonLoader = new JsonLoader();
            dataJson = jsonLoader.getDataFromJsonArray(filePath, sourceFileLocation);
        } else {
            throw new InvalidFileExtensionException(fileExtension);
        }

        dataJson.forEach(resultRow -> testResultData.add(parseJenkinsReportRow((JSONObject) resultRow)));

        return testResultData;
    }

    public abstract String getDataSourcePath(StaticVersion staticVersion, ReportProperties reportProperties);

    protected abstract ReportRow parseJenkinsReportRow(JSONObject testJenkinsReportRow);
}
