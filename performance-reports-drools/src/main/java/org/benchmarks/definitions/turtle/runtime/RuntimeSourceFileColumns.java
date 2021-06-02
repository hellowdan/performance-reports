package org.benchmarks.definitions.turtle.runtime;

import org.benchmarks.definitions.SourceFileColumns;

public enum RuntimeSourceFileColumns implements SourceFileColumns {
    JOIN_COUNT("Param: joinCount"),
    MATCH_RATIO("Param: matchRatio"),
    NESTED_ACCUMULATES("Param: nestedAccumulates"),
    OBJECTS_PER_SEGMENT("Param: objectsPerSegment"),
    PER_SEGMENT_UPDATE("Param: perSegmentUpdate"),
    SEGMENT_COUNT("Param: segmentCount");

    private String column;

    RuntimeSourceFileColumns(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
