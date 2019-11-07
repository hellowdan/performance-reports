package org.benchmarks.reports.data;

public class DocElementPosition {
    private String startIndex;
    private String endIndex;

    public DocElementPosition() {
    }

    public DocElementPosition(String startIndex, String endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public String getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }

    public String getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(String endIndex) {
        this.endIndex = endIndex;
    }
}
