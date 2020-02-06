package org.benchmarks.definitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.benchmarks.util.ReportProperties;

public class DailyProperties extends ReportProperties {

    private static String DAILY_BENCHMARK_JOB_SUBFOLDER = "daily_benchmark_job_subfolder";
    private static String DAILY_BENCHMARK_JOB_PRODUCTS = "daily_benchmark_job_products";
    private static String DAILY_BENCHMARK_JOB_NAMES = "daily_benchmark_job_names";
    private static String DAILY_BENCHMARK_JOB_PATHS = "daily_benchmark_job_paths";
    private static String FILE_LOCATION = "file_location";
    private static DailyProperties dailyProperties = null;
    private List<DailyBenchmarkConfig> benchmarkConfigs;
    private SourceFileLocation sourceFileLocation;

    public DailyProperties(String filename) throws IOException {
        super(filename);

        String[] daily_benchmark_job_subfolder = {""};
        String[] daily_benchmark_job_products = {""};
        String[] daily_benchmark_job_names = {""};
        String[] daily_benchmark_job_paths = {""};
        this.sourceFileLocation = null;

        if (this.properties.containsKey(DAILY_BENCHMARK_JOB_SUBFOLDER)) {
            daily_benchmark_job_subfolder = this.properties.getProperty(DAILY_BENCHMARK_JOB_SUBFOLDER).split(";");
        }
        if (this.properties.containsKey(DAILY_BENCHMARK_JOB_PRODUCTS)) {
            daily_benchmark_job_products = this.properties.getProperty(DAILY_BENCHMARK_JOB_PRODUCTS).split(";");
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
                                                                           this.sourceFileLocation);

                    benchmarkConfigs.add(config);
                }
            }
        }
    }

    public static DailyProperties getInstance(String filename) throws IOException {
        if ((dailyProperties == null) || ((dailyProperties != null) && (dailyProperties.fileName != filename))) {
            dailyProperties = new DailyProperties(filename);
        }
        return dailyProperties;
    }

    public List<DailyBenchmarkConfig> getBenchmarkConfigs() {
        return benchmarkConfigs;
    }

    public SourceFileLocation getSourceFileLocation() {
        return this.sourceFileLocation;
    }
}
