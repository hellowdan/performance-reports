package org.benchmarks.data;

import org.benchmarks.definitions.DailyBenchmarkConfig;
import org.benchmarks.definitions.SourceFileColumns;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONObject;

public class DailyBenchmarkData extends ReportData {

    private String benchmark;
    private String benchmarkPath;

    public DailyBenchmarkData(DailyBenchmarkConfig config) {
        super();

        this.benchmark = config.getBenchmark();
        this.benchmarkPath = config.getLastSuccessfulBuildCsvPath();
    }

    @Override
    public String getDataSourcePath(StaticVersion staticVersion, ReportProperties reportProperties) {
        return this.benchmarkPath;
    }

    @Override
    protected ReportRow parseReportRow(JSONObject reportRow) {
        DailyBenchmarkRow dailyBenchmarkRow = new DailyBenchmarkRow();

        dailyBenchmarkRow.setBenchmark(this.benchmark);

        if (reportRow.get(SourceFileColumns.BENCHMARK) != null) {
            dailyBenchmarkRow.setName((String) reportRow.get(SourceFileColumns.BENCHMARK));
        }
        if (reportRow.get(SourceFileColumns.SCORE) != null) {
            dailyBenchmarkRow.setScore(reportRow.get(SourceFileColumns.SCORE).toString());
        }

        return dailyBenchmarkRow;
    }
}
