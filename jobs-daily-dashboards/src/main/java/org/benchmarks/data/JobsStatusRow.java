package org.benchmarks.data;

public class JobsStatusRow {

    private String job;
    private String product;
    private String branch;
    private String url;
    private String apiUrl;
    private String lastBuildApiUrl;
    private int active;

    public JobsStatusRow() {
    }

    public JobsStatusRow(String job, String product, String branch, String url, String apiUrl, String lastBuildApiUrl, int active) {
        this.job = job;
        this.product = product;
        this.branch = branch;
        this.url = url;
        this.apiUrl = apiUrl;
        this.lastBuildApiUrl = lastBuildApiUrl;
        this.active = active;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getLastBuildApiUrl() {
        return lastBuildApiUrl;
    }

    public void setLastBuildApiUrl(String lastBuildApiUrl) {
        this.lastBuildApiUrl = lastBuildApiUrl;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "TestResults{" +
                "job='" + job + '\'' +
                "product='" + product + '\'' +
                "branch='" + branch + '\'' +
                "url='" + url + '\'' +
                "active='" + active + '\'' +
                "api_url='" + apiUrl + '\'' +
                "last_build_api_url='" + lastBuildApiUrl + '\'' +
                '}';
    }
}
