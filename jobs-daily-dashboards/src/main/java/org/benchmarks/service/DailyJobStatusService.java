package org.benchmarks.service;

import java.io.IOException;
import java.util.List;

import org.benchmarks.dao.DailyJobStatusRepository;
import org.benchmarks.data.DailyJobStatusData;
import org.benchmarks.exceptions.ExceptionsConstants;
import org.benchmarks.model.DailyJobStatusEntity;
import org.benchmarks.model.JobsStatusEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyJobStatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DailyJobStatusService.class);

    @Autowired
    DailyJobStatusRepository dailyJobStatusRepository;

    @Autowired
    JobsStatusService jobsStatusService;

    private List<JobsStatusEntity> jobsStatusEntities;

    private Boolean result;

    private void setResult(Boolean result) {
        this.result = result;
    }

    private Boolean getResult(){
        return this.result;
    }

    public Boolean runStatus(){
        setResult(false);
        this.jobsStatusEntities = jobsStatusService.getAllJobs();

        jobsStatusEntities.forEach(j -> {
            try {
                DailyJobStatusData dailyJobStatusData = new DailyJobStatusData(j);
                DailyJobStatusEntity statusEntity = dailyJobStatusData.getDroolsStatusData();
                dailyJobStatusRepository.save(statusEntity);
                setResult(true);
            } catch (IOException e) {
                LOGGER.debug(String.format(ExceptionsConstants.FAILED_SAVING_TO_DATABASE, dailyJobStatusRepository.getClass().getCanonicalName()), e);
            }
        });

        return getResult();
    }

}
