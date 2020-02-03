package org.benchmarks.data;

public class DailyBenchmarkRow implements ReportRow {

    private String benchmark;
    private String name;
    private String score;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String getUniqueID() {
        return "benchmark" + this.benchmark +
                "|name=" + this.name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getScore() {
        return score;
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
