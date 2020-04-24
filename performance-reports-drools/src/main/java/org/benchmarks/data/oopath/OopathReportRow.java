package org.benchmarks.data.oopath;

import org.benchmarks.data.ReportRow;

public class OopathReportRow implements ReportRow {

    private String benchmark = "";
    private String fancyName = "";
    private String accumulateFunction = "";
    private String numberOfFacts = "";
    private String numberOfParentFacts = "";
    private String numberOfRules = "";
    private String score = "";

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public void setAccumulateFunction(String accumulateFunction) {
        this.accumulateFunction = accumulateFunction;
    }

    public void setNumberOfFacts(String numberOfFacts) {
        this.numberOfFacts = numberOfFacts;
    }

    public void setNumberOfParentFacts(String numberOfParentFacts) {
        this.numberOfParentFacts = numberOfParentFacts;
    }

    public String getNumberOfRules() {
        return numberOfRules;
    }

    public void setNumberOfRules(String numberOfRules) {
        this.numberOfRules = numberOfRules;
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
                "|accumulateFunction=" + this.accumulateFunction +
                "|numberOfFacts=" + this.numberOfFacts +
                "|numberOfParentFacts=" + this.numberOfParentFacts +
                "|numberOfRules=" + this.numberOfRules;
    }

    @Override
    public String toString() {
        return "DMNReportRow{" +
                "benchmark='" + this.benchmark + '\'' +
                ", fancyName=" + this.fancyName + '\'' +
                ", accumulateFunction=" + this.accumulateFunction + '\'' +
                ", numberOfFacts=" + this.numberOfFacts + '\'' +
                ", numberOfParentFacts=" + this.numberOfParentFacts + '\'' +
                ", numberOfRules=" + this.numberOfRules + '\'' +
                ", score=" + this.score +
                '}';
    }
}
