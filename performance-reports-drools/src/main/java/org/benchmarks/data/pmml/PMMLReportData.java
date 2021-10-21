package org.benchmarks.data.pmml;

import org.benchmarks.data.ReportData;
import org.benchmarks.data.ReportRow;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.definitions.pmml.PMMLSourceFileColumns;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONObject;

public class PMMLReportData extends ReportData {

    public PMMLReportData() {
        super();
    }

    @Override
    protected ReportRow parseReportRow(JSONObject jenkinsReportRow) {
        PMMLReportRow pmmlReportRow = new PMMLReportRow();

        if (jenkinsReportRow.get(PMMLSourceFileColumns.BENCHMARK) != null) {
            pmmlReportRow.setBenchmark(jenkinsReportRow.get(PMMLSourceFileColumns.BENCHMARK).toString());
        }
        if (jenkinsReportRow.get(PMMLSourceFileColumns.SCORE) != null) {
            pmmlReportRow.setScore(jenkinsReportRow.get(PMMLSourceFileColumns.SCORE).toString());
        }

        return pmmlReportRow;
    }

    @Override
    public String getDataSourcePath(StaticVersion staticVersion, ReportProperties reportProperties) {
        DroolsReportProperties droolsProperties = (DroolsReportProperties) reportProperties;

        if (staticVersion == StaticVersion.NEXT) {
            return droolsProperties.getNextVersionPMMLPath();
        } else if (staticVersion == StaticVersion.CURRENT) {
            return droolsProperties.getCurrentVersionPMMLPath();
        } else if (staticVersion == StaticVersion.PREVIOUS) {
            return droolsProperties.getPreviousVersionPMMLPath();
        } else if (staticVersion == StaticVersion.OLDER) {
            return droolsProperties.getOlderVersionPMMLPath();
        } else {
            return "";
        }
    }
}
