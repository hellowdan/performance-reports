package org.benchmarks.data.pmml;

import org.benchmarks.data.ReportRow;

public class PMMLReportRow implements ReportRow {

    private String benchmark = "";
    private String fancyName = "";
    private String score = "";

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public String getFancyName() {
        return fancyName;
    }

    public void setFancyName(String fancyName) {
        this.fancyName = fancyName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String getUniqueID() {
        return "benchmark=" + this.benchmark;
    }

    @Override
    public String toString() {
        return "PMMLReportRow{" +
                "benchmark='" + benchmark + '\'' +
                ", fancyName=" + fancyName + '\'' +
                ", score=" + score +
                '}';
    }
}
