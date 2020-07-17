package org.benchmarks.data;

import java.io.IOException;
import java.sql.Timestamp;

import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.definitions.SourceStatusColumns;
import org.benchmarks.model.DailyJobStatusEntity;
import org.benchmarks.model.JobsStatusEntity;
import org.benchmarks.util.JsonLoader;
import org.json.simple.JSONObject;

public class DailyJobStatusData {

    private JobsStatusEntity jobsStatusEntity;

    public DailyJobStatusData(JobsStatusEntity jobsStatusEntity) {
        this.jobsStatusEntity = jobsStatusEntity;
    }

    public DailyJobStatusEntity getDroolsStatusData() throws IOException {
        JsonLoader jsonLoader = new JsonLoader();
        JSONObject dataJsonLastBuild = jsonLoader.getDataFromJsonObject(this.jobsStatusEntity.getLastBuildApiUrl(), SourceFileLocation.WEB);
        JSONObject dataJsonJob = jsonLoader.getDataFromJsonObject(this.jobsStatusEntity.getApiUrl(), SourceFileLocation.WEB);

        DailyJobStatusRow resultRow = parseJenkinsStatus(dataJsonLastBuild, dataJsonJob);

        DailyJobStatusEntity dailyJobStatusEntity = new DailyJobStatusEntity(resultRow, this.jobsStatusEntity);

        return dailyJobStatusEntity;
    }

    public DailyJobStatusEntity getDroolsStatusDataOld() throws IOException {
        JsonLoader jsonLoader = new JsonLoader();
        JSONObject dataJsonLastBuild = jsonLoader.getDataFromJsonObject(this.jobsStatusEntity.getLastBuildApiUrl(), SourceFileLocation.WEB);
        JSONObject dataJsonJob = jsonLoader.getDataFromJsonObject(this.jobsStatusEntity.getApiUrl(), SourceFileLocation.WEB);

        DailyJobStatusRow resultRow = parseJenkinsStatus(dataJsonLastBuild, dataJsonJob);

        DailyJobStatusEntity dailyJobStatusEntity = new DailyJobStatusEntity(resultRow, this.jobsStatusEntity);

        return dailyJobStatusEntity;
    }

    protected DailyJobStatusRow parseJenkinsStatus(JSONObject dataJsonLastBuild, JSONObject dataJsonJob) {
        DailyJobStatusRow jobStatusRow = new DailyJobStatusRow();

        if (dataJsonLastBuild.get(SourceStatusColumns.URL.getColumn()) != null) {
            jobStatusRow.setUrl(dataJsonLastBuild.get(SourceStatusColumns.URL.getColumn()).toString());
        }
        if (dataJsonJob.get(SourceStatusColumns.LAST_BUILD.getColumn()) != null) {
            JSONObject resultsObject = (JSONObject) dataJsonJob.get(SourceStatusColumns.LAST_BUILD.getColumn());
            String number = resultsObject.get(SourceStatusColumns.BUILD_NUMBER.getColumn()).toString();
            jobStatusRow.setLastBuild(number);
        }
        if (dataJsonJob.get(SourceStatusColumns.LAST_SUCCESSFUL_BUILD.getColumn()) != null) {
            JSONObject resultsObject = (JSONObject) dataJsonJob.get(SourceStatusColumns.LAST_SUCCESSFUL_BUILD.getColumn());
            String number = resultsObject.get(SourceStatusColumns.BUILD_NUMBER.getColumn()).toString();
            jobStatusRow.setLastSuccessfulBuild(number);
        }
        if (dataJsonJob.get(SourceStatusColumns.LAST_FAILED_BUILD.getColumn()) != null) {
            JSONObject resultsObject = (JSONObject) dataJsonJob.get(SourceStatusColumns.LAST_FAILED_BUILD.getColumn());
            String number = resultsObject.get(SourceStatusColumns.BUILD_NUMBER.getColumn()).toString();
            jobStatusRow.setLastFailedBuild(number);
        }
        if (dataJsonLastBuild.get(SourceStatusColumns.LAST_BUILD_STATUS.getColumn()) != null) {
            jobStatusRow.setLastBuildStatus(dataJsonLastBuild.get(SourceStatusColumns.LAST_BUILD_STATUS.getColumn()).toString());
        }
        if (dataJsonLastBuild.get(SourceStatusColumns.LAST_BUILD_TIMESTAMP.getColumn()) != null) {
            Long lastBuildDateOfExecution = Long.parseLong(dataJsonLastBuild.get(SourceStatusColumns.LAST_BUILD_TIMESTAMP.getColumn()).toString());
            jobStatusRow.setLastBuildDateOfExecution(new Timestamp(lastBuildDateOfExecution));
        }
        if (dataJsonLastBuild.get(SourceStatusColumns.LAST_BUILD_DURATION.getColumn()) != null) {
            Double lastBuildDuration = Double.parseDouble(dataJsonLastBuild.get(SourceStatusColumns.LAST_BUILD_DURATION.getColumn()).toString());
            jobStatusRow.setLastBuildDuration(lastBuildDuration);
        }

        return jobStatusRow;
    }
}
