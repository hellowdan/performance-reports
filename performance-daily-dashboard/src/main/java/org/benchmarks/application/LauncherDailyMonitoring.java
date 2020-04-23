package org.benchmarks.application;

import java.io.IOException;
import java.util.List;

import org.benchmarks.dao.DailyBenchmarkRepository;
import org.benchmarks.data.DailyBenchmarkData;
import org.benchmarks.definitions.DailyBenchmarkProperties;
import org.benchmarks.definitions.Product;
import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.exceptions.ExceptionsConstants;
import org.benchmarks.model.DailyBenchmarkEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.benchmarks"})
@ComponentScan({"org.benchmarks"})
@EntityScan("org.benchmarks")
@EnableJpaRepositories("org.benchmarks")
public class LauncherDailyMonitoring implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(LauncherDailyMonitoring.class);

    @Autowired
    DailyBenchmarkRepository reportRepository;

    public static void main(String[] args) {
        SpringApplication.run(LauncherDailyMonitoring.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String propertiesFilePath = null;

        if (args[0].equals(Product.DROOLS.getProduct())) {
            propertiesFilePath = "/drools-daily-dashboard.properties";
        } else if (args[0].equals(Product.OPTAPLANNER.getProduct())) {
            propertiesFilePath = "/optaplanner-daily-dashboard.properties";
        }

        if (propertiesFilePath != null) {
            DailyBenchmarkProperties dailyBenchmarkProperties = DailyBenchmarkProperties.getInstance(propertiesFilePath);

            dailyBenchmarkProperties.getBenchmarkConfigs().forEach(config -> {
                try {
                    DailyBenchmarkData dailyBenchmarkData = new DailyBenchmarkData(config);
                    List<DailyBenchmarkEntity> benchmarkEntity = dailyBenchmarkData.getDroolsBenchmarkData(SourceFileExtension.CSV, dailyBenchmarkProperties.getSourceFileLocation());
                    benchmarkEntity.forEach(row -> reportRepository.save(row));
                } catch (IOException e) {
                    LOGGER.debug(String.format(ExceptionsConstants.FAILED_SAVING_TO_DATABASE, reportRepository.getClass().getCanonicalName()), e);
                }
            });
        }
    }
}
