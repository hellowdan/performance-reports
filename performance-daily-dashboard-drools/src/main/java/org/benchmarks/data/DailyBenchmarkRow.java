package org.benchmarks.data;

public class DailyBenchmarkRow extends DroolsReportRow {

    private String benchmark;

    public DailyBenchmarkRow(String benchmark) {
        this.benchmark = benchmark;
    }

    public DailyBenchmarkRow() {
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
