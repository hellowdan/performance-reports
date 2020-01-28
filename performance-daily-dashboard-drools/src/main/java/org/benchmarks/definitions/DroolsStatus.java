package org.benchmarks.definitions;

public enum DroolsStatus {
    SUCCESS("SUCCESS", 1),
    FAILURE("FAILURE", 0);

    private String status;
    private int statusFlag;

    DroolsStatus(String status, int statusFlag) {
        this.status = status;
        this.statusFlag = statusFlag;
    }

    public String getStatus() {
        return status;
    }

    public int getStatusFlag() {
        return statusFlag;
    }
}
