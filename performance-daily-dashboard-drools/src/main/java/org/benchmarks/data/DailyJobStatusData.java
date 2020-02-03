package org.benchmarks.data;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.benchmarks.definitions.DailyBenchmarkConfig;
import org.benchmarks.definitions.SourceStatusColumns;
import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.model.DailyBenchmarkEntity;
import org.benchmarks.model.DailyJobStatusEntity;
import org.benchmarks.util.JsonLoader;
import org.json.simple.JSONObject;

public class DailyJobStatusData {

    private DailyBenchmarkEntity getBenchmarkEntity(DailyBenchmarkRow testResultData, String product) {
        return new DailyBenchmarkEntity(
                testResultData.getBenchmark(),
                testResultData.getName(),
                product,
                testResultData.getScore()
        );
    }

    public List<DailyBenchmarkEntity> getDroolsBenchmarkData(SourceFileExtension sourceFileExtension, SourceFileLocation jenkinsReportLocation, DailyBenchmarkConfig config) throws IOException {
        DailyBenchmarkData dailyBenchmarkData = new DailyBenchmarkData(config);
        List<DailyBenchmarkRow> dailyBenchmarkRows = dailyBenchmarkData.getData(config.getLastSuccessfulBuildCsvPath(), sourceFileExtension, jenkinsReportLocation);
        List<DailyBenchmarkEntity> dailyBenchmarkEntities = new ArrayList();

        for (int i = 0; i < dailyBenchmarkRows.size(); i++) {
            dailyBenchmarkEntities.add(getBenchmarkEntity(dailyBenchmarkRows.get(i), config.getProduct()));
        }

        return dailyBenchmarkEntities;
    }

    public DailyJobStatusEntity getDroolsStatusData(SourceFileLocation jenkinsReportLocation, DailyBenchmarkConfig config) throws IOException {
        JsonLoader jsonLoader = new JsonLoader();
        JSONObject dataJsonLastBuild = jsonLoader.getDataFromJsonObject(config.getLastBuildApiPath(), jenkinsReportLocation);
        JSONObject dataJsonJob = jsonLoader.getDataFromJsonObject(config.getApiPath(), jenkinsReportLocation);

        DailyJobStatusRow resultRow = parseJenkinsStatus(dataJsonLastBuild, dataJsonJob, config.getProduct());

        DailyJobStatusEntity dailyJobStatusEntity = new DailyJobStatusEntity(resultRow);

        return dailyJobStatusEntity;
    }

    protected DailyJobStatusRow parseJenkinsStatus(JSONObject dataJsonLastBuild, JSONObject dataJsonJob, String product) {
        DailyJobStatusRow droolsJenkinsReportRow = new DailyJobStatusRow();

        droolsJenkinsReportRow.setProduct(product);

        if (dataJsonLastBuild.get(SourceStatusColumns.JOB.getColumn()) != null) {
            String displayName = dataJsonLastBuild.get(SourceStatusColumns.JOB.getColumn()).toString();
            if (displayName.indexOf("#") > 0) {
                displayName = displayName.substring(0, displayName.indexOf("#")).trim();
            }
            droolsJenkinsReportRow.setBenchmark(displayName);
        }
        if (dataJsonLastBuild.get(SourceStatusColumns.URL.getColumn()) != null) {
            droolsJenkinsReportRow.setUrl(dataJsonLastBuild.get(SourceStatusColumns.URL.getColumn()).toString());
        }
        if (dataJsonJob.get(SourceStatusColumns.LAST_BUILD.getColumn()) != null) {
            JSONObject resultsObject = (JSONObject) dataJsonJob.get(SourceStatusColumns.LAST_BUILD.getColumn());
            String number = resultsObject.get(SourceStatusColumns.BUILD_NUMBER.getColumn()).toString();
            droolsJenkinsReportRow.setLastBuild(number);
        }
        if (dataJsonJob.get(SourceStatusColumns.LAST_SUCCESSFUL_BUILD.getColumn()) != null) {
            JSONObject resultsObject = (JSONObject) dataJsonJob.get(SourceStatusColumns.LAST_SUCCESSFUL_BUILD.getColumn());
            String number = resultsObject.get(SourceStatusColumns.BUILD_NUMBER.getColumn()).toString();
            droolsJenkinsReportRow.setLastSuccessfulBuild(number);
        }
        if (dataJsonJob.get(SourceStatusColumns.LAST_FAILED_BUILD.getColumn()) != null) {
            JSONObject resultsObject = (JSONObject) dataJsonJob.get(SourceStatusColumns.LAST_FAILED_BUILD.getColumn());
            String number = resultsObject.get(SourceStatusColumns.BUILD_NUMBER.getColumn()).toString();
            droolsJenkinsReportRow.setLastFailedBuild(number);
        }
        if (dataJsonLastBuild.get(SourceStatusColumns.LAST_BUILD_STATUS.getColumn()) != null) {
            droolsJenkinsReportRow.setLastBuildStatus(dataJsonLastBuild.get(SourceStatusColumns.LAST_BUILD_STATUS.getColumn()).toString());
        }
        if (dataJsonLastBuild.get(SourceStatusColumns.LAST_BUILD_TIMESTAMP.getColumn()) != null) {
            Long lastBuildDateOfExecution = Long.parseLong(dataJsonLastBuild.get(SourceStatusColumns.LAST_BUILD_TIMESTAMP.getColumn()).toString());
            droolsJenkinsReportRow.setLastBuildDateOfExecution(new Timestamp(lastBuildDateOfExecution));
        }

        return droolsJenkinsReportRow;
    }
}
