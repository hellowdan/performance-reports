package org.benchmarks.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.benchmarks.data.DailyJobStatusRow;

@Entity
@Table(name = "jobs_daily_status_drools")
public class DailyJobStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="last_build_number")
    private String lastBuildNumber;

    @Column(name="last_successful_build_number")
    private String lastSuccessfulBuildNumber;

    @Column(name="last_failed_build_number")
    private String lastFailedBuildNumber;

    @Column(name="last_build_url")
    private String lastBuildUrl;

    @Column(name="last_build_status")
    private String lastBuildStatus;

    @Column(name="last_build_status_flag")
    private int lastBuildStatusFlag;

    @Column(name="last_build_duration")
    private Double lastBuildDuration;

    @Column(name="last_build_date_of_execution")
    private Timestamp lastBuildDateOfExecution;

    @Column(name="day")
    private int day;

    @Column(name="month")
    private int month;

    @Column(name="year")
    private int year;

    @Column(name="date_of_record")
    private LocalDateTime dateOfRecord;

    @Column(name="date_of_recordts")
    private Timestamp dateOfRecordTS;

    @ManyToOne
    @JoinColumn(name = "jobs_status_drools_id")
    private JobsStatusEntity jobsStatusEntity;

    public DailyJobStatusEntity() {
    }

    public DailyJobStatusEntity(DailyJobStatusRow resultRow, JobsStatusEntity jobsStatusEntity) {
        LocalDate localDate = LocalDate.now();
        this.lastBuildUrl = resultRow.getUrl();
        this.lastBuildNumber = resultRow.getLastBuild();
        this.lastSuccessfulBuildNumber = resultRow.getLastSuccessfulBuild();
        this.lastFailedBuildNumber = resultRow.getLastFailedBuild();
        this.lastBuildStatus = resultRow.getLastBuildStatus();
        this.lastBuildStatusFlag = resultRow.getLastBuildStatusFlag();
        this.lastBuildDuration = resultRow.getLastBuildDuration();
        this.lastBuildDateOfExecution = resultRow.getLastBuildDateOfExecution();
        this.day = localDate.getDayOfMonth();
        this.month = localDate.getMonthValue();
        this.year = localDate.getYear();
        this.dateOfRecord = LocalDateTime.now();
        this.dateOfRecordTS = new Timestamp(System.currentTimeMillis());
        this.jobsStatusEntity = jobsStatusEntity;
    }

    @Override
    public String toString() {
        return "DroolsJenkinsDailyStatusEntity{" +
                "lastBuildNumber='" + lastBuildNumber + '\'' +
                ", lastSuccessfulBuildNumber=" + lastSuccessfulBuildNumber +
                ", lastFailedBuildNumber=" + lastFailedBuildNumber +
                ", lastBuildUrl=" + lastBuildUrl +
                ", lastBuildStatus=" + lastBuildStatus +
                ", lastBuildStatusFlag=" + lastBuildStatusFlag +
                ", lastBuildDuration=" + lastBuildDuration +
                ", lastBuildDateOfExecution=" + lastBuildDateOfExecution +
                ", jobsStatusEntity=" + jobsStatusEntity.toString() +
                '}';
    }
}

