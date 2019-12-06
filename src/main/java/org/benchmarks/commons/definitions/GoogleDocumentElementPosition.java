package org.benchmarks.commons.definitions;

public class GoogleDocumentElementPosition {

    private String startIndex;
    private String endIndex;

    public GoogleDocumentElementPosition(String startIndex, String endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public String getStartIndex() {
        return startIndex;
    }

    public String getEndIndex() {
        return endIndex;
    }

}
