package org.benchmarks.data;

import java.sql.Timestamp;

public class DroolsJenkinsDailyStatusRow {

    private String benchmark;
    private String url;
    private String lastBuild;
    private String lastSuccessfulBuild;
    private String lastFailedBuild;
    private String lastBuildStatus;
    private Timestamp lastBuildDateOfExecution;

    public DroolsJenkinsDailyStatusRow() {
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLastBuild() {
        return lastBuild;
    }

    public void setLastBuild(String lastBuild) {
        this.lastBuild = lastBuild;
    }

    public String getLastSuccessfulBuild() {
        return lastSuccessfulBuild;
    }

    public void setLastSuccessfulBuild(String lastSuccessfulBuild) {
        this.lastSuccessfulBuild = lastSuccessfulBuild;
    }

    public String getLastFailedBuild() {
        return lastFailedBuild;
    }

    public void setLastFailedBuild(String lastFailedBuild) {
        this.lastFailedBuild = lastFailedBuild;
    }

    public String getLastBuildStatus() {
        return lastBuildStatus;
    }

    public void setLastBuildStatus(String lastBuildStatus) {
        this.lastBuildStatus = lastBuildStatus;
    }

    public Timestamp getLastBuildDateOfExecution() {
        return lastBuildDateOfExecution;
    }

    public void setLastBuildDateOfExecution(Timestamp lastBuildDateOfExecution) {
        this.lastBuildDateOfExecution = lastBuildDateOfExecution;
    }

    @Override
    public String toString() {
        return "TestResults{" +
                "benchmark='" + benchmark + '\'' +
                "url='" + url + '\'' +
                "lastBuild='" + lastBuild + '\'' +
                "lastSuccessfulBuild='" + lastSuccessfulBuild + '\'' +
                "lastFailedBuild='" + lastFailedBuild + '\'' +
                "lastBuildStatus='" + lastBuildStatus + '\'' +
                "lastBuildDateOfExecution='" + lastBuildDateOfExecution + '\'' +
                '}';
    }
}
