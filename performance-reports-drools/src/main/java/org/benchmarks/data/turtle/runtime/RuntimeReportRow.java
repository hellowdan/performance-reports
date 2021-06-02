package org.benchmarks.data.turtle.runtime;

import org.benchmarks.data.ReportRow;

public class RuntimeReportRow implements ReportRow {

    private String benchmark = "";
    private String fancyName = "";
    private String joinCount = "";
    private String matchRatio = "";
    private String nestedAccumulates = "";
    private String objectsPerSegment = "";
    private String perSegmentUpdate = "";
    private String segmentCount = "";
    private String score = "";

    public String getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(String joinCount) {
        this.joinCount = joinCount;
    }

    public String getNestedAccumulates() {
        return nestedAccumulates;
    }

    public void setNestedAccumulates(String nestedAccumulates) {
        this.nestedAccumulates = nestedAccumulates;
    }

    public String getObjectsPerSegment() {
        return objectsPerSegment;
    }

    public void setObjectsPerSegment(String objectsPerSegment) {
        this.objectsPerSegment = objectsPerSegment;
    }

    public String getPerSegmentUpdate() {
        return perSegmentUpdate;
    }

    public void setPerSegmentUpdate(String perSegmentUpdate) {
        this.perSegmentUpdate = perSegmentUpdate;
    }

    public String getSegmentCount() {
        return segmentCount;
    }

    public void setSegmentCount(String segmentCount) {
        this.segmentCount = segmentCount;
    }

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
                "|joinCount=" + this.joinCount +
                "|matchRatio=" + this.matchRatio +
                "|nestedAccumulates=" + this.nestedAccumulates +
                "|objectsPerSegment=" + this.objectsPerSegment +
                "|perSegmentUpdate=" + this.perSegmentUpdate +
                "|segmentCount=" + this.segmentCount;
    }

    @Override
    public String toString() {
        return "RuntimeReportRow{" +
                "benchmark='" + benchmark + '\'' +
                ", fancyName=" + fancyName + '\'' +
                ", joinCount=" + joinCount + '\'' +
                ", matchRatio='" + matchRatio + '\'' +
                ", nestedAccumulates=" + nestedAccumulates + '\'' +
                ", objectsPerSegment=" + objectsPerSegment + '\'' +
                ", perSegmentUpdate=" + perSegmentUpdate + '\'' +
                ", segmentCount=" + segmentCount + '\'' +
                ", score=" + score +
                '}';
    }
}
