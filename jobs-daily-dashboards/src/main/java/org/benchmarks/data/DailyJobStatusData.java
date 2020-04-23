package org.benchmarks.data;

import java.io.IOException;
import java.sql.Timestamp;

import org.benchmarks.definitions.DailyJobConfig;
import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.definitions.SourceStatusColumns;
import org.benchmarks.model.DailyJobStatusEntity;
import org.benchmarks.util.JsonLoader;
import org.json.simple.JSONObject;

public class DailyJobStatusData {

    private String benchmark;
    private String lastBuildApiPath;
    private String apiPath;
    private String product;
    private String branch;

    public DailyJobStatusData(DailyJobConfig config) {
        super();

        this.benchmark = config.getBenchmark();
        this.lastBuildApiPath = config.getLastBuildApiPath();
        this.apiPath = config.getApiPath();
        this.product = config.getProduct();
        this.branch = config.getBranch();
    }

    public DailyJobStatusEntity getDroolsStatusData() throws IOException {
        JsonLoader jsonLoader = new JsonLoader();
        JSONObject dataJsonLastBuild = jsonLoader.getDataFromJsonObject(this.lastBuildApiPath, SourceFileLocation.WEB);
        JSONObject dataJsonJob = jsonLoader.getDataFromJsonObject(this.apiPath, SourceFileLocation.WEB);

        DailyJobStatusRow resultRow = parseJenkinsStatus(dataJsonLastBuild, dataJsonJob);

        DailyJobStatusEntity dailyJobStatusEntity = new DailyJobStatusEntity(resultRow);

        return dailyJobStatusEntity;
    }

    protected DailyJobStatusRow parseJenkinsStatus(JSONObject dataJsonLastBuild, JSONObject dataJsonJob) {
        DailyJobStatusRow droolsJenkinsReportRow = new DailyJobStatusRow();

        droolsJenkinsReportRow.setBenchmark(this.benchmark);
        droolsJenkinsReportRow.setProduct(this.product);
        droolsJenkinsReportRow.setBranch(this.branch);

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
        if (dataJsonLastBuild.get(SourceStatusColumns.LAST_BUILD_DURATION.getColumn()) != null) {
            Double lastBuildDuration = Double.parseDouble(dataJsonLastBuild.get(SourceStatusColumns.LAST_BUILD_DURATION.getColumn()).toString());
            droolsJenkinsReportRow.setLastBuildDuration(lastBuildDuration);
        }

        return droolsJenkinsReportRow;
    }
}
