package org.benchmarks.drools.data;

import org.benchmarks.commons.data.JenkinsReport;
import org.benchmarks.commons.data.JenkinsReportRow;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.commons.util.PropertiesLoader;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.benchmarks.drools.definitions.DroolsReportColumns;
import org.json.simple.JSONObject;

public class DroolsRuntimeJenkinsReport extends JenkinsReport {

    public DroolsRuntimeJenkinsReport() {
        super();
    }

    @Override
    protected JenkinsReportRow parseJenkinsReportRow(JSONObject jenkinsReportRow) {
        DroolsJenkinsReportRow droolsResultRow = new DroolsJenkinsReportRow();

        if (jenkinsReportRow.get(DroolsReportColumns.benchmark.getColumn()) != null) {
            droolsResultRow.setName((String) jenkinsReportRow.get(DroolsReportColumns.benchmark.getColumn()));
        }
        if (jenkinsReportRow.get(DroolsReportColumns.matchRatio.getColumn()) != null) {
            droolsResultRow.setMatchRatio(jenkinsReportRow.get(DroolsReportColumns.matchRatio.getColumn()).toString());
        }
        if (jenkinsReportRow.get(DroolsReportColumns.score.getColumn()) != null) {
            droolsResultRow.setScore(jenkinsReportRow.get(DroolsReportColumns.score.getColumn()).toString());
        }
        droolsResultRow.setHashCode();

        return droolsResultRow;
    }

    @Override
    public String getDataSourcePath(JenkinsReportVersion jenkinsReportVersion, PropertiesLoader propertiesLoader) {
        DroolsPropertiesLoader droolsProperties = (DroolsPropertiesLoader) propertiesLoader;

        if (jenkinsReportVersion == JenkinsReportVersion.NEW) {
            return droolsProperties.getNewVersionRuntimePath();
        } else if (jenkinsReportVersion == JenkinsReportVersion.PREVIOUS) {
            return droolsProperties.getPreviousVersionRuntimePath();
        } else if (jenkinsReportVersion == JenkinsReportVersion.OLDER) {
            return droolsProperties.getOlderVersionRuntimePath();
        } else return "";
    }
}
