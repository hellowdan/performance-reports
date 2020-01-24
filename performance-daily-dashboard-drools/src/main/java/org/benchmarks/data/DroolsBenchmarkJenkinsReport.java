package org.benchmarks.data;

import org.benchmarks.definitions.DroolsDailyBenchmarkConfig;
import org.benchmarks.definitions.DroolsReportColumns;
import org.benchmarks.definitions.JenkinsReportVersion;
import org.benchmarks.util.PropertiesLoader;
import org.json.simple.JSONObject;

public class DroolsBenchmarkJenkinsReport extends JenkinsReport{

    private String benchmark;
    private String benchmarkPath;

    public DroolsBenchmarkJenkinsReport(DroolsDailyBenchmarkConfig config) {
        super();

        this.benchmark = config.getBenchmark();
        this.benchmarkPath = config.getLastSuccessfulBuildCsvPath();
    }

    @Override
    public String getDataSourcePath(JenkinsReportVersion jenkinsReportVersion, PropertiesLoader propertiesLoader) {
        return this.benchmarkPath;
    }

    @Override
    protected JenkinsReportRow parseJenkinsReportRow(JSONObject testJenkinsReportRow) {
        DroolsJenkinsDailyReportRow droolsResultRow = new DroolsJenkinsDailyReportRow();

        droolsResultRow.setBenchmark(this.benchmark);

        if (testJenkinsReportRow.get(DroolsReportColumns.BENCHMARK.getColumn()) != null) {
            droolsResultRow.setName((String) testJenkinsReportRow.get(DroolsReportColumns.BENCHMARK.getColumn()));
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.SCORE.getColumn()) != null) {
            droolsResultRow.setScore(testJenkinsReportRow.get(DroolsReportColumns.SCORE.getColumn()).toString());
        }

        return droolsResultRow;
    }
}
