package org.benchmarks.definitions;

public class DailyBenchmarkConfig {

    private String job;
    private String product;
    private String branch;
    private String path;
    private String apiPath;
    private String lastBuildApiPath;
    private String lastSuccessfulBuildCsvPath;

    public DailyBenchmarkConfig(String job, String path, String subfolder, String product, String branch, SourceFileLocation sourceFileLocation) {
        this.job = job;
        this.path = path;
        this.product = product;
        this.branch = branch;

        if (sourceFileLocation == SourceFileLocation.WEB) {
            this.lastSuccessfulBuildCsvPath = path + "/lastSuccessfulBuild/artifact/" + subfolder.trim() + "/results.csv";
            this.apiPath = path + "/api/json";
            this.lastBuildApiPath = path + "/lastCompletedBuild/api/json";
        } else if (sourceFileLocation == SourceFileLocation.CLASSPATH) {
            this.lastSuccessfulBuildCsvPath = "/" + path + ".csv";
            this.apiPath = "/" + path + ".json";
            this.lastBuildApiPath = "/" + path + "-lastBuild.json";
        }
    }

    public String getLastSuccessfulBuildCsvPath() {
        return lastSuccessfulBuildCsvPath;
    }

    public String getJob() {
        return this.job;
    }

    public String getProduct() {
        return this.product;
    }

    public String getBranch() {
        return this.branch;
    }
}
