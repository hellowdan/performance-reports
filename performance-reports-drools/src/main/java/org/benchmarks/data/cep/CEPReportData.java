package org.benchmarks.data.cep;

import org.benchmarks.data.ReportData;
import org.benchmarks.data.ReportRow;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.definitions.cep.CEPSourceFileColumns;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONObject;

public class CEPReportData extends ReportData {

    private boolean IsMultiThreaded = false;

    public CEPReportData() {
        super();
    }

    public CEPReportData(boolean IsMultiThreaded) {
        super();
        this.IsMultiThreaded = IsMultiThreaded;
    }

    @Override
    protected ReportRow parseReportRow(JSONObject jenkinsReportRow) {
        CEPReportRow cepReportRow = new CEPReportRow();

        if (jenkinsReportRow.get(CEPSourceFileColumns.BENCHMARK) != null) {
            cepReportRow.setBenchmark(jenkinsReportRow.get(CEPSourceFileColumns.BENCHMARK).toString());
        }

        if (jenkinsReportRow.get(CEPSourceFileColumns.RULES_AND_EVENTS_NUMBER.getColumn()) != null) {
            cepReportRow.setRulesAndEventsNumber(jenkinsReportRow.get(CEPSourceFileColumns.RULES_AND_EVENTS_NUMBER.getColumn()).toString());
        }

        if (jenkinsReportRow.get(CEPSourceFileColumns.SCORE) != null) {
            cepReportRow.setScore(jenkinsReportRow.get(CEPSourceFileColumns.SCORE).toString());
        }

        return cepReportRow;
    }

    @Override
    public String getDataSourcePath(StaticVersion staticVersion, ReportProperties reportProperties) {
        DroolsReportProperties droolsProperties = (DroolsReportProperties) reportProperties;

        if (this.IsMultiThreaded) {
            if (staticVersion == StaticVersion.NEXT) {
                return droolsProperties.getNextVersionEventProcessingMultithreadedPath();
            } else if (staticVersion == StaticVersion.CURRENT) {
                return droolsProperties.getCurrentVersionEventProcessingMultithreadedPath();
            } else if (staticVersion == StaticVersion.PREVIOUS) {
                return droolsProperties.getPreviousVersionEventProcessingMultithreadedPath();
            } else if (staticVersion == StaticVersion.OLDER) {
                return droolsProperties.getOlderVersionEventProcessingMultithreadedPath();
            } else {
                return "";
            }
        } else {
            if (staticVersion == StaticVersion.NEXT) {
                return droolsProperties.getNextVersionEventProcessingPath();
            } else if (staticVersion == StaticVersion.CURRENT) {
                return droolsProperties.getCurrentVersionEventProcessingPath();
            } else if (staticVersion == StaticVersion.PREVIOUS) {
                return droolsProperties.getPreviousVersionEventProcessingPath();
            } else if (staticVersion == StaticVersion.OLDER) {
                return droolsProperties.getOlderVersionEventProcessingPath();
            } else {
                return "";
            }
        }
    }
}
