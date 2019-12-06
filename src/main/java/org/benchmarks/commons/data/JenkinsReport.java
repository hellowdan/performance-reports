package org.benchmarks.commons.data;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

import org.benchmarks.commons.definitions.JenkinsReportFileExtension;
import org.benchmarks.commons.util.CsvLoader;
import org.benchmarks.commons.util.JsonLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.commons.util.PropertiesLoader;

public abstract class JenkinsReport {

    public JenkinsReport() {
    }

    public List<JenkinsReportRow> getData(String filePath, JenkinsReportFileExtension fileExtension, JenkinsReportLocation jenkinsReportLocation) throws IOException {
        List<JenkinsReportRow> droolsTestResultData = new ArrayList<>();
        JSONArray dataJson;

        if (fileExtension.equals(JenkinsReportFileExtension.CSV)) {
            CsvLoader csvLoader = new CsvLoader();
            dataJson = csvLoader.getDataFromCSV(filePath, jenkinsReportLocation);
        } else if (fileExtension.equals(JenkinsReportFileExtension.JSON)) {
            JsonLoader jsonLoader = new JsonLoader();
            dataJson = jsonLoader.getDataFromJson(filePath, jenkinsReportLocation);
        } else {
            throw new InvalidPathException("Invalid file extension: ", filePath + " - " + fileExtension);
        }

        dataJson.forEach(resultRow -> droolsTestResultData.add(parseJenkinsReportRow((JSONObject) resultRow)));

        return droolsTestResultData;
    }

    public abstract String getDataSourcePath(JenkinsReportVersion jenkinsReportVersion, PropertiesLoader propertiesLoader);

    protected abstract JenkinsReportRow parseJenkinsReportRow(JSONObject testJenkinsReportRow);
}
