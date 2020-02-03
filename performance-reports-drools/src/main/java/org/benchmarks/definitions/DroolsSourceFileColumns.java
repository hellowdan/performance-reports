package org.benchmarks.definitions;

public enum DroolsSourceFileColumns implements SourceFileColumns {
    NUMBER_OF_RULES("Param: numberOfRules"),
    NR_OF_RULES("Param: nrOfRules"),
    USE_CANONICAL_MODEL("Param: useCanonicalModel"),
    RULES_PROVIDER_ID("Param: rulesProviderId"),
    MATCH_RATIO("Param: matchRatio");

    private String column;

    DroolsSourceFileColumns(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
