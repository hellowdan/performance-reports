package org.benchmarks.data.turtle.buildtime;

import org.benchmarks.data.ReportData;
import org.benchmarks.data.ReportRow;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.definitions.turtle.buildtime.BuildtimeSourceFileColumns;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONObject;

public class BuildtimeReportData extends ReportData {

    public BuildtimeReportData() {
        super();
    }

    @Override
    protected ReportRow parseReportRow(JSONObject jenkinsReportRow) {
        BuildtimeReportRow buildtimeReportRow = new BuildtimeReportRow();

        if (jenkinsReportRow.get(BuildtimeSourceFileColumns.BENCHMARK) != null) {
            buildtimeReportRow.setBenchmark(jenkinsReportRow.get(BuildtimeSourceFileColumns.BENCHMARK).toString());
        }
        if (jenkinsReportRow.get(BuildtimeSourceFileColumns.NUMBER_OF_RULES.getColumn()) != null) {
            buildtimeReportRow.setNumberOfRules(jenkinsReportRow.get(BuildtimeSourceFileColumns.NUMBER_OF_RULES.getColumn()).toString());
        }
        if (jenkinsReportRow.get(BuildtimeSourceFileColumns.NR_OF_RULES.getColumn()) != null) {
            buildtimeReportRow.setNrOfRules(jenkinsReportRow.get(BuildtimeSourceFileColumns.NR_OF_RULES.getColumn()).toString());
        }
        if (jenkinsReportRow.get(BuildtimeSourceFileColumns.USE_CANONICAL_MODEL.getColumn()) != null) {
            buildtimeReportRow.setUseCanonicalModel(jenkinsReportRow.get(BuildtimeSourceFileColumns.USE_CANONICAL_MODEL.getColumn()).toString());
        }
        if (jenkinsReportRow.get(BuildtimeSourceFileColumns.RULES_PROVIDER_ID.getColumn()) != null) {
            buildtimeReportRow.setRulesProviderId((String) jenkinsReportRow.get(BuildtimeSourceFileColumns.RULES_PROVIDER_ID.getColumn()));
        }
        if (jenkinsReportRow.get(BuildtimeSourceFileColumns.SCORE) != null) {
            buildtimeReportRow.setScore(jenkinsReportRow.get(BuildtimeSourceFileColumns.SCORE).toString());
        }

        return buildtimeReportRow;
    }

    @Override
    public String getDataSourcePath(StaticVersion staticVersion, ReportProperties reportProperties) {
        DroolsReportProperties droolsProperties = (DroolsReportProperties) reportProperties;

        if (staticVersion == StaticVersion.NEXT) {
            return droolsProperties.getNextVersionBuildtimePath();
        } else if (staticVersion == StaticVersion.CURRENT) {
            return droolsProperties.getCurrentVersionBuildtimePath();
        } else if (staticVersion == StaticVersion.PREVIOUS) {
            return droolsProperties.getPreviousVersionBuildtimePath();
        } else if (staticVersion == StaticVersion.OLDER) {
            return droolsProperties.getOlderVersionBuildtimePath();
        } else {
            return "";
        }
    }
}
