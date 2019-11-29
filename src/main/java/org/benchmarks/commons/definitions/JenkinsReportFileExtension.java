package org.benchmarks.commons.definitions;

public enum JenkinsReportFileExtension {
    CSV("csv"),
    JSON("json");

    private String extension;

    JenkinsReportFileExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public static JenkinsReportFileExtension getExtension(String extension) {
        if(extension.equals(CSV.toString())){
            return JenkinsReportFileExtension.CSV;
        } else if (extension.equals(JSON.toString())){
            return JenkinsReportFileExtension.JSON;
        } else return null;
    }
}
