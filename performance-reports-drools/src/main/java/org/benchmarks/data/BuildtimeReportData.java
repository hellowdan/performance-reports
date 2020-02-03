package org.benchmarks.data;

import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.DroolsSourceFileColumns;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONObject;

public class BuildtimeReportData extends ReportData {

    public BuildtimeReportData() {
        super();
    }

    @Override
    protected ReportRow parseReportRow(JSONObject testJenkinsReportRow) {
        DroolsReportRow droolsResultRow = new DroolsReportRow();

        if (testJenkinsReportRow.get(DroolsSourceFileColumns.BENCHMARK) != null) {
            droolsResultRow.setName((String) testJenkinsReportRow.get(DroolsSourceFileColumns.BENCHMARK));
        }
        if (testJenkinsReportRow.get(DroolsSourceFileColumns.NUMBER_OF_RULES.getColumn()) != null) {
            droolsResultRow.setNumberOfRules(testJenkinsReportRow.get(DroolsSourceFileColumns.NUMBER_OF_RULES.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(DroolsSourceFileColumns.NR_OF_RULES.getColumn()) != null) {
            droolsResultRow.setNrOfRules(testJenkinsReportRow.get(DroolsSourceFileColumns.NR_OF_RULES.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(DroolsSourceFileColumns.USE_CANONICAL_MODEL.getColumn()) != null) {
            droolsResultRow.setUseCanonicalModel(testJenkinsReportRow.get(DroolsSourceFileColumns.USE_CANONICAL_MODEL.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(DroolsSourceFileColumns.RULES_PROVIDER_ID.getColumn()) != null) {
            droolsResultRow.setRulesProviderId((String) testJenkinsReportRow.get(DroolsSourceFileColumns.RULES_PROVIDER_ID.getColumn()));
        }
        if (testJenkinsReportRow.get(DroolsSourceFileColumns.SCORE) != null) {
            droolsResultRow.setScore(testJenkinsReportRow.get(DroolsSourceFileColumns.SCORE).toString());
        }

        return droolsResultRow;
    }

    @Override
    public String getDataSourcePath(StaticVersion staticVersion, ReportProperties reportProperties) {
        DroolsReportProperties droolsProperties = (DroolsReportProperties) reportProperties;

        if (staticVersion == StaticVersion.CURRENT) {
            return droolsProperties.getCurrentVersionBuildtimePath();
        } else if (staticVersion == StaticVersion.PREVIOUS) {
            return droolsProperties.getPreviousVersionBuildtimePath();
        } else if (staticVersion == StaticVersion.OLDER) {
            return droolsProperties.getOlderVersionBuildtimePath();
        } else return "";
    }

}
