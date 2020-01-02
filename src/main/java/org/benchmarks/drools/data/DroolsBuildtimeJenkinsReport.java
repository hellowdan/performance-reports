package org.benchmarks.drools.data;

import org.benchmarks.commons.data.JenkinsReport;
import org.benchmarks.commons.data.JenkinsReportRow;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.commons.util.PropertiesLoader;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.benchmarks.drools.definitions.DroolsReportColumns;
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

        if (jenkinsReportVersion == JenkinsReportVersion.NEW) {
            return droolsProperties.getCurrentVersionBuildtimePath();
        } else if (jenkinsReportVersion == JenkinsReportVersion.PREVIOUS) {
            return droolsProperties.getPreviousVersionBuildtimePath();
        } else if (jenkinsReportVersion == JenkinsReportVersion.OLDER) {
            return droolsProperties.getOlderVersionBuildtimePath();
        } else return "";
    }

}
