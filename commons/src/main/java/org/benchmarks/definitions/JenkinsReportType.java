package org.benchmarks.definitions;

public enum JenkinsReportType {

    BUILDTIME("buildtime"),
    RUNTIME("runtime");

    private String fileType;

    JenkinsReportType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
}
