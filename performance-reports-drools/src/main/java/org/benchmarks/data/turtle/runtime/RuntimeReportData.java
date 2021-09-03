package org.benchmarks.data.turtle.runtime;

import org.benchmarks.data.ReportData;
import org.benchmarks.data.ReportRow;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.definitions.turtle.runtime.RuntimeSourceFileColumns;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONObject;

public class RuntimeReportData extends ReportData {

    private boolean IsMultiThreaded = false;

    public RuntimeReportData() {
        super();
    }

    public RuntimeReportData(boolean IsMultiThreaded) {
        super();
        this.IsMultiThreaded = IsMultiThreaded;
    }

    @Override
    protected ReportRow parseReportRow(JSONObject jenkinsReportRow) {
        RuntimeReportRow runtimeReportRow = new RuntimeReportRow();

        if (jenkinsReportRow.get(RuntimeSourceFileColumns.BENCHMARK) != null) {
            runtimeReportRow.setBenchmark(jenkinsReportRow.get(RuntimeSourceFileColumns.BENCHMARK).toString());
        }
        if (jenkinsReportRow.get(RuntimeSourceFileColumns.JOIN_COUNT.getColumn()) != null) {
            runtimeReportRow.setJoinCount(jenkinsReportRow.get(RuntimeSourceFileColumns.JOIN_COUNT.getColumn()).toString());
        }
        if (jenkinsReportRow.get(RuntimeSourceFileColumns.MATCH_RATIO.getColumn()) != null) {
            runtimeReportRow.setMatchRatio(jenkinsReportRow.get(RuntimeSourceFileColumns.MATCH_RATIO.getColumn()).toString());
        }
        if (jenkinsReportRow.get(RuntimeSourceFileColumns.NESTED_ACCUMULATES.getColumn()) != null) {
            runtimeReportRow.setNestedAccumulates(jenkinsReportRow.get(RuntimeSourceFileColumns.NESTED_ACCUMULATES.getColumn()).toString());
        }
        if (jenkinsReportRow.get(RuntimeSourceFileColumns.OBJECTS_PER_SEGMENT.getColumn()) != null) {
            runtimeReportRow.setObjectsPerSegment(jenkinsReportRow.get(RuntimeSourceFileColumns.OBJECTS_PER_SEGMENT.getColumn()).toString());
        }
        if (jenkinsReportRow.get(RuntimeSourceFileColumns.PER_SEGMENT_UPDATE.getColumn()) != null) {
            runtimeReportRow.setPerSegmentUpdate(jenkinsReportRow.get(RuntimeSourceFileColumns.PER_SEGMENT_UPDATE.getColumn()).toString());
        }
        if (jenkinsReportRow.get(RuntimeSourceFileColumns.SEGMENT_COUNT.getColumn()) != null) {
            runtimeReportRow.setSegmentCount(jenkinsReportRow.get(RuntimeSourceFileColumns.SEGMENT_COUNT.getColumn()).toString());
        }
        if (jenkinsReportRow.get(RuntimeSourceFileColumns.SCORE) != null) {
            runtimeReportRow.setScore(jenkinsReportRow.get(RuntimeSourceFileColumns.SCORE).toString());
        }

        return runtimeReportRow;
    }

    @Override
    public String getDataSourcePath(StaticVersion staticVersion, ReportProperties reportProperties) {
        DroolsReportProperties droolsProperties = (DroolsReportProperties) reportProperties;

        if (this.IsMultiThreaded) {
            if (staticVersion == StaticVersion.NEXT) {
                return droolsProperties.getNextVersionRuntimeMultithreadedPath();
            } else if (staticVersion == StaticVersion.CURRENT) {
                return droolsProperties.getCurrentVersionRuntimeMultithreadedPath();
            } else if (staticVersion == StaticVersion.PREVIOUS) {
                return droolsProperties.getPreviousVersionRuntimeMultithreadedPath();
            } else if (staticVersion == StaticVersion.OLDER) {
                return droolsProperties.getOlderVersionRuntimeMultithreadedPath();
            } else {
                return "";
            }
        } else {
            if (staticVersion == StaticVersion.NEXT) {
                return droolsProperties.getNextVersionRuntimePath();
            } else if (staticVersion == StaticVersion.CURRENT) {
                return droolsProperties.getCurrentVersionRuntimePath();
            } else if (staticVersion == StaticVersion.PREVIOUS) {
                return droolsProperties.getPreviousVersionRuntimePath();
            } else if (staticVersion == StaticVersion.OLDER) {
                return droolsProperties.getOlderVersionRuntimePath();
            } else {
                return "";
            }
        }
    }
}
