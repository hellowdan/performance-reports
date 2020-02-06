package org.benchmarks.dao;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;
import org.benchmarks.data.DailyJobStatusData;
import org.benchmarks.definitions.DailyProperties;
import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.exceptions.ExceptionsConstants;
import org.benchmarks.model.DailyBenchmarkEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootApplication(scanBasePackages = {"org.benchmarks"})
@ComponentScan({"org.benchmarks"})
@EntityScan("org.benchmarks")
@EnableJpaRepositories("org.benchmarks")
public class DailyBenchmarkRepositoryTest {

    @Autowired
    DailyBenchmarkRepository reportRepository;

    @Before
    public void cleanUpDb() {
        reportRepository.deleteAll();
    }

    @Test
    public void testSave() throws IOException {
        String propertiesFilePath = "/drools-daily-dashboard-test.properties";

        DailyProperties dailyProperties = DailyProperties.getInstance(propertiesFilePath);
        DailyJobStatusData dailyJobStatusData = new DailyJobStatusData();

        dailyProperties.getBenchmarkConfigs().forEach(config -> {
            try {
                List<DailyBenchmarkEntity> benchmarkEntity = dailyJobStatusData.getDroolsBenchmarkData(SourceFileExtension.CSV, dailyProperties.getSourceFileLocation(), config);
                benchmarkEntity.forEach(row -> reportRepository.save(row));
            } catch (IOException e) {
                fail(String.format(ExceptionsConstants.FAILED_SAVING_TO_DATABASE, reportRepository.getClass().getCanonicalName()));
            }
        });

        List<DailyBenchmarkEntity> dailyBenchmarkEntities = Lists.newArrayList(reportRepository.findAll());
        assertThat(dailyBenchmarkEntities.size(), is(10));
    }

    @Test
    public void testLiveSave() throws IOException {
        String propertiesFilePath = "/drools-daily-dashboard-live-test.properties";

        DailyProperties dailyProperties = DailyProperties.getInstance(propertiesFilePath);
        DailyJobStatusData dailyJobStatusData = new DailyJobStatusData();

        dailyProperties.getBenchmarkConfigs().forEach(config -> {
            try {
                List<DailyBenchmarkEntity> benchmarkEntity = dailyJobStatusData.getDroolsBenchmarkData(SourceFileExtension.CSV, dailyProperties.getSourceFileLocation(), config);
                benchmarkEntity.forEach(row -> reportRepository.save(row));
            } catch (IOException e) {
                fail(String.format(ExceptionsConstants.FAILED_SAVING_TO_DATABASE, reportRepository.getClass().getCanonicalName()));
            }
        });

        List<DailyBenchmarkEntity> dailyBenchmarkEntities = Lists.newArrayList(reportRepository.findAll());
        assertThat(dailyBenchmarkEntities.size(), is(1537));
    }

}
