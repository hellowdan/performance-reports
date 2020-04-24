package org.benchmarks.data.operators;

import org.benchmarks.data.ReportRow;

public class OperatorsReportRow implements ReportRow {

    private String benchmark = "";
    private String fancyName = "";
    private String rulesAndFactsNumber = "";
    private String score = "";

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public String getRulesAndFactsNumber() {
        return rulesAndFactsNumber;
    }

    public void setRulesAndFactsNumber(String rulesAndFactsNumber) {
        this.rulesAndFactsNumber = rulesAndFactsNumber;
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
                "|rulesAndFactsNumber=" + this.rulesAndFactsNumber;
    }

    @Override
    public String toString() {
        return "DMNReportRow{" +
                "benchmark='" + this.benchmark + '\'' +
                ", fancyName=" + this.fancyName + '\'' +
                ", accumulateFunction=" + this.rulesAndFactsNumber + '\'' +
                ", score=" + this.score +
                '}';
    }
}
