package org.benchmarks.data.turtle.buildtime;

import org.benchmarks.data.ReportRow;

public class BuildtimeReportRow implements ReportRow {

    private String benchmark = "";
    private String fancyName = "";
    private String numberOfRules = "";
    private String nrOfRules = "";
    private String useCanonicalModel = "";
    private String rulesProviderId = "";
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

    public String getNumberOfRules() {
        return numberOfRules;
    }

    public void setNumberOfRules(String numberOfRules) {
        this.numberOfRules = numberOfRules;
    }

    public String getNrOfRules() {
        return nrOfRules;
    }

    public void setNrOfRules(String nrOfRules) {
        this.nrOfRules = nrOfRules;
    }

    public String getUseCanonicalModel() {
        return useCanonicalModel;
    }

    public void setUseCanonicalModel(String useCanonicalModel) {
        this.useCanonicalModel = useCanonicalModel;
    }

    public String getRulesProviderId() {
        return rulesProviderId;
    }

    public void setRulesProviderId(String rulesProviderId) {
        this.rulesProviderId = rulesProviderId;
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
                "|numberOfRules=" + this.numberOfRules +
                "|nrOfRules=" + this.nrOfRules +
                "|useCanonicalModel=" + this.useCanonicalModel +
                "|rulesProviderId=" + this.rulesProviderId;
    }

    @Override
    public String toString() {
        return "BuildtimeReportRow{" +
                "benchmark='" + benchmark + '\'' +
                ", fancyName=" + fancyName + '\'' +
                ", numberOfRules='" + numberOfRules + '\'' +
                ", nrOfRules='" + nrOfRules + '\'' +
                ", useCanonicalModel=" + useCanonicalModel +
                ", rulesProviderId='" + rulesProviderId + '\'' +
                ", score=" + score +
                '}';
    }
}
