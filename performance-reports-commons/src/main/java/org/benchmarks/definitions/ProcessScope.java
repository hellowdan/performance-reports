package org.benchmarks.definitions;

public enum ProcessScope {
    FULL("FULL"),
    SPREADSHEETS_ONLY("SPREADSHEETS_ONLY");

    private String scope;

    ProcessScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }
}
