import java.net.URI;

import org.benchmarks.model.JobsStatusEntity;
import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"org.benchmarks"})
public class JobsStatusTest {

    public void addJob() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/jobsStatus/job";
        JobsStatusEntity jobsStatusEntity = new JobsStatusEntity();
        jobsStatusEntity.setJob("JOB ADD TEST");
        HttpEntity<JobsStatusEntity> requestEntity = new HttpEntity<JobsStatusEntity>(jobsStatusEntity, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
    }

    @Test
    public void getAllJobsTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/jobsStatus/jobs";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<JobsStatusEntity[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, JobsStatusEntity[].class);
        JobsStatusEntity[] jobs = responseEntity.getBody();
        for (JobsStatusEntity job : jobs) {
            System.out.println("Id:" + job.getId() + ", Title:" + job.getJob());
        }
    }

    @Test
    public void updateJob() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/jobsStatus/job";
        JobsStatusEntity jobsStatusEntity = new JobsStatusEntity();
        jobsStatusEntity.setId((long) 1);
        jobsStatusEntity.setJob("JOB UPDATE TEST");
        HttpEntity<JobsStatusEntity> requestEntity = new HttpEntity<JobsStatusEntity>(jobsStatusEntity, headers);
        restTemplate.put(url, requestEntity);
    }

    @Test
    public void deleteJob() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/jobsStatus/job/{id}";
        HttpEntity<JobsStatusEntity> requestEntity = new HttpEntity<JobsStatusEntity>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);
    }

}
