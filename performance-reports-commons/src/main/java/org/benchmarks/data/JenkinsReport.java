package org.benchmarks.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.benchmarks.definitions.JenkinsReportFileExtension;
import org.benchmarks.definitions.JenkinsReportLocation;
import org.benchmarks.definitions.JenkinsReportVersion;
import org.benchmarks.exceptions.InvalidFileExtensionException;
import org.benchmarks.util.CsvLoader;
import org.benchmarks.util.JsonLoader;
import org.benchmarks.util.PropertiesLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public abstract class JenkinsReport {

    public JenkinsReport() {
    }

    public List getData(String filePath, JenkinsReportFileExtension fileExtension, JenkinsReportLocation jenkinsReportLocation) throws IOException {
        List<JenkinsReportRow> testResultData = new ArrayList<>();
        JSONArray dataJson;

        if (fileExtension.equals(JenkinsReportFileExtension.CSV)) {
            CsvLoader csvLoader = new CsvLoader();
            dataJson = csvLoader.getDataFromCSV(filePath, jenkinsReportLocation);
        } else if (fileExtension.equals(JenkinsReportFileExtension.JSON)) {
            JsonLoader jsonLoader = new JsonLoader();
            dataJson = jsonLoader.getDataFromJsonArray(filePath, jenkinsReportLocation);
        } else {
            throw new InvalidFileExtensionException(fileExtension);
        }

        dataJson.forEach(resultRow -> testResultData.add(parseJenkinsReportRow((JSONObject) resultRow)));

        return testResultData;
    }

    public abstract String getDataSourcePath(JenkinsReportVersion jenkinsReportVersion, PropertiesLoader propertiesLoader);

    protected abstract JenkinsReportRow parseJenkinsReportRow(JSONObject testJenkinsReportRow);
}
