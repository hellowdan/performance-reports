package org.benchmarks.application;

import java.io.IOException;

import org.benchmarks.dao.DailyJobStatusRepository;
import org.benchmarks.data.DailyJobStatusData;
import org.benchmarks.definitions.DailyJobProperties;
import org.benchmarks.exceptions.ExceptionsConstants;
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
public class LaunchDailyJobStatus implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaunchDailyJobStatus.class);

    @Autowired
    DailyJobStatusRepository statusRepository;

    public static void main(String[] args) {
        SpringApplication.run(LaunchDailyJobStatus.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String propertiesFilePath = "/jobs-daily-dashboard.properties";

        DailyJobProperties dailyJobProperties = DailyJobProperties.getInstance(propertiesFilePath);

        dailyJobProperties.getBenchmarkConfigs().forEach(config -> {
            try {
                DailyJobStatusData dailyJobStatusData = new DailyJobStatusData(config);
                DailyJobStatusEntity statusEntity = dailyJobStatusData.getDroolsStatusData();
                statusRepository.save(statusEntity);
            } catch (IOException e) {
                LOGGER.debug(String.format(ExceptionsConstants.FAILED_SAVING_TO_DATABASE, statusRepository.getClass().getCanonicalName()), e);
            }
        });
    }
}

