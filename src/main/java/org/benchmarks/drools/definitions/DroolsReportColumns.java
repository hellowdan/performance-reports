package org.benchmarks.drools.definitions;

public enum DroolsReportColumns {
    benchmark("Benchmark"),
    numberOfRules("Param: numberOfRules"),
    nrOfRules("Param: nrOfRules"),
    useCanonicalModel("Param: useCanonicalModel"),
    rulesProviderId("Param: rulesProviderId"),
    matchRatio("Param: matchRatio"),
    score("Score");


    private String column;

    DroolsReportColumns(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
