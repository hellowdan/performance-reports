package org.benchmarks.data.dmn;

import org.benchmarks.data.ReportRow;

public class DMNReportRow implements ReportRow {

    private String benchmark = "";
    private String fancyName = "";
    private String expression = "";
    private String numberOfDecisionTableRules = "";
    private String numberOfDecisions = "";
    private String numberOfDecisionsWithBKM = "";
    private String numberOfDecisionsWithContext = "";
    private String numberOfElements = "";
    private String param = "";
    private String resourceName = "";
    private String sparseness = "";
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

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getNumberOfDecisionTableRules() {
        return numberOfDecisionTableRules;
    }

    public void setNumberOfDecisionTableRules(String numberOfDecisionTableRules) {
        this.numberOfDecisionTableRules = numberOfDecisionTableRules;
    }

    public String getNumberOfDecisions() {
        return numberOfDecisions;
    }

    public void setNumberOfDecisions(String numberOfDecisions) {
        this.numberOfDecisions = numberOfDecisions;
    }

    public String getNumberOfDecisionsWithBKM() {
        return numberOfDecisionsWithBKM;
    }

    public void setNumberOfDecisionsWithBKM(String numberOfDecisionsWithBKM) {
        this.numberOfDecisionsWithBKM = numberOfDecisionsWithBKM;
    }

    public String getNumberOfDecisionsWithContext() {
        return numberOfDecisionsWithContext;
    }

    public void setNumberOfDecisionsWithContext(String numberOfDecisionsWithContext) {
        this.numberOfDecisionsWithContext = numberOfDecisionsWithContext;
    }

    public String getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(String numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getSparseness() {
        return sparseness;
    }

    public void setSparseness(String sparseness) {
        this.sparseness = sparseness;
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
                "|expression=" + this.expression +
                "|numberOfDecisionTableRules=" + this.numberOfDecisionTableRules +
                "|numberOfDecisions=" + this.numberOfDecisions +
                "|numberOfDecisionsWithBKM=" + this.numberOfDecisionsWithBKM +
                "|numberOfDecisionsWithContext=" + this.numberOfDecisionsWithContext +
                "|numberOfElements=" + this.numberOfElements +
                "|param=" + this.param +
                "|resourceName=" + this.resourceName +
                "|sparseness=" + this.sparseness;
    }

    @Override
    public String toString() {
        return "DMNReportRow{" +
                "benchmark='" + benchmark + '\'' +
                ", fancyName=" + fancyName + '\'' +
                ", expression=" + expression + '\'' +
                ", numberOfDecisionTableRules=" + numberOfDecisionTableRules + '\'' +
                ", numberOfDecisions=" + numberOfDecisions + '\'' +
                ", numberOfDecisionsWithBKM=" + numberOfDecisionsWithBKM + '\'' +
                ", numberOfDecisionsWithContext=" + numberOfDecisionsWithContext + '\'' +
                ", numberOfElements=" + numberOfElements + '\'' +
                ", param=" + param + '\'' +
                ", resourceName=" + resourceName + '\'' +
                ", sparseness=" + sparseness + '\'' +
                ", score=" + score +
                '}';
    }
}
