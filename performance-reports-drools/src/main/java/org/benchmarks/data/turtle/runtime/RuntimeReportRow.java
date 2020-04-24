package org.benchmarks.data.turtle.runtime;

import org.benchmarks.data.ReportRow;

public class RuntimeReportRow implements ReportRow {

    private String benchmark = "";
    private String fancyName = "";
    private String matchRatio = "";
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

    public String getMatchRatio() {
        return matchRatio;
    }

    public void setMatchRatio(String matchRatio) {
        this.matchRatio = matchRatio;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String getUniqueID() {
        return "benchmark=" + this.benchmark +
                "|matchRatio=" + this.matchRatio;
    }

    @Override
    public String toString() {
        return "RuntimeReportRow{" +
                "benchmark='" + benchmark + '\'' +
                ", fancyName=" + fancyName + '\'' +
                ", matchRatio='" + matchRatio + '\'' +
                ", score=" + score +
                '}';
    }
}
