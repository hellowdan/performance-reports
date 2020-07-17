package org.benchmarks.controller;

import java.util.List;

import org.benchmarks.model.JobsStatusEntity;
import org.benchmarks.service.JobsStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping(value="jobsStatus")
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
public class JobsStatusController {

    @Autowired
    private JobsStatusService jobsStatusService;

    @GetMapping("jobs")
    public ResponseEntity<List<JobsStatusEntity>> getAllJobs() {
        List<JobsStatusEntity> jobsStatusEntityList = jobsStatusService.getAllJobs();
        return new ResponseEntity<List<JobsStatusEntity>>(jobsStatusEntityList, HttpStatus.OK);
    }

    @GetMapping("job/{id}")
    public ResponseEntity<JobsStatusEntity> getJob(@PathVariable("id") int id) {
        JobsStatusEntity jobsStatusEntity = jobsStatusService.getJobById(id);
        return new ResponseEntity<JobsStatusEntity>(jobsStatusEntity, HttpStatus.OK);
    }

    @PostMapping(value="add-job", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Void> addJob(@RequestBody JobsStatusEntity jobsStatusEntity, UriComponentsBuilder builder) {
        jobsStatusService.saveJob(jobsStatusEntity);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/job/{id}").buildAndExpand(jobsStatusEntity.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
}
