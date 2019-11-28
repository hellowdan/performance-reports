package org.benchmarks.reports.data;

public enum InputFileType {

    BUILDTIME("buildtime"),
    RUNTIME("runtime");

    private String fileType;

    InputFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
}
