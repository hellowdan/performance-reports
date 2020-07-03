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
    JobsStatusRepository repository;

    public List<JobsStatusEntity> getAllJobs(){
        List<JobsStatusEntity> jobsStatus = new ArrayList<>();
        repository.findAll().forEach(e -> jobsStatus.add(e));
        return jobsStatus;
    }

    public JobsStatusEntity getJobById(long jobsStatusId) {
        JobsStatusEntity jobsStatusEntity = repository.findById(jobsStatusId).get();
        return jobsStatusEntity;
    }

    public void saveJob(JobsStatusEntity jobsStatusEntity) {
        repository.save(jobsStatusEntity);
    }

    public void deleteJob(int jobsStatusId) {
        repository.delete(getJobById(jobsStatusId));
    }

}
