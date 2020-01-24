package org.benchmarks.definitions;

public class DroolsDailyBenchmarkConfig {
    private String benchmark;
    private String path;
    private String apiPath;
    private String lastBuildApiPath;
    private String lastSuccessfulBuildCsvPath;

    public DroolsDailyBenchmarkConfig(String benchmark, String path) {
        this.benchmark = benchmark;
        this.path = path;
        this.apiPath = path + "/api/json";
        this.lastBuildApiPath = path + "/lastBuild/api/json";
        this.lastSuccessfulBuildCsvPath = path + "/lastSuccessfulBuild/artifact/results.csv";
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLastBuildApiPath() {
        return lastBuildApiPath;
    }

    public void setLastBuildApiPath(String lastBuildApiPath) {
        this.lastBuildApiPath = lastBuildApiPath;
    }

    public String getLastSuccessfulBuildCsvPath() {
        return lastSuccessfulBuildCsvPath;
    }

    public void setLastSuccessfulBuildCsvPath(String lastSuccessfulBuildCsvPath) {
        this.lastSuccessfulBuildCsvPath = lastSuccessfulBuildCsvPath;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }
}
