package org.benchmarks.definitions.turtle.buildtime;

import org.benchmarks.definitions.SourceFileColumns;

public enum BuildtimeSourceFileColumns implements SourceFileColumns {
    NUMBER_OF_RULES("Param: numberOfRules"),
    NR_OF_RULES("Param: nrOfRules"),
    USE_CANONICAL_MODEL("Param: useCanonicalModel"),
    RULES_PROVIDER_ID("Param: rulesProviderId");

    private String column;

    BuildtimeSourceFileColumns(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
