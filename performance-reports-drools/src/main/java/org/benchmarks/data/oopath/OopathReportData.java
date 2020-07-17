package org.benchmarks.data.oopath;

import org.benchmarks.data.ReportData;
import org.benchmarks.data.ReportRow;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.definitions.oopath.OopathSourceFileColumns;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONObject;

public class OopathReportData extends ReportData {

    public OopathReportData() {
        super();
    }

    @Override
    protected ReportRow parseReportRow(JSONObject jenkinsReportRow) {
        OopathReportRow oopathReportRow = new OopathReportRow();

        if (jenkinsReportRow.get(OopathSourceFileColumns.BENCHMARK) != null) {
            oopathReportRow.setBenchmark(jenkinsReportRow.get(OopathSourceFileColumns.BENCHMARK).toString());
        }

        if (jenkinsReportRow.get(OopathSourceFileColumns.ACCUMULATE_FUNCTION.getColumn()) != null) {
            oopathReportRow.setAccumulateFunction(jenkinsReportRow.get(OopathSourceFileColumns.ACCUMULATE_FUNCTION.getColumn()).toString());
        }

        if (jenkinsReportRow.get(OopathSourceFileColumns.NUMBER_OF_FACTS.getColumn()) != null) {
            oopathReportRow.setNumberOfFacts(jenkinsReportRow.get(OopathSourceFileColumns.NUMBER_OF_FACTS.getColumn()).toString());
        }

        if (jenkinsReportRow.get(OopathSourceFileColumns.NUMBER_OF_PARENT_FACTS.getColumn()) != null) {
            oopathReportRow.setNumberOfParentFacts(jenkinsReportRow.get(OopathSourceFileColumns.NUMBER_OF_PARENT_FACTS.getColumn()).toString());
        }

        if (jenkinsReportRow.get(OopathSourceFileColumns.NUMBER_OF_RULES.getColumn()) != null) {
            oopathReportRow.setNumberOfRules(jenkinsReportRow.get(OopathSourceFileColumns.NUMBER_OF_RULES.getColumn()).toString());
        }

        if (jenkinsReportRow.get(OopathSourceFileColumns.SCORE) != null) {
            oopathReportRow.setScore(jenkinsReportRow.get(OopathSourceFileColumns.SCORE).toString());
        }

        return oopathReportRow;
    }

    @Override
    public String getDataSourcePath(StaticVersion staticVersion, ReportProperties reportProperties) {
        DroolsReportProperties droolsProperties = (DroolsReportProperties) reportProperties;

        if (staticVersion == StaticVersion.NEXT) {
            return droolsProperties.getNextVersionOopathPath();
        } else if (staticVersion == StaticVersion.CURRENT) {
            return droolsProperties.getCurrentVersionOopathPath();
        } else if (staticVersion == StaticVersion.PREVIOUS) {
            return droolsProperties.getPreviousVersionOopathPath();
        } else if (staticVersion == StaticVersion.OLDER) {
            return droolsProperties.getOlderVersionOopathPath();
        } else {
            return "";
        }
    }
}
