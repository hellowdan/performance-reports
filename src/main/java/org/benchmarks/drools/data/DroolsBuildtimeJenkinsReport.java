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

        if (testJenkinsReportRow.get(DroolsReportColumns.benchmark.getColumn()) != null) {
            droolsResultRow.setName((String) testJenkinsReportRow.get(DroolsReportColumns.benchmark.getColumn()));
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.numberOfRules.getColumn()) != null) {
            droolsResultRow.setNumberOfRules(testJenkinsReportRow.get(DroolsReportColumns.numberOfRules.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.nrOfRules.getColumn()) != null) {
            droolsResultRow.setNrOfRules(testJenkinsReportRow.get(DroolsReportColumns.nrOfRules.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.useCanonicalModel.getColumn()) != null) {
            droolsResultRow.setUseCanonicalModel(testJenkinsReportRow.get(DroolsReportColumns.useCanonicalModel.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.rulesProviderId.getColumn()) != null) {
            droolsResultRow.setRulesProviderId((String) testJenkinsReportRow.get(DroolsReportColumns.rulesProviderId.getColumn()));
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.score.getColumn()) != null) {
            droolsResultRow.setScore(testJenkinsReportRow.get(DroolsReportColumns.score.getColumn()).toString());
        }
        droolsResultRow.setHashCode();

        return droolsResultRow;
    }

    @Override
    public String getDataSourcePath(JenkinsReportVersion jenkinsReportVersion, PropertiesLoader propertiesLoader) {
        DroolsPropertiesLoader droolsProperties = (DroolsPropertiesLoader) propertiesLoader;

        if (jenkinsReportVersion == JenkinsReportVersion.NEW) {
            return droolsProperties.getNewVersionBuildtimePath();
        } else if (jenkinsReportVersion == JenkinsReportVersion.PREVIOUS) {
            return droolsProperties.getPreviousVersionBuildtimePath();
        } else if (jenkinsReportVersion == JenkinsReportVersion.OLDER) {
            return droolsProperties.getOlderVersionBuildtimePath();
        } else return "";
    }

}
