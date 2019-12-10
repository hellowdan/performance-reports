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
    private int hashCode;

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

    public int getHashCode() {
        return this.hashCode;
    }

    public void setHashCode() {
        int computedHashCode = 0;

        if (this.name != null) {
            computedHashCode = 31 * this.name.hashCode();
        }
        if (this.numberOfRules != null) {
            computedHashCode = 31 * computedHashCode + this.numberOfRules.hashCode();
        }
        if (this.nrOfRules != null) {
            computedHashCode = 31 * computedHashCode + this.nrOfRules.hashCode();
        }
        if (this.useCanonicalModel != null) {
            computedHashCode = 31 * computedHashCode + this.useCanonicalModel.hashCode();
        }
        if (this.rulesProviderId != null) {
            computedHashCode = 31 * computedHashCode + this.rulesProviderId.hashCode();
        }
        if (this.matchRatio != null) {
            computedHashCode = 31 * computedHashCode + this.matchRatio.hashCode();
        }

        this.hashCode = computedHashCode;
    }

    @Override
    public String toString() {
        return "TestResults{" +
                "hashCode=" + hashCode + '\'' +
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
