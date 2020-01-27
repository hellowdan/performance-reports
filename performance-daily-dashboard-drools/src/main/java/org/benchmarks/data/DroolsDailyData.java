package org.benchmarks.data;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.benchmarks.definitions.DroolsDailyBenchmarkConfig;
import org.benchmarks.definitions.DroolsStatusColumns;
import org.benchmarks.definitions.JenkinsReportFileExtension;
import org.benchmarks.definitions.JenkinsReportLocation;
import org.benchmarks.model.DroolsJenkinsDailyReportRowEntity;
import org.benchmarks.model.DroolsJenkinsDailyStatusEntity;
import org.benchmarks.util.JsonLoader;
import org.json.simple.JSONObject;

public class DroolsDailyData {

    private DroolsJenkinsDailyReportRowEntity getDroolsJenkinsReportRowDB(DroolsJenkinsDailyReportRow testResultData) {
        return new DroolsJenkinsDailyReportRowEntity(
                testResultData.getBenchmark(),
                testResultData.getName(),
                testResultData.getScore()
        );
    }

    public List<DroolsJenkinsDailyReportRowEntity> getDroolsBenchmarkData(JenkinsReportFileExtension jenkinsReportFileExtension, JenkinsReportLocation jenkinsReportLocation, DroolsDailyBenchmarkConfig config) throws IOException {
        DroolsBenchmarkJenkinsReport droolsBenchmarkJenkinsReport = new DroolsBenchmarkJenkinsReport(config);
        List<DroolsJenkinsDailyReportRow> testResultData = droolsBenchmarkJenkinsReport.getData(config.getLastSuccessfulBuildCsvPath(), jenkinsReportFileExtension, jenkinsReportLocation);
        List<DroolsJenkinsDailyReportRowEntity> droolsJenkinsDailyReportRowEntities = new ArrayList();

        for (int i = 0; i < testResultData.size(); i++) {
            droolsJenkinsDailyReportRowEntities.add(getDroolsJenkinsReportRowDB(testResultData.get(i)));
        }

        return droolsJenkinsDailyReportRowEntities;
    }

    public DroolsJenkinsDailyStatusEntity getDroolsStatusData(JenkinsReportLocation jenkinsReportLocation, DroolsDailyBenchmarkConfig config) throws IOException {
        JsonLoader jsonLoader = new JsonLoader();
        JSONObject dataJsonLastBuild = jsonLoader.getDataFromJsonObject(config.getLastBuildApiPath(), jenkinsReportLocation);
        JSONObject dataJsonJob = jsonLoader.getDataFromJsonObject(config.getApiPath(), jenkinsReportLocation);

        DroolsJenkinsDailyStatusRow resultRow = parseJenkinsStatus(dataJsonLastBuild, dataJsonJob);

        DroolsJenkinsDailyStatusEntity droolsJenkinsDailyStatusEntity = new DroolsJenkinsDailyStatusEntity(resultRow);

        return droolsJenkinsDailyStatusEntity;
    }

    protected DroolsJenkinsDailyStatusRow parseJenkinsStatus(JSONObject dataJsonLastBuild, JSONObject dataJsonJob) {
        DroolsJenkinsDailyStatusRow droolsJenkinsReportRow = new DroolsJenkinsDailyStatusRow();

        if (dataJsonLastBuild.get(DroolsStatusColumns.JOB.getColumn()) != null) {
            String displayName = dataJsonLastBuild.get(DroolsStatusColumns.JOB.getColumn()).toString();
            if (displayName.indexOf("#") > 0) {
                displayName = displayName.substring(0, displayName.indexOf("#")).trim();
            }
            droolsJenkinsReportRow.setBenchmark(displayName);
        }
        if (dataJsonLastBuild.get(DroolsStatusColumns.URL.getColumn()) != null) {
            droolsJenkinsReportRow.setUrl(dataJsonLastBuild.get(DroolsStatusColumns.URL.getColumn()).toString());
        }
        if (dataJsonJob.get(DroolsStatusColumns.LAST_BUILD.getColumn()) != null) {
            JSONObject resultsObject = (JSONObject) dataJsonJob.get(DroolsStatusColumns.LAST_BUILD.getColumn());
            String number = resultsObject.get(DroolsStatusColumns.BUILD_NUMBER.getColumn()).toString();
            droolsJenkinsReportRow.setLastBuild(number);
        }
        if (dataJsonJob.get(DroolsStatusColumns.LAST_SUCCESSFUL_BUILD.getColumn()) != null) {
            JSONObject resultsObject = (JSONObject) dataJsonJob.get(DroolsStatusColumns.LAST_SUCCESSFUL_BUILD.getColumn());
            String number = resultsObject.get(DroolsStatusColumns.BUILD_NUMBER.getColumn()).toString();
            droolsJenkinsReportRow.setLastSuccessfulBuild(number);
        }
        if (dataJsonJob.get(DroolsStatusColumns.LAST_FAILED_BUILD.getColumn()) != null) {
            JSONObject resultsObject = (JSONObject) dataJsonJob.get(DroolsStatusColumns.LAST_FAILED_BUILD.getColumn());
            String number = resultsObject.get(DroolsStatusColumns.BUILD_NUMBER.getColumn()).toString();
            droolsJenkinsReportRow.setLastFailedBuild(number);
        }
        if (dataJsonLastBuild.get(DroolsStatusColumns.LAST_BUILD_STATUS.getColumn()) != null) {
            droolsJenkinsReportRow.setLastBuildStatus(dataJsonLastBuild.get(DroolsStatusColumns.LAST_BUILD_STATUS.getColumn()).toString());
        }
        if (dataJsonLastBuild.get(DroolsStatusColumns.LAST_BUILD_TIMESTAMP.getColumn()) != null) {
            Long lastBuildDateOfExecution = Long.parseLong(dataJsonLastBuild.get(DroolsStatusColumns.LAST_BUILD_TIMESTAMP.getColumn()).toString());
            droolsJenkinsReportRow.setLastBuildDateOfExecution(new Timestamp(lastBuildDateOfExecution));
        }

        return droolsJenkinsReportRow;
    }
}
