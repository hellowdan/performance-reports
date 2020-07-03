package org.benchmarks.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.benchmarks.data.JobsStatusRow;

@Entity
@Table(name = "jobs_status_drools")
public class JobsStatusEntity {

    public Long getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getProduct() {
        return product;
    }

    public String getBranch() {
        return branch;
    }

    public String getUrl() {
        return url;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getLastBuildApiUrl() {
        return lastBuildApiUrl;
    }

    public int getActive() {
        return active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="job")
    private String job;

    @Column(name="product")
    private String product;

    @Column(name="branch")
    private String branch;

    @Column(name="url")
    private String url;

    @Column(name="api_Url")
    private String apiUrl;

    @Column(name="last_build_api_url")
    private String lastBuildApiUrl;

    @Column(name="active")
    private int active;

    public JobsStatusEntity() {
    }

    public JobsStatusEntity(JobsStatusRow resultRow) {
        this.job = resultRow.getJob();
        this.product = resultRow.getProduct();
        this.branch = resultRow.getBranch();
        this.url = resultRow.getUrl();
        this.apiUrl = resultRow.getApiUrl();
        this.lastBuildApiUrl = resultRow.getLastBuildApiUrl();
        this.active = resultRow.getActive();
    }

    @Override
    public String toString() {
        return "DroolsJenkinsDailyStatusEntity{" +
                "job='" + job + '\'' +
                ", product='" + product + '\'' +
                ", branch='" + branch + '\'' +
                ", url='" + url + '\'' +
                ", apiUrl=" + apiUrl +
                ", lastBuildApiUrl=" + lastBuildApiUrl +
                ", active=" + active +
                '}';
    }

}
