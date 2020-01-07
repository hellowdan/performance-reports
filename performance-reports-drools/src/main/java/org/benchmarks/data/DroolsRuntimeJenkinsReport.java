package org.benchmarks.data;

import org.benchmarks.definitions.DroolsPropertiesLoader;
import org.benchmarks.definitions.DroolsReportColumns;
import org.benchmarks.definitions.JenkinsReportVersion;
import org.benchmarks.util.PropertiesLoader;
import org.json.simple.JSONObject;

public class DroolsRuntimeJenkinsReport extends JenkinsReport {

    public DroolsRuntimeJenkinsReport() {
        super();
    }

    @Override
    protected JenkinsReportRow parseJenkinsReportRow(JSONObject jenkinsReportRow) {
        DroolsJenkinsReportRow droolsResultRow = new DroolsJenkinsReportRow();

        if (jenkinsReportRow.get(DroolsReportColumns.BENCHMARK.getColumn()) != null) {
            droolsResultRow.setName((String) jenkinsReportRow.get(DroolsReportColumns.BENCHMARK.getColumn()));
        }
        if (jenkinsReportRow.get(DroolsReportColumns.MATCH_RATIO.getColumn()) != null) {
            droolsResultRow.setMatchRatio(jenkinsReportRow.get(DroolsReportColumns.MATCH_RATIO.getColumn()).toString());
        }
        if (jenkinsReportRow.get(DroolsReportColumns.SCORE.getColumn()) != null) {
            droolsResultRow.setScore(jenkinsReportRow.get(DroolsReportColumns.SCORE.getColumn()).toString());
        }

        return droolsResultRow;
    }

    @Override
    public String getDataSourcePath(JenkinsReportVersion jenkinsReportVersion, PropertiesLoader propertiesLoader) {
        DroolsPropertiesLoader droolsProperties = (DroolsPropertiesLoader) propertiesLoader;

        if (jenkinsReportVersion == JenkinsReportVersion.CURRENT) {
            return droolsProperties.getCurrentVersionRuntimePath();
        } else if (jenkinsReportVersion == JenkinsReportVersion.PREVIOUS) {
            return droolsProperties.getPreviousVersionRuntimePath();
        } else if (jenkinsReportVersion == JenkinsReportVersion.OLDER) {
            return droolsProperties.getOlderVersionRuntimePath();
        } else return "";
    }
}
