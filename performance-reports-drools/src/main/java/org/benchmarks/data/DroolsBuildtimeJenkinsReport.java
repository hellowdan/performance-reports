package org.benchmarks.data;

import org.benchmarks.definitions.DroolsPropertiesLoader;
import org.benchmarks.definitions.DroolsReportColumns;
import org.benchmarks.definitions.JenkinsReportVersion;
import org.benchmarks.util.PropertiesLoader;
import org.json.simple.JSONObject;

public class DroolsBuildtimeJenkinsReport extends JenkinsReport {

    public DroolsBuildtimeJenkinsReport() {
        super();
    }

    @Override
    protected JenkinsReportRow parseJenkinsReportRow(JSONObject testJenkinsReportRow) {
        DroolsJenkinsReportRow droolsResultRow = new DroolsJenkinsReportRow();

        if (testJenkinsReportRow.get(DroolsReportColumns.BENCHMARK.getColumn()) != null) {
            droolsResultRow.setName((String) testJenkinsReportRow.get(DroolsReportColumns.BENCHMARK.getColumn()));
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.NUMBER_OF_RULES.getColumn()) != null) {
            droolsResultRow.setNumberOfRules(testJenkinsReportRow.get(DroolsReportColumns.NUMBER_OF_RULES.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.NR_OF_RULES.getColumn()) != null) {
            droolsResultRow.setNrOfRules(testJenkinsReportRow.get(DroolsReportColumns.NR_OF_RULES.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.USE_CANONICAL_MODEL.getColumn()) != null) {
            droolsResultRow.setUseCanonicalModel(testJenkinsReportRow.get(DroolsReportColumns.USE_CANONICAL_MODEL.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.RULES_PROVIDER_ID.getColumn()) != null) {
            droolsResultRow.setRulesProviderId((String) testJenkinsReportRow.get(DroolsReportColumns.RULES_PROVIDER_ID.getColumn()));
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.SCORE.getColumn()) != null) {
            droolsResultRow.setScore(testJenkinsReportRow.get(DroolsReportColumns.SCORE.getColumn()).toString());
        }

        return droolsResultRow;
    }

    @Override
    public String getDataSourcePath(JenkinsReportVersion jenkinsReportVersion, PropertiesLoader propertiesLoader) {
        DroolsPropertiesLoader droolsProperties = (DroolsPropertiesLoader) propertiesLoader;

        if (jenkinsReportVersion == JenkinsReportVersion.CURRENT) {
            return droolsProperties.getCurrentVersionBuildtimePath();
        } else if (jenkinsReportVersion == JenkinsReportVersion.PREVIOUS) {
            return droolsProperties.getPreviousVersionBuildtimePath();
        } else if (jenkinsReportVersion == JenkinsReportVersion.OLDER) {
            return droolsProperties.getOlderVersionBuildtimePath();
        } else return "";
    }

}
