package org.benchmarks.data;

public class DailyBenchmarkRow implements ReportRow {

    private String job;
    private String benchmark;
    private String score;

    public DailyBenchmarkRow() {
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String getUniqueID() {
        return "benchmark" + this.job +
                "|name=" + this.benchmark;
    }

    @Override
    public String getBenchmark() {
        return benchmark;
    }

    @Override
    public String getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "TestResults{" +
                "benchmark='" + job + '\'' +
                ", name='" + benchmark + '\'' +
                ", score=" + score +
                '}';
    }
}
