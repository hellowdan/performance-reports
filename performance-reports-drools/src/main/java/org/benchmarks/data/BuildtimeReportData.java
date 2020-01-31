package org.benchmarks.data;

import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.SourceFileColumns;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONObject;

public class BuildtimeReportData extends ReportData {

    public BuildtimeReportData() {
        super();
    }

    @Override
    protected ReportRow parseJenkinsReportRow(JSONObject testJenkinsReportRow) {
        DroolsReportRow droolsResultRow = new DroolsReportRow();

        if (testJenkinsReportRow.get(SourceFileColumns.BENCHMARK.getColumn()) != null) {
            droolsResultRow.setName((String) testJenkinsReportRow.get(SourceFileColumns.BENCHMARK.getColumn()));
        }
        if (testJenkinsReportRow.get(SourceFileColumns.NUMBER_OF_RULES.getColumn()) != null) {
            droolsResultRow.setNumberOfRules(testJenkinsReportRow.get(SourceFileColumns.NUMBER_OF_RULES.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(SourceFileColumns.NR_OF_RULES.getColumn()) != null) {
            droolsResultRow.setNrOfRules(testJenkinsReportRow.get(SourceFileColumns.NR_OF_RULES.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(SourceFileColumns.USE_CANONICAL_MODEL.getColumn()) != null) {
            droolsResultRow.setUseCanonicalModel(testJenkinsReportRow.get(SourceFileColumns.USE_CANONICAL_MODEL.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(SourceFileColumns.RULES_PROVIDER_ID.getColumn()) != null) {
            droolsResultRow.setRulesProviderId((String) testJenkinsReportRow.get(SourceFileColumns.RULES_PROVIDER_ID.getColumn()));
        }
        if (testJenkinsReportRow.get(SourceFileColumns.SCORE.getColumn()) != null) {
            droolsResultRow.setScore(testJenkinsReportRow.get(SourceFileColumns.SCORE.getColumn()).toString());
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
