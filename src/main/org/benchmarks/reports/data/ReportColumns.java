package org.benchmarks.reports.data;

public enum ReportColumns {
    benchmark("Benchmark"),
    numberOfRules("Param: numberOfRules"),
    nrOfRules("Param: nrOfRules"),
    useCanonicalModel("Param: useCanonicalModel"),
    rulesProviderId("Param: rulesProviderId"),
    matchRatio("Param: matchRatio"),
    score("Score");


    private String column;

    ReportColumns(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
