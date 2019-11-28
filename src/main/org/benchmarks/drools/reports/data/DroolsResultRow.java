package org.benchmarks.drools.reports.data;

import org.benchmarks.reports.data.ResultRow;

public class DroolsResultRow extends ResultRow {

    private String name;
    private String numberOfRules;
    private String nrOfRules;
    private String useCanonicalModel;
    private String rulesProviderId;
    private String matchRatio;
    private String score;
    private int hashCode;

    public DroolsResultRow() {
    }

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
        int hashCode = 0;

        if (this.name != null) {
            hashCode = 31 * this.name.hashCode();
        }
        if (this.numberOfRules != null) {
            hashCode = 31 * hashCode + this.numberOfRules.hashCode();
        }
        if (this.nrOfRules != null) {
            hashCode = 31 * hashCode + this.nrOfRules.hashCode();
        }
        if (this.useCanonicalModel != null) {
            hashCode = 31 * hashCode + this.useCanonicalModel.hashCode();
        }
        if (this.rulesProviderId != null) {
            hashCode = 31 * hashCode + this.rulesProviderId.hashCode();
        }
        if (this.matchRatio != null) {
            hashCode = 31 * hashCode + this.matchRatio.hashCode();
        }

        this.hashCode = hashCode;
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
