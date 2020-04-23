package org.benchmarks.definitions;

public class DailyJobConfig {

    private String job;
    private String product;
    private String branch;
    private String path;
    private String apiPath;
    private String lastBuildApiPath;

    public DailyJobConfig(String job, String path, String product, String branch) {
        this.job = job;
        this.path = path;
        this.product = product;
        this.branch = branch;
        this.apiPath = path + "/api/json";
        this.lastBuildApiPath = path + "/lastCompletedBuild/api/json";
    }

    public String getBenchmark() {
        return job;
    }

    public void setBenchmark(String benchmark) {
        this.job = benchmark;
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

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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
}
