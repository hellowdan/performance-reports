package org.benchmarks.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "performance_daily_dashboard_drools")
public class DailyBenchmarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String job;
    private String name;
    private String score;
    private int day;
    private int month;
    private int year;
    private LocalDateTime dateOfRecord;
    private Timestamp dateOfRecordTS;

    public DailyBenchmarkEntity() {
    }

    public DailyBenchmarkEntity(String job, String name, String score) {
        LocalDate localDate = LocalDate.now();
        this.job = job;
        this.name = name;
        this.score = score;
        this.day = localDate.getDayOfMonth();
        this.month = localDate.getMonthValue();
        this.year = localDate.getYear();
        this.dateOfRecord = LocalDateTime.now();
        this.dateOfRecordTS = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "DroolsJenkinsDailyReportRowEntity{" +
                "job='" + job + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
