package org.benchmarks.definitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DroolsDailyPropertiesLoader extends DroolsPropertiesLoader {

    private static String DAILY_BENCHMARK_JOB_NAMES = "daily_benchmark_job_names";
    private static String DAILY_BENCHMARK_JOB_PATHS = "daily_benchmark_job_paths";
    private static DroolsDailyPropertiesLoader droolsDailyPropertiesLoader = null;
    private List<DroolsDailyBenchmarkConfig> benchmarkConfigs;

    public DroolsDailyPropertiesLoader(String filename) throws IOException {
        super(filename);

        String[] daily_benchmark_job_names = {""};
        String[] daily_benchmark_job_paths = {""};

        if (this.properties.containsKey(DAILY_BENCHMARK_JOB_NAMES)) {
            daily_benchmark_job_names = this.properties.getProperty(DAILY_BENCHMARK_JOB_NAMES).split(";");
        }
        if (this.properties.containsKey(DAILY_BENCHMARK_JOB_PATHS)) {
            daily_benchmark_job_paths = this.properties.getProperty(DAILY_BENCHMARK_JOB_PATHS).split(";");
        }

        benchmarkConfigs = new ArrayList();

        if (daily_benchmark_job_names.length > 0) {
            if (daily_benchmark_job_names.length == daily_benchmark_job_paths.length) {
                for (int i = 0; i < daily_benchmark_job_names.length; i++) {
                    DroolsDailyBenchmarkConfig config = new DroolsDailyBenchmarkConfig(daily_benchmark_job_names[i], daily_benchmark_job_paths[i]);

                    benchmarkConfigs.add(config);
                }
            }
        }
    }

    public static DroolsDailyPropertiesLoader getInstance(String filename) throws IOException {
        if ((droolsDailyPropertiesLoader == null) || ((droolsDailyPropertiesLoader != null) && (droolsDailyPropertiesLoader.fileName != filename))) {
            droolsDailyPropertiesLoader = new DroolsDailyPropertiesLoader(filename);
        }
        return droolsDailyPropertiesLoader;
    }

    public List<DroolsDailyBenchmarkConfig> getBenchmarkConfigs() {
        return benchmarkConfigs;
    }
}
