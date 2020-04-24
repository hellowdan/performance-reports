package org.benchmarks.data.operators;

import org.benchmarks.data.ReportData;
import org.benchmarks.data.ReportRow;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.definitions.operators.OperatorsSourceFileColumns;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONObject;

public class OperatorsReportData extends ReportData {

    public OperatorsReportData() {
        super();
    }

    @Override
    protected ReportRow parseReportRow(JSONObject jenkinsReportRow) {
        OperatorsReportRow operatorsReportRow = new OperatorsReportRow();

        if (jenkinsReportRow.get(OperatorsSourceFileColumns.BENCHMARK) != null) {
            operatorsReportRow.setBenchmark(jenkinsReportRow.get(OperatorsSourceFileColumns.BENCHMARK).toString());
        }

        if (jenkinsReportRow.get(OperatorsSourceFileColumns.RULES_AND_FACTS_NUMBER.getColumn()) != null) {
            operatorsReportRow.setRulesAndFactsNumber(jenkinsReportRow.get(OperatorsSourceFileColumns.RULES_AND_FACTS_NUMBER.getColumn()).toString());
        }

        if (jenkinsReportRow.get(OperatorsSourceFileColumns.SCORE) != null) {
            operatorsReportRow.setScore(jenkinsReportRow.get(OperatorsSourceFileColumns.SCORE).toString());
        }

        return operatorsReportRow;
    }

    @Override
    public String getDataSourcePath(StaticVersion staticVersion, ReportProperties reportProperties) {
        DroolsReportProperties droolsProperties = (DroolsReportProperties) reportProperties;

        if (staticVersion == StaticVersion.NEXT) {
            return droolsProperties.getNextVersionOperatorsPath();
        } else if (staticVersion == StaticVersion.CURRENT) {
            return droolsProperties.getCurrentVersionOperatorsPath();
        } else if (staticVersion == StaticVersion.PREVIOUS) {
            return droolsProperties.getPreviousVersionOperatorsPath();
        } else if (staticVersion == StaticVersion.OLDER) {
            return droolsProperties.getOlderVersionOperatorsPath();
        } else {
            return "";
        }
    }
}
