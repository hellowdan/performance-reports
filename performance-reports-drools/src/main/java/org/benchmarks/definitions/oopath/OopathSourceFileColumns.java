package org.benchmarks.definitions.oopath;

import org.benchmarks.definitions.SourceFileColumns;

public enum OopathSourceFileColumns implements SourceFileColumns {
    ACCUMULATE_FUNCTION("Param: accumulateFunction"),
    NUMBER_OF_FACTS("Param: numberOfFacts"),
    NUMBER_OF_PARENT_FACTS("Param: numberOfParentFacts"),
    NUMBER_OF_RULES("Param: numberOfRules");

    private String column;

    OopathSourceFileColumns(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
