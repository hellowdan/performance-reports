package org.benchmarks.data.dmn;

import org.benchmarks.data.ReportData;
import org.benchmarks.data.ReportRow;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.definitions.dmn.DMNSourceFileColumns;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONObject;

public class DMNReportData extends ReportData {

    public DMNReportData() {
        super();
    }

    @Override
    protected ReportRow parseReportRow(JSONObject jenkinsReportRow) {
        DMNReportRow dmnReportRow = new DMNReportRow();

        if (jenkinsReportRow.get(DMNSourceFileColumns.BENCHMARK) != null) {
            dmnReportRow.setBenchmark(jenkinsReportRow.get(DMNSourceFileColumns.BENCHMARK).toString());
        }

        if (jenkinsReportRow.get(DMNSourceFileColumns.EXPRESSION.getColumn()) != null) {
            dmnReportRow.setExpression(jenkinsReportRow.get(DMNSourceFileColumns.EXPRESSION.getColumn()).toString());
        }

        if (jenkinsReportRow.get(DMNSourceFileColumns.NUMBER_OF_DECISION_TABLE_RULES.getColumn()) != null) {
            dmnReportRow.setNumberOfDecisionTableRules(jenkinsReportRow.get(DMNSourceFileColumns.NUMBER_OF_DECISION_TABLE_RULES.getColumn()).toString());
        }

        if (jenkinsReportRow.get(DMNSourceFileColumns.NUMBER_OF_DECISIONS.getColumn()) != null) {
            dmnReportRow.setNumberOfDecisions(jenkinsReportRow.get(DMNSourceFileColumns.NUMBER_OF_DECISIONS.getColumn()).toString());
        }

        if (jenkinsReportRow.get(DMNSourceFileColumns.NUMBER_OF_DECISIONS_WITH_BKM.getColumn()) != null) {
            dmnReportRow.setNumberOfDecisionsWithBKM(jenkinsReportRow.get(DMNSourceFileColumns.NUMBER_OF_DECISIONS_WITH_BKM.getColumn()).toString());
        }

        if (jenkinsReportRow.get(DMNSourceFileColumns.NUMBER_OF_DECISIONS_WITH_CONTEXT.getColumn()) != null) {
            dmnReportRow.setNumberOfDecisionsWithContext(jenkinsReportRow.get(DMNSourceFileColumns.NUMBER_OF_DECISIONS_WITH_CONTEXT.getColumn()).toString());
        }

        if (jenkinsReportRow.get(DMNSourceFileColumns.NUMBER_OF_ELEMENTS.getColumn()) != null) {
            dmnReportRow.setNumberOfElements(jenkinsReportRow.get(DMNSourceFileColumns.NUMBER_OF_ELEMENTS.getColumn()).toString());
        }

        if (jenkinsReportRow.get(DMNSourceFileColumns.PARAM.getColumn()) != null) {
            dmnReportRow.setParam(jenkinsReportRow.get(DMNSourceFileColumns.PARAM.getColumn()).toString());
        }

        if (jenkinsReportRow.get(DMNSourceFileColumns.RESOURCE_NAME.getColumn()) != null) {
            dmnReportRow.setResourceName(jenkinsReportRow.get(DMNSourceFileColumns.RESOURCE_NAME.getColumn()).toString());
        }

        if (jenkinsReportRow.get(DMNSourceFileColumns.SPARSENESS.getColumn()) != null) {
            dmnReportRow.setSparseness(jenkinsReportRow.get(DMNSourceFileColumns.SPARSENESS.getColumn()).toString());
        }

        if (jenkinsReportRow.get(DMNSourceFileColumns.SCORE) != null) {
            dmnReportRow.setScore(jenkinsReportRow.get(DMNSourceFileColumns.SCORE).toString());
        }

        return dmnReportRow;
    }

    @Override
    public String getDataSourcePath(StaticVersion staticVersion, ReportProperties reportProperties) {
        DroolsReportProperties droolsProperties = (DroolsReportProperties) reportProperties;

        if (staticVersion == StaticVersion.NEXT) {
            return droolsProperties.getNextVersionDMNPath();
        } else if (staticVersion == StaticVersion.CURRENT) {
            return droolsProperties.getCurrentVersionDMNPath();
        } else if (staticVersion == StaticVersion.PREVIOUS) {
            return droolsProperties.getPreviousVersionDMNPath();
        } else if (staticVersion == StaticVersion.OLDER) {
            return droolsProperties.getOlderVersionDMNPath();
        } else {
            return "";
        }
    }
}
