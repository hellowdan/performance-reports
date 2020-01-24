package org.benchmarks.definitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DroolsDailyPropertiesLoader extends DroolsPropertiesLoader {

    private List<DroolsDailyBenchmarkConfig> benchmarkConfigs;

    private static DroolsDailyPropertiesLoader droolsDailyPropertiesLoader = null;

    public DroolsDailyPropertiesLoader(String filename) throws IOException {
        super(filename);

        String[] daily_benchmark_names = this.properties.getProperty("daily_benchmark_names").split(";");
        String[] daily_benchmark_paths = this.properties.getProperty("daily_benchmark_paths").split(";");

        benchmarkConfigs = new ArrayList();

        if(daily_benchmark_names.length == daily_benchmark_paths.length) {
            for (int i = 0; i < daily_benchmark_names.length; i++) {
                DroolsDailyBenchmarkConfig config = new DroolsDailyBenchmarkConfig(daily_benchmark_names[i], daily_benchmark_paths[i]);

                benchmarkConfigs.add(config);
            }
        }
    }

    public List<DroolsDailyBenchmarkConfig> getBenchmarkConfigs() {
        return benchmarkConfigs;
    }

    public static DroolsDailyPropertiesLoader getInstance(String filename) throws IOException {
        if ((droolsDailyPropertiesLoader == null) || ((droolsDailyPropertiesLoader != null) && (droolsDailyPropertiesLoader.fileName != filename))) {
            droolsDailyPropertiesLoader = new DroolsDailyPropertiesLoader(filename);
        }
        return droolsDailyPropertiesLoader;
    }
}
