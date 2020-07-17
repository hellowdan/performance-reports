package org.benchmarks.service;

import java.util.ArrayList;
import java.util.List;

import org.benchmarks.dao.JobsStatusRepository;
import org.benchmarks.model.JobsStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobsStatusService {

    @Autowired
    JobsStatusRepository jobsStatusRepository;

    public List<JobsStatusEntity> getAllJobs(){
        List<JobsStatusEntity> jobsStatus = new ArrayList<>();
        jobsStatusRepository.findAll().forEach(e -> jobsStatus.add(e));
        return jobsStatus;
    }

    public JobsStatusEntity getJobById(long jobsStatusId) {
        JobsStatusEntity jobsStatusEntity = jobsStatusRepository.findById(jobsStatusId).get();
        return jobsStatusEntity;
    }

    public void saveJob(JobsStatusEntity jobsStatusEntity) {
        jobsStatusRepository.save(jobsStatusEntity);
    }

    public void deleteJob(int jobsStatusId) {
        jobsStatusRepository.delete(getJobById(jobsStatusId));
    }

}
