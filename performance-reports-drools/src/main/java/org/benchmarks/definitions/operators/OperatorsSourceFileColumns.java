package org.benchmarks.definitions.operators;

import org.benchmarks.definitions.SourceFileColumns;

public enum OperatorsSourceFileColumns implements SourceFileColumns {
    RULES_AND_FACTS_NUMBER("Param: rulesAndFactsNumber");

    private String column;

    OperatorsSourceFileColumns(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
