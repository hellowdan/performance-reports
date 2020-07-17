package org.benchmarks.definitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.benchmarks.util.ReportProperties;

public class DailyBenchmarkProperties extends ReportProperties {

    private static String DAILY_BENCHMARK_JOB_SUBFOLDER = "daily_benchmark_job_subfolder";
    private static String DAILY_BENCHMARK_JOB_PRODUCTS = "daily_benchmark_job_products";
    private static String DAILY_BENCHMARK_JOB_BRANCHES = "daily_benchmark_job_branches";
    private static String DAILY_BENCHMARK_JOB_NAMES = "daily_benchmark_job_names";
    private static String DAILY_BENCHMARK_JOB_PATHS = "daily_benchmark_job_paths";
    private static String FILE_LOCATION = "file_location";
    private static DailyBenchmarkProperties dailyBenchmarkProperties = null;
    private List<DailyBenchmarkConfig> benchmarkConfigs;
    private SourceFileLocation sourceFileLocation;

    public DailyBenchmarkProperties(String filename) throws IOException {
        super(filename);

        String[] daily_benchmark_job_subfolder = {""};
        String[] daily_benchmark_job_products = {""};
        String[] daily_benchmark_job_branches = {""};
        String[] daily_benchmark_job_names = {""};
        String[] daily_benchmark_job_paths = {""};
        this.sourceFileLocation = null;

        if (this.properties.containsKey(DAILY_BENCHMARK_JOB_SUBFOLDER)) {
            daily_benchmark_job_subfolder = this.properties.getProperty(DAILY_BENCHMARK_JOB_SUBFOLDER).split(";");
        }
        if (this.properties.containsKey(DAILY_BENCHMARK_JOB_PRODUCTS)) {
            daily_benchmark_job_products = this.properties.getProperty(DAILY_BENCHMARK_JOB_PRODUCTS).split(";");
        }
        if (this.properties.containsKey(DAILY_BENCHMARK_JOB_BRANCHES)) {
            daily_benchmark_job_branches = this.properties.getProperty(DAILY_BENCHMARK_JOB_BRANCHES).split(";");
        }
        if (this.properties.containsKey(DAILY_BENCHMARK_JOB_NAMES)) {
            daily_benchmark_job_names = this.properties.getProperty(DAILY_BENCHMARK_JOB_NAMES).split(";");
        }
        if (this.properties.containsKey(DAILY_BENCHMARK_JOB_PATHS)) {
            daily_benchmark_job_paths = this.properties.getProperty(DAILY_BENCHMARK_JOB_PATHS).split(";");
        }
        if (this.properties.containsKey(FILE_LOCATION)) {
            this.sourceFileLocation = SourceFileLocation.getLocation(this.properties.getProperty(FILE_LOCATION));
        }

        benchmarkConfigs = new ArrayList();

        if (daily_benchmark_job_names.length > 0) {
            if ((daily_benchmark_job_names.length == daily_benchmark_job_paths.length)&&
                (daily_benchmark_job_names.length == daily_benchmark_job_products.length)&&
                (daily_benchmark_job_names.length == daily_benchmark_job_subfolder.length)
            ) {
                for (int i = 0; i < daily_benchmark_job_names.length; i++) {
                    DailyBenchmarkConfig config = new DailyBenchmarkConfig(daily_benchmark_job_names[i], daily_benchmark_job_paths[i],
                                                                           daily_benchmark_job_subfolder[i], daily_benchmark_job_products[i],
                                                                           daily_benchmark_job_branches[i], this.sourceFileLocation);

                    benchmarkConfigs.add(config);
                }
            }
        }
    }

    public static DailyBenchmarkProperties getInstance(String filename) throws IOException {
        if ((dailyBenchmarkProperties == null) || ((dailyBenchmarkProperties != null) && (dailyBenchmarkProperties.fileName != filename))) {
            dailyBenchmarkProperties = new DailyBenchmarkProperties(filename);
        }
        return dailyBenchmarkProperties;
    }

    public List<DailyBenchmarkConfig> getBenchmarkConfigs() {
        return benchmarkConfigs;
    }

    public SourceFileLocation getSourceFileLocation() {
        return this.sourceFileLocation;
    }
}
