package org.benchmarks.data;

import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.DroolsSourceFileColumns;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONObject;

public class RuntimeReportData extends ReportData {

    public RuntimeReportData() {
        super();
    }

    @Override
    protected ReportRow parseReportRow(JSONObject jenkinsReportRow) {
        DroolsReportRow droolsResultRow = new DroolsReportRow();

        if (jenkinsReportRow.get(DroolsSourceFileColumns.BENCHMARK) != null) {
            droolsResultRow.setName((String) jenkinsReportRow.get(DroolsSourceFileColumns.BENCHMARK));
        }
        if (jenkinsReportRow.get(DroolsSourceFileColumns.MATCH_RATIO.getColumn()) != null) {
            droolsResultRow.setMatchRatio(jenkinsReportRow.get(DroolsSourceFileColumns.MATCH_RATIO.getColumn()).toString());
        }
        if (jenkinsReportRow.get(DroolsSourceFileColumns.SCORE) != null) {
            droolsResultRow.setScore(jenkinsReportRow.get(DroolsSourceFileColumns.SCORE).toString());
        }

        return droolsResultRow;
    }

    @Override
    public String getDataSourcePath(StaticVersion staticVersion, ReportProperties reportProperties) {
        DroolsReportProperties droolsProperties = (DroolsReportProperties) reportProperties;

        if (staticVersion == StaticVersion.CURRENT) {
            return droolsProperties.getCurrentVersionRuntimePath();
        } else if (staticVersion == StaticVersion.PREVIOUS) {
            return droolsProperties.getPreviousVersionRuntimePath();
        } else if (staticVersion == StaticVersion.OLDER) {
            return droolsProperties.getOlderVersionRuntimePath();
        } else return "";
    }
}
