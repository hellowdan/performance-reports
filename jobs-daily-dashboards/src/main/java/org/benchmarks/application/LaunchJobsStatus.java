package org.benchmarks.application;

import org.benchmarks.dao.JobsStatusRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.benchmarks"})
@EnableJpaRepositories(basePackageClasses= {JobsStatusRepository.class})
public class LaunchJobsStatus {

    public static void main(String[] args) {
        SpringApplication.run(LaunchJobsStatus.class, args);
    }
}
