package org.benchmarks.definitions.turtle.runtime;

import org.benchmarks.definitions.SourceFileColumns;

public enum RuntimeSourceFileColumns implements SourceFileColumns {
    MATCH_RATIO("Param: matchRatio");

    private String column;

    RuntimeSourceFileColumns(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
