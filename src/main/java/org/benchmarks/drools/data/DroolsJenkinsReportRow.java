package org.benchmarks.drools.data;

import org.benchmarks.commons.data.JenkinsReportRow;

public class DroolsJenkinsReportRow implements JenkinsReportRow {

    private String name;
    private String numberOfRules;
    private String nrOfRules;
    private String useCanonicalModel;
    private String rulesProviderId;
    private String matchRatio;
    private String score;

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfRules(String numberOfRules) {
        this.numberOfRules = numberOfRules;
    }

    public void setNrOfRules(String nrOfRules) {
        this.nrOfRules = nrOfRules;
    }

    public void setUseCanonicalModel(String useCanonicalModel) {
        this.useCanonicalModel = useCanonicalModel;
    }

    public void setRulesProviderId(String rulesProviderId) {
        this.rulesProviderId = rulesProviderId;
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
        return  "name=" + this.name +
                "|numberOfRules=" + this.numberOfRules +
                "|nrOfRules=" + this.nrOfRules +
                "|useCanonicalModel=" + this.useCanonicalModel +
                "|rulesProviderId=" + this.rulesProviderId +
                "|matchRatio=" + this.matchRatio;
    }

    @Override
    public String toString() {
        return "TestResults{" +
                "name='" + name + '\'' +
                ", numberOfRules='" + numberOfRules + '\'' +
                ", nrOfRules='" + nrOfRules + '\'' +
                ", useCanonicalModel=" + useCanonicalModel +
                ", rulesProviderId='" + rulesProviderId + '\'' +
                ", matchRatio='" + matchRatio + '\'' +
                ", score=" + score +
                '}';
    }
}
