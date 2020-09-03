package org.benchmarks.definitions;

public enum ProcessIteration {
    WEEKLY("WEEKLY"),
    GA("GA");

    private String iteration;

    ProcessIteration(String scope) {
        this.iteration = scope;
    }

    public String getIteration() {
        return iteration;
    }
}
