package org.benchmarks.data;

public class DroolsJenkinsDailyReportRow extends DroolsJenkinsReportRow{

    private String benchmark;

    public DroolsJenkinsDailyReportRow(String benchmark) {
        this.benchmark = benchmark;
    }

    public DroolsJenkinsDailyReportRow() {
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    @Override
    public String getUniqueID() {
        return "benchmark" + this.benchmark +
                "|name=" + this.name;
    }

    @Override
    public String toString() {
        return "TestResults{" +
                "benchmark='" + benchmark + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
