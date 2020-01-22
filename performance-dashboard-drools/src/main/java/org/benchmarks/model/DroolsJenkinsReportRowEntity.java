package org.benchmarks.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.benchmarks.definitions.JenkinsReportType;
import org.benchmarks.definitions.JenkinsReportVersion;

@Entity
@Table(name = "performance_monitoring_drools")
public class DroolsJenkinsReportRowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String fancyName;
    private String numberOfRules;
    private String nrOfRules;
    private String useCanonicalModel;
    private String rulesProviderId;
    private String matchRatio;
    private String score;
    private String reportType;
    private String reportVersion;
    private String version;
    private int day;
    private int month;
    private int year;
    private LocalDateTime dateOfRecord;
    private Timestamp dateOfRecordTS;

    public DroolsJenkinsReportRowEntity() {
    }

    public DroolsJenkinsReportRowEntity(String name, String fancyName, String numberOfRules, String nrOfRules, String useCanonicalModel, String rulesProviderId, String matchRatio, String score, JenkinsReportType reportType, JenkinsReportVersion reportVersion, String version) {
        LocalDate localDate = LocalDate.now();
        this.name = name;
        this.fancyName = fancyName;
        this.numberOfRules = numberOfRules;
        this.nrOfRules = nrOfRules;
        this.useCanonicalModel = useCanonicalModel;
        this.rulesProviderId = rulesProviderId;
        this.matchRatio = matchRatio;
        this.score = score;
        this.reportType = reportType.getFileType();
        this.reportVersion = reportVersion.toString();
        this.version = version;
        this.day = localDate.getDayOfMonth();
        this.month = localDate.getMonthValue();
        this.year = localDate.getYear();
        this.dateOfRecord = LocalDateTime.now();
        this.dateOfRecordTS = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "DroolsJenkinsReportRowDB{" +
                "name='" + name + '\'' +
                ", fancyName=" + fancyName + '\'' +
                ", numberOfRules='" + numberOfRules + '\'' +
                ", nrOfRules='" + nrOfRules + '\'' +
                ", useCanonicalModel=" + useCanonicalModel +
                ", rulesProviderId='" + rulesProviderId + '\'' +
                ", matchRatio='" + matchRatio + '\'' +
                ", score=" + score +
                '}';
    }
}
