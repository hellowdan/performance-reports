package org.benchmarks.drools.definitions;

public enum DroolsReportColumns {
    BENCHMARK("Benchmark"),
    NUMBER_OF_RULES("Param: numberOfRules"),
    NR_OF_RULES("Param: nrOfRules"),
    USE_CANONICAL_MODEL("Param: useCanonicalModel"),
    RULES_PROVIDER_ID("Param: rulesProviderId"),
    MATCH_RATIO("Param: matchRatio"),
    SCORE("Score");


    private String column;

    DroolsReportColumns(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
