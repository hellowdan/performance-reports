//package org.benchmarks.application;
//
//import java.util.List;
//
//import org.benchmarks.data.BenchmarkDashboardData;
//import org.benchmarks.definitions.StaticVersion;
//import org.benchmarks.model.BenchmarkDashboardEntity;
//import org.benchmarks.dao.BenchmarkDashboardRepository;
//import org.benchmarks.definitions.DroolsReportProperties;
//import org.benchmarks.definitions.SourceFileExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//@SpringBootApplication(scanBasePackages={"org.benchmarks"})
//@ComponentScan({"org.benchmarks"})
//@EntityScan("org.benchmarks")
//@EnableJpaRepositories("org.benchmarks")
//public class LauncherBenchmarkDashboard implements CommandLineRunner {
//
//    public static void main(String[] args) {
//        SpringApplication.run(LauncherBenchmarkDashboard.class, args);
//    }
//
//    @Autowired
//    BenchmarkDashboardRepository repository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        BenchmarkDashboardData benchmarkDashboardData = new BenchmarkDashboardData();
//        String propertiesFilePath = "/drools-dashboard.properties";
//
//        List<BenchmarkDashboardEntity> buildtimeCurrent = benchmarkDashboardData.getDroolsBuildtimeData(SourceFileExtension.CSV, StaticVersion.CURRENT, DroolsReportProperties.getInstance(propertiesFilePath).getCurrentVersion(), DroolsReportProperties.getInstance(propertiesFilePath).getCurrentVersionSourceFileLocation(), DroolsReportProperties.getInstance(propertiesFilePath));
//        buildtimeCurrent.forEach(row -> repository.save(row));
//
//        List<BenchmarkDashboardEntity> buildtimePrevious = benchmarkDashboardData.getDroolsBuildtimeData(SourceFileExtension.CSV, StaticVersion.PREVIOUS, DroolsReportProperties.getInstance(propertiesFilePath).getPreviousVersion(), DroolsReportProperties.getInstance(propertiesFilePath).getCurrentVersionSourceFileLocation(), DroolsReportProperties.getInstance(propertiesFilePath));
//        buildtimePrevious.forEach(row -> repository.save(row));
//
//        List<BenchmarkDashboardEntity> buildtimeOlder = benchmarkDashboardData.getDroolsBuildtimeData(SourceFileExtension.CSV, StaticVersion.OLDER, DroolsReportProperties.getInstance(propertiesFilePath).getOlderVersion(), DroolsReportProperties.getInstance(propertiesFilePath).getCurrentVersionSourceFileLocation(), DroolsReportProperties.getInstance(propertiesFilePath));
//        buildtimeOlder.forEach(row -> repository.save(row));
//
//        List<BenchmarkDashboardEntity> runtimeCurrent = benchmarkDashboardData.getDroolsRuntimeData(SourceFileExtension.CSV, StaticVersion.CURRENT, DroolsReportProperties.getInstance(propertiesFilePath).getCurrentVersion(), DroolsReportProperties.getInstance(propertiesFilePath).getCurrentVersionSourceFileLocation(), DroolsReportProperties.getInstance(propertiesFilePath));
//        runtimeCurrent.forEach(row -> repository.save(row));
//
//        List<BenchmarkDashboardEntity> runtimePrevious = benchmarkDashboardData.getDroolsRuntimeData(SourceFileExtension.CSV, StaticVersion.PREVIOUS, DroolsReportProperties.getInstance(propertiesFilePath).getPreviousVersion(), DroolsReportProperties.getInstance(propertiesFilePath).getCurrentVersionSourceFileLocation(), DroolsReportProperties.getInstance(propertiesFilePath));
//        runtimePrevious.forEach(row -> repository.save(row));
//
//        List<BenchmarkDashboardEntity> runtimeOlder = benchmarkDashboardData.getDroolsRuntimeData(SourceFileExtension.CSV, StaticVersion.OLDER, DroolsReportProperties.getInstance(propertiesFilePath).getOlderVersion(), DroolsReportProperties.getInstance(propertiesFilePath).getCurrentVersionSourceFileLocation(), DroolsReportProperties.getInstance(propertiesFilePath));
//        runtimeOlder.forEach(row -> repository.save(row));
//    }
//}
