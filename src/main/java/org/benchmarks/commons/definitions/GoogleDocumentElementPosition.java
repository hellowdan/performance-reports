package org.benchmarks.commons.definitions;

public class GoogleDocumentElementPosition {

    private String startIndex;
    private String endIndex;

    public GoogleDocumentElementPosition() {
    }

    public GoogleDocumentElementPosition(String startIndex, String endIndex) {
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
