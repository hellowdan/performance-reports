package org.benchmarks.application;

import java.io.IOException;

import org.benchmarks.dao.DroolsJenkinsDailyStatusRepository;
import org.benchmarks.data.DroolsDailyData;
import org.benchmarks.definitions.DroolsDailyPropertiesLoader;
import org.benchmarks.definitions.JenkinsReportLocation;
import org.benchmarks.model.DroolsJenkinsDailyStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"org.benchmarks"})
@ComponentScan({"org.benchmarks"})
@EntityScan("org.benchmarks")
@EnableJpaRepositories("org.benchmarks")
public class LauncherDailyStatus implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LauncherDailyStatus.class, args);
    }

    @Autowired
    DroolsJenkinsDailyStatusRepository statusRepository;

    @Override
    public void run(String... args) throws Exception {
        DroolsDailyData droolsDailyData = new DroolsDailyData();
        String propertiesFilePath = "/drools-daily-dashboard.properties";

        DroolsDailyPropertiesLoader propertiesLoader = DroolsDailyPropertiesLoader.getInstance(propertiesFilePath);

        propertiesLoader.getBenchmarkConfigs().forEach(config -> {
            try {
                DroolsJenkinsDailyStatusEntity statusEntity = droolsDailyData.getDroolsStatusData(JenkinsReportLocation.WEB, config);
                statusRepository.save(statusEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
