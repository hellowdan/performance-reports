package org.benchmarks.data;

public class DroolsJenkinsReportRow implements JenkinsReportRow {

    private String name;
    private String fancyName;
    private String numberOfRules;
    private String nrOfRules;
    private String useCanonicalModel;
    private String rulesProviderId;
    private String matchRatio;
    private String score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "name=" + this.name +
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
                ", fancyName=" + fancyName + '\'' +
                ", numberOfRules='" + numberOfRules + '\'' +
                ", nrOfRules='" + nrOfRules + '\'' +
                ", useCanonicalModel=" + useCanonicalModel +
                ", rulesProviderId='" + rulesProviderId + '\'' +
                ", matchRatio='" + matchRatio + '\'' +
                ", score=" + score +
                '}';
    }
}
