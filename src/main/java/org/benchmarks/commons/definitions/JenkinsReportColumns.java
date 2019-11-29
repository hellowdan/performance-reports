package org.benchmarks.commons.definitions;

public enum JenkinsReportColumns {
    benchmark("Benchmark"),
    numberOfRules("Param: numberOfRules"),
    nrOfRules("Param: nrOfRules"),
    useCanonicalModel("Param: useCanonicalModel"),
    rulesProviderId("Param: rulesProviderId"),
    matchRatio("Param: matchRatio"),
    score("Score");


    private String column;

    JenkinsReportColumns(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
