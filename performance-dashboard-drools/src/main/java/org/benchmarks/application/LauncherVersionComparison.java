package org.benchmarks.application;

import java.util.List;

import org.benchmarks.data.DroolsData;
import org.benchmarks.model.DroolsJenkinsReportRowEntity;
import org.benchmarks.dao.DroolsJenkinsReportRowRepository;
import org.benchmarks.definitions.DroolsPropertiesLoader;
import org.benchmarks.definitions.JenkinsReportFileExtension;
import org.benchmarks.definitions.JenkinsReportVersion;
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
public class LauncherVersionComparison implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LauncherVersionComparison.class, args);
    }

    @Autowired
    DroolsJenkinsReportRowRepository repository;

    @Override
    public void run(String... args) throws Exception {
        DroolsData droolsData = new DroolsData();
        String propertiesFilePath = "/drools-dashboard.properties";

        List<DroolsJenkinsReportRowEntity> buildtimeCurrent = droolsData.getDroolsBuildtimeData(JenkinsReportFileExtension.CSV, JenkinsReportVersion.CURRENT, DroolsPropertiesLoader.getInstance(propertiesFilePath).getCurrentVersion(), DroolsPropertiesLoader.getInstance(propertiesFilePath).getCurrentVersionJenkinsReportLocation(), DroolsPropertiesLoader.getInstance(propertiesFilePath));
        buildtimeCurrent.forEach(row -> repository.save(row));

        List<DroolsJenkinsReportRowEntity> buildtimePrevious = droolsData.getDroolsBuildtimeData(JenkinsReportFileExtension.CSV, JenkinsReportVersion.PREVIOUS, DroolsPropertiesLoader.getInstance(propertiesFilePath).getPreviousVersion(), DroolsPropertiesLoader.getInstance(propertiesFilePath).getCurrentVersionJenkinsReportLocation(), DroolsPropertiesLoader.getInstance(propertiesFilePath));
        buildtimePrevious.forEach(row -> repository.save(row));

        List<DroolsJenkinsReportRowEntity> buildtimeOlder = droolsData.getDroolsBuildtimeData(JenkinsReportFileExtension.CSV, JenkinsReportVersion.OLDER, DroolsPropertiesLoader.getInstance(propertiesFilePath).getOlderVersion(), DroolsPropertiesLoader.getInstance(propertiesFilePath).getCurrentVersionJenkinsReportLocation(), DroolsPropertiesLoader.getInstance(propertiesFilePath));
        buildtimeOlder.forEach(row -> repository.save(row));

        List<DroolsJenkinsReportRowEntity> runtimeCurrent = droolsData.getDroolsRuntimeData(JenkinsReportFileExtension.CSV, JenkinsReportVersion.CURRENT, DroolsPropertiesLoader.getInstance(propertiesFilePath).getCurrentVersion(), DroolsPropertiesLoader.getInstance(propertiesFilePath).getCurrentVersionJenkinsReportLocation(), DroolsPropertiesLoader.getInstance(propertiesFilePath));
        runtimeCurrent.forEach(row -> repository.save(row));

        List<DroolsJenkinsReportRowEntity> runtimePrevious = droolsData.getDroolsRuntimeData(JenkinsReportFileExtension.CSV, JenkinsReportVersion.PREVIOUS, DroolsPropertiesLoader.getInstance(propertiesFilePath).getPreviousVersion(), DroolsPropertiesLoader.getInstance(propertiesFilePath).getCurrentVersionJenkinsReportLocation(), DroolsPropertiesLoader.getInstance(propertiesFilePath));
        runtimePrevious.forEach(row -> repository.save(row));

        List<DroolsJenkinsReportRowEntity> runtimeOlder = droolsData.getDroolsRuntimeData(JenkinsReportFileExtension.CSV, JenkinsReportVersion.OLDER, DroolsPropertiesLoader.getInstance(propertiesFilePath).getOlderVersion(), DroolsPropertiesLoader.getInstance(propertiesFilePath).getCurrentVersionJenkinsReportLocation(), DroolsPropertiesLoader.getInstance(propertiesFilePath));
        runtimeOlder.forEach(row -> repository.save(row));
    }
}
