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
import org.json.simple.JSONArray;
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
        JSONArray dataJsonLastBuild = jsonLoader.getDataFromJson(config.getLastBuildApiPath(), jenkinsReportLocation);
        JSONArray dataJsonJob = jsonLoader.getDataFromJson(config.getApiPath(), jenkinsReportLocation);

        JSONObject objLastBuild = new JSONObject();
        objLastBuild.put("lastBuild:", dataJsonLastBuild);

        JSONObject objJob = new JSONObject();
        objJob.put("job:", dataJsonJob);

        DroolsJenkinsDailyStatusRow resultRow = parseJenkinsStatus(objLastBuild, objJob);

        DroolsJenkinsDailyStatusEntity droolsJenkinsDailyStatusEntity = new DroolsJenkinsDailyStatusEntity(resultRow);

        return droolsJenkinsDailyStatusEntity;
    }

    protected DroolsJenkinsDailyStatusRow parseJenkinsStatus(JSONObject dataJsonLastBuild, JSONObject dataJsonJob) {
        DroolsJenkinsDailyStatusRow droolsJenkinsReportRow = new DroolsJenkinsDailyStatusRow();

        if (dataJsonLastBuild.get(DroolsStatusColumns.JOB.getColumn()) != null) {
            droolsJenkinsReportRow.setBenchmark(dataJsonLastBuild.get(DroolsStatusColumns.JOB.getColumn()).toString());
        }
        if (dataJsonLastBuild.get(DroolsStatusColumns.URL.getColumn()) != null) {
            droolsJenkinsReportRow.setUrl(dataJsonLastBuild.get(DroolsStatusColumns.URL.getColumn()).toString());
        }
        if (dataJsonLastBuild.get(DroolsStatusColumns.LAST_BUILD.getColumn()) != null) {
            droolsJenkinsReportRow.setLastBuild(dataJsonLastBuild.get(DroolsStatusColumns.LAST_BUILD.getColumn()).toString());
        }
        if (dataJsonLastBuild.get(DroolsStatusColumns.LAST_SUCCESSFUL_BUILD.getColumn()) != null) {
            droolsJenkinsReportRow.setLastSuccessfulBuild(dataJsonLastBuild.get(DroolsStatusColumns.LAST_SUCCESSFUL_BUILD.getColumn()).toString());
        }
        if (dataJsonLastBuild.get(DroolsStatusColumns.LAST_FAILED_BUILD.getColumn()) != null) {
            droolsJenkinsReportRow.setLastFailedBuild(dataJsonLastBuild.get(DroolsStatusColumns.LAST_FAILED_BUILD.getColumn()).toString());
        }
        if (dataJsonJob.get(DroolsStatusColumns.LAST_BUILD_STATUS.getColumn()) != null) {
            droolsJenkinsReportRow.setLastBuildStatus(dataJsonJob.get(DroolsStatusColumns.LAST_BUILD_STATUS.getColumn()).toString());
        }
        if (dataJsonJob.get(DroolsStatusColumns.LAST_BUILD_TIMESTAMP.getColumn()) != null) {
            Long lastBuildDateOfExecution = Long.parseLong(dataJsonJob.get(DroolsStatusColumns.LAST_BUILD_TIMESTAMP.getColumn()).toString());
            droolsJenkinsReportRow.setLastBuildDateOfExecution(new Timestamp(lastBuildDateOfExecution));
        }

        return droolsJenkinsReportRow;
    }
}
