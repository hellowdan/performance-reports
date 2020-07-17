package org.benchmarks.definitions.cep;

import org.benchmarks.definitions.SourceFileColumns;

public enum CEPSourceFileColumns implements SourceFileColumns {
    RULES_AND_EVENTS_NUMBER("Param: rulesAndEventsNumber");

    private String column;

    CEPSourceFileColumns(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
