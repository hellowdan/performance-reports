package org.benchmarks.definitions;

public enum ReportType {

    DMN("dmn", "DMN"),
    EVENT_PROCESSING("event_processing","Event Processing"),
    EVENT_PROCESSING_MULTITHREADED("event_processing_multithreaded", "Event Processing Multithreaded"),
    OOPATH("oopath", "Oopath"),
    OPERATORS("operators", "Operators"),
    SESSION("session", "Session"),
    THROUGHPUT("throughput", "Throughput"),
    BUILDTIME("buildtime", "Buildtime"),
    RUNTIME("runtime", "Runtime"),
    RUNTIME_MULTITHREADED("runtime__multithreaded", "Runtime Multithreaded");

    private String fileType;
    private String fileTitle;

    ReportType(String fileType, String fileTitle) {
        this.fileType = fileType;
        this.fileTitle = fileTitle;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFileTitle() {
        return fileTitle;
    }
}
