package org.benchmarks.definitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.benchmarks.util.ReportProperties;

public class DailyJobProperties extends ReportProperties {

    private static String DAILY_BENCHMARK_JOB_PRODUCT = "daily_benchmark_job_product";
    private static String DAILY_BENCHMARK_JOB_BRANCHES = "daily_benchmark_job_branches";
    private static String DAILY_BENCHMARK_JOB_NAMES = "daily_benchmark_job_names";
    private static String DAILY_BENCHMARK_JOB_PATHS = "daily_benchmark_job_paths";
    private static DailyJobProperties dailyBenchmarkProperties = null;
    private List<DailyJobConfig> dailyJobConfigs;

    public DailyJobProperties(String filename) throws IOException {
        super(filename);

        String daily_benchmark_job_product = "";
        String[] daily_benchmark_job_branches = {""};
        String[] daily_benchmark_job_names = {""};
        String[] daily_benchmark_job_paths = {""};

        if (this.properties.containsKey(DAILY_BENCHMARK_JOB_PRODUCT)) {
            daily_benchmark_job_product = this.properties.getProperty(DAILY_BENCHMARK_JOB_PRODUCT);
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

        dailyJobConfigs = new ArrayList();

        if (daily_benchmark_job_names.length > 0) {
            if ((daily_benchmark_job_names.length == daily_benchmark_job_paths.length) &&
                    (daily_benchmark_job_names.length == daily_benchmark_job_branches.length)
            ) {
                for (int i = 0; i < daily_benchmark_job_names.length; i++) {
                    DailyJobConfig config = new DailyJobConfig(daily_benchmark_job_names[i], daily_benchmark_job_paths[i],
                                                               daily_benchmark_job_product,
                                                               daily_benchmark_job_branches[i]);

                    dailyJobConfigs.add(config);
                }
            }
        }
    }

    public static DailyJobProperties getInstance(String filename) throws IOException {
        if ((dailyBenchmarkProperties == null) || ((dailyBenchmarkProperties != null) && (dailyBenchmarkProperties.fileName != filename))) {
            dailyBenchmarkProperties = new DailyJobProperties(filename);
        }
        return dailyBenchmarkProperties;
    }

    public List<DailyJobConfig> getBenchmarkConfigs() {
        return dailyJobConfigs;
    }
}
