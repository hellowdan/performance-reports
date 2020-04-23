package org.benchmarks.data;

import java.sql.Timestamp;

import org.benchmarks.definitions.JobStatus;

public class DailyJobStatusRow {

    private String benchmark;
    private String product;
    private String branch;
    private String url;
    private String lastBuild;
    private String lastSuccessfulBuild;
    private String lastFailedBuild;
    private String lastBuildStatus;
    private int lastBuildStatusFlag;
    private Double lastBuildDuration;
    private Timestamp lastBuildDateOfExecution;

    public DailyJobStatusRow() {
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
        if (this.lastBuildStatus.equals(JobStatus.SUCCESS.getStatus())) {
            this.lastBuildStatusFlag = JobStatus.SUCCESS.getStatusFlag();
        } else if (this.lastBuildStatus.equals(JobStatus.FAILURE.getStatus())) {
            this.lastBuildStatusFlag = JobStatus.FAILURE.getStatusFlag();
        } else if (this.lastBuildStatus.equals(JobStatus.ABORTED.getStatus())) {
            this.lastBuildStatusFlag = JobStatus.ABORTED.getStatusFlag();
        } else if (this.lastBuildStatus.equals(JobStatus.UNSTABLE.getStatus())) {
            this.lastBuildStatusFlag = JobStatus.UNSTABLE.getStatusFlag();
        }
    }

    public int getLastBuildStatusFlag() {
        return lastBuildStatusFlag;
    }

    public Double getLastBuildDuration() {
        return lastBuildDuration;
    }

    public void setLastBuildDuration(Double lastBuildDuration) {
        this.lastBuildDuration = lastBuildDuration;
    }

    public Timestamp getLastBuildDateOfExecution() {
        return lastBuildDateOfExecution;
    }

    public void setLastBuildDateOfExecution(Timestamp lastBuildDateOfExecution) {
        this.lastBuildDateOfExecution = lastBuildDateOfExecution;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "TestResults{" +
                "benchmark='" + benchmark + '\'' +
                "product='" + product + '\'' +
                "branch='" + branch + '\'' +
                "url='" + url + '\'' +
                "lastBuild='" + lastBuild + '\'' +
                "lastSuccessfulBuild='" + lastSuccessfulBuild + '\'' +
                "lastFailedBuild='" + lastFailedBuild + '\'' +
                "lastBuildStatus='" + lastBuildStatus + '\'' +
                "lastBuildStatusFlag='" + lastBuildStatusFlag + '\'' +
                "lastBuildDateOfExecution='" + lastBuildDateOfExecution + '\'' +
                '}';
    }
}
