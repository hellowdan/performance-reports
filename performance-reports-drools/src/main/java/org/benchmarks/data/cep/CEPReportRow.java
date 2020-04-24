package org.benchmarks.data.cep;

import org.benchmarks.data.ReportRow;

public class CEPReportRow implements ReportRow {

    private String benchmark = "";
    private String fancyName = "";
    private String rulesAndEventsNumber = "";
    private String score = "";

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public void setRulesAndEventsNumber(String rulesAndEventsNumber) {
        this.rulesAndEventsNumber = rulesAndEventsNumber;
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
                "|rulesAndEventsNumber=" + this.rulesAndEventsNumber;
    }

    @Override
    public String toString() {
        return "DMNReportRow{" +
                "benchmark='" + benchmark + '\'' +
                ", fancyName=" + fancyName + '\'' +
                ", rulesAndEventsNumber=" + rulesAndEventsNumber + '\'' +
                ", score=" + score +
                '}';
    }
}
