package org.benchmarks.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.benchmarks.data.DailyJobStatusRow;

@Entity
@Table(name = "jobs_daily_status_drools")
public class DailyJobStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String job;
    private String product;
    private String branch;
    private String lastBuildNumber;
    private String lastSuccessfulBuildNumber;
    private String lastFailedBuildNumber;
    private String lastBuildUrl;
    private String lastBuildStatus;
    private int lastBuildStatusFlag;
    private Double lastBuildDuration;
    private Timestamp lastBuildDateOfExecution;
    private int day;
    private int month;
    private int year;
    private LocalDateTime dateOfRecord;
    private Timestamp dateOfRecordTS;

    public DailyJobStatusEntity() {
    }

    public DailyJobStatusEntity(DailyJobStatusRow resultRow) {
        LocalDate localDate = LocalDate.now();
        this.job = resultRow.getBenchmark();
        this.product = resultRow.getProduct();
        this.branch = resultRow.getBranch();
        this.lastBuildNumber = resultRow.getLastBuild();
        this.lastSuccessfulBuildNumber = resultRow.getLastSuccessfulBuild();
        this.lastFailedBuildNumber = resultRow.getLastFailedBuild();
        this.lastBuildUrl = resultRow.getUrl();
        this.lastBuildStatus = resultRow.getLastBuildStatus();
        this.lastBuildStatusFlag = resultRow.getLastBuildStatusFlag();
        this.lastBuildDuration = resultRow.getLastBuildDuration();
        this.lastBuildDateOfExecution = resultRow.getLastBuildDateOfExecution();
        this.day = localDate.getDayOfMonth();
        this.month = localDate.getMonthValue();
        this.year = localDate.getYear();
        this.dateOfRecord = LocalDateTime.now();
        this.dateOfRecordTS = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "DroolsJenkinsDailyStatusEntity{" +
                "job='" + job + '\'' +
                ", product='" + product + '\'' +
                ", branch='" + branch + '\'' +
                ", lastBuildNumber='" + lastBuildNumber + '\'' +
                ", lastSuccessfulBuildNumber=" + lastSuccessfulBuildNumber +
                ", lastFailedBuildNumber=" + lastFailedBuildNumber +
                ", lastBuildUrl=" + lastBuildUrl +
                ", lastBuildStatus=" + lastBuildStatus +
                ", lastBuildStatusFlag=" + lastBuildStatusFlag +
                ", lastBuildDuration=" + lastBuildDuration +
                ", lastBuildDateOfExecution=" + lastBuildDateOfExecution +
                '}';
    }
}

