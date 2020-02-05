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
import org.benchmarks.model.DailyBenchmarkEntity;
import org.benchmarks.model.DailyJobStatusEntity;
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
            DailyProperties propertiesLoader = DailyProperties.getInstance(propertiesFilePath);
            DailyJobStatusData dailyJobStatusData = new DailyJobStatusData();

            propertiesLoader.getBenchmarkConfigs().forEach(config -> {
                try {
                    List<DailyBenchmarkEntity> benchmarkEntity = dailyJobStatusData.getDroolsBenchmarkData(SourceFileExtension.CSV, SourceFileLocation.WEB, config);
                    benchmarkEntity.forEach(row -> reportRepository.save(row));

                    DailyJobStatusEntity statusEntity = dailyJobStatusData.getDroolsStatusData(SourceFileLocation.WEB, config);
                    statusRepository.save(statusEntity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
