//package org.benchmarks.dao;
//
//import java.io.IOException;
//import java.util.List;
//
//import com.google.common.collect.Lists;
//import org.benchmarks.data.DailyJobStatusData;
//import org.benchmarks.exceptions.ExceptionsConstants;
//import org.benchmarks.model.DailyJobStatusEntity;
//import org.hamcrest.CoreMatchers;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@TestPropertySource(locations="classpath:application-test.properties")
//@SpringBootApplication(scanBasePackages = {"org.benchmarks"})
//@ComponentScan({"org.benchmarks"})
//@EntityScan("org.benchmarks")
//@EnableJpaRepositories("org.benchmarks")
//public class DailyStatusRepositoryTest {
//
//    @Autowired
//    DailyJobStatusRepository statusRepository;
//
//    @Before
//    public void cleanUpDb() {
//        statusRepository.deleteAll();
//    }
//
//    @Test
//    public void testSave() throws IOException {
//        String propertiesFilePath = "/drools-daily-dashboard-test.properties";
//
//        DailyJobProperties dailyJobProperties = DailyJobProperties.getInstance(propertiesFilePath);
//
//        dailyJobProperties.getBenchmarkConfigs().forEach(config -> {
//            try {
//                DailyJobStatusData dailyJobStatusData = new DailyJobStatusData(config);
//                DailyJobStatusEntity statusEntity = dailyJobStatusData.getDroolsStatusData();
//                statusRepository.save(statusEntity);
//            } catch (IOException e) {
//                Assert.fail(String.format(ExceptionsConstants.FAILED_SAVING_TO_DATABASE, statusRepository.getClass().getCanonicalName()));
//            }
//        });
//
//        List<DailyJobStatusEntity> dailyJobStatusEntities = Lists.newArrayList(statusRepository.findAll());
//        Assert.assertThat(dailyJobStatusEntities.size(), CoreMatchers.is(1));
//    }
//
//    @Test
//    public void testLiveSave() throws IOException {
//        String propertiesFilePath = "/drools-daily-dashboard-live-test.properties";
//
//        DailyJobProperties dailyJobProperties = DailyJobProperties.getInstance(propertiesFilePath);
//
//        dailyJobProperties.getBenchmarkConfigs().forEach(config -> {
//            try {
//                DailyJobStatusData dailyJobStatusData = new DailyJobStatusData(config);
//                DailyJobStatusEntity statusEntity = dailyJobStatusData.getDroolsStatusData();
//                statusRepository.save(statusEntity);
//            } catch (IOException e) {
//                Assert.fail(String.format(ExceptionsConstants.FAILED_SAVING_TO_DATABASE, statusRepository.getClass().getCanonicalName()));
//            }
//        });
//
//        List<DailyJobStatusEntity> dailyJobStatusEntities = Lists.newArrayList(statusRepository.findAll());
//        Assert.assertThat(dailyJobStatusEntities.size(), CoreMatchers.is(7));
//    }
//}
