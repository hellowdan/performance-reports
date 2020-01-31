package org.benchmarks.definitions;

public enum ReportType {

    BUILDTIME("buildtime"),
    RUNTIME("runtime");

    private String fileType;

    ReportType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
}
