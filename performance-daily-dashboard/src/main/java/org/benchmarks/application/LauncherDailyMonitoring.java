package org.benchmarks.application;

import java.io.IOException;
import java.util.List;

import org.benchmarks.dao.DailyBenchmarkRepository;
import org.benchmarks.dao.DailyJobStatusRepository;
import org.benchmarks.data.DailyJobStatusData;
import org.benchmarks.definitions.DailyProperties;
import org.benchmarks.definitions.Product;
import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.exceptions.ExceptionsConstants;
import org.benchmarks.model.DailyBenchmarkEntity;
import org.benchmarks.model.DailyJobStatusEntity;
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
    @Autowired
    DailyJobStatusRepository statusRepository;

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
            DailyProperties dailyProperties = DailyProperties.getInstance(propertiesFilePath);
            DailyJobStatusData dailyJobStatusData = new DailyJobStatusData();

            dailyProperties.getBenchmarkConfigs().forEach(config -> {
                try {
                    List<DailyBenchmarkEntity> benchmarkEntity = dailyJobStatusData.getDroolsBenchmarkData(SourceFileExtension.CSV, dailyProperties.getSourceFileLocation(), config);
                    benchmarkEntity.forEach(row -> reportRepository.save(row));
                } catch (IOException e) {
                    LOGGER.debug(String.format(ExceptionsConstants.FAILED_SAVING_TO_DATABASE, reportRepository.getClass().getCanonicalName()), e);
                }

                try {
                    DailyJobStatusEntity statusEntity = dailyJobStatusData.getDroolsStatusData(dailyProperties.getSourceFileLocation(), config);
                    statusRepository.save(statusEntity);
                } catch (IOException e) {
                    LOGGER.debug(String.format(ExceptionsConstants.FAILED_SAVING_TO_DATABASE, statusRepository.getClass().getCanonicalName()), e);
                }
            });
        }
    }
}
