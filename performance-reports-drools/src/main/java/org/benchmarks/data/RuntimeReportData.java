package org.benchmarks.data;

import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.SourceFileColumns;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONObject;

public class RuntimeReportData extends ReportData {

    public RuntimeReportData() {
        super();
    }

    @Override
    protected ReportRow parseJenkinsReportRow(JSONObject jenkinsReportRow) {
        DroolsReportRow droolsResultRow = new DroolsReportRow();

        if (jenkinsReportRow.get(SourceFileColumns.BENCHMARK.getColumn()) != null) {
            droolsResultRow.setName((String) jenkinsReportRow.get(SourceFileColumns.BENCHMARK.getColumn()));
        }
        if (jenkinsReportRow.get(SourceFileColumns.MATCH_RATIO.getColumn()) != null) {
            droolsResultRow.setMatchRatio(jenkinsReportRow.get(SourceFileColumns.MATCH_RATIO.getColumn()).toString());
        }
        if (jenkinsReportRow.get(SourceFileColumns.SCORE.getColumn()) != null) {
            droolsResultRow.setScore(jenkinsReportRow.get(SourceFileColumns.SCORE.getColumn()).toString());
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
