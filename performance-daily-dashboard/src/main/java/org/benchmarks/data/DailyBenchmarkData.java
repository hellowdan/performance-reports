package org.benchmarks.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.benchmarks.definitions.DailyBenchmarkConfig;
import org.benchmarks.definitions.SourceFileColumns;
import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.model.DailyBenchmarkEntity;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONObject;

public class DailyBenchmarkData extends ReportData {

    private String benchmark;
    private String benchmarkPath;
    private String product;
    private String branch;

    public DailyBenchmarkData(DailyBenchmarkConfig config) {
        super();

        this.benchmark = config.getBenchmark();
        this.benchmarkPath = config.getLastSuccessfulBuildCsvPath();
        this.product = config.getProduct();
        this.branch = config.getBranch();
    }

    @Override
    public String getDataSourcePath(StaticVersion staticVersion, ReportProperties reportProperties) {
        return this.benchmarkPath;
    }

    public List<DailyBenchmarkEntity> getDroolsBenchmarkData(SourceFileExtension sourceFileExtension, SourceFileLocation jenkinsReportLocation) throws IOException {
        List<DailyBenchmarkRow> dailyBenchmarkRows = getData(this.benchmarkPath, sourceFileExtension, jenkinsReportLocation);
        List<DailyBenchmarkEntity> dailyBenchmarkEntities = new ArrayList();

        for (int i = 0; i < dailyBenchmarkRows.size(); i++) {
            dailyBenchmarkEntities.add(getBenchmarkEntity(dailyBenchmarkRows.get(i), this.product, this.branch));
        }

        return dailyBenchmarkEntities;
    }

    private DailyBenchmarkEntity getBenchmarkEntity(DailyBenchmarkRow dailyBenchmarkRow, String product, String branch) {
        return new DailyBenchmarkEntity(
                dailyBenchmarkRow.getBenchmark(),
                dailyBenchmarkRow.getName(),
                product,
                branch,
                dailyBenchmarkRow.getScore()
        );
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
