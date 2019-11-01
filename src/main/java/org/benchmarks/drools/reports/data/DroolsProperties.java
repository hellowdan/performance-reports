package org.benchmarks.drools.reports.data;

import java.io.IOException;
import org.benchmarks.reports.util.ReportProperties;

public class DroolsProperties extends ReportProperties {

    static private DroolsProperties droolsProperties = null;

    private String newVersion;
    private String oldVersion;
    private String reportDate;
    private String author;
    private String emailAuthor;
    private String buildtimeCsvPath;
    private String runtimeCsvPath;
    private String buildtimeJsonPath;
    private String runtimeJsonPath;
    private String filesTitle;
    private String templateTitle;
    private String templateDocID;
    private String templateSheetID;
    private String resultParentFolderID;
    private String folderTitle;
    private String buildtimeResults;
    private String runtimeResults;
    private Boolean useCsv;

    public DroolsProperties(String filename) throws IOException {
        super(filename);

        this.newVersion = this.properties.getProperty("new_version");
        this.oldVersion = this.properties.getProperty("old_version");
        this.reportDate = this.properties.getProperty("report_date");
        this.author = this.properties.getProperty("author");
        this.emailAuthor = this.properties.getProperty("email_author");
        this.buildtimeCsvPath = this.properties.getProperty("buildtime_csv_path");
        this.runtimeCsvPath = this.properties.getProperty("runtime_csv_path");
        this.buildtimeJsonPath = this.properties.getProperty("buildtime_json_path");
        this.runtimeJsonPath = this.properties.getProperty("runtime_json_path");
        this.filesTitle = this.properties.getProperty("files_title");
        this.templateTitle = this.properties.getProperty("template_title");
        this.templateDocID = this.properties.getProperty("template_doc_id");
        this.templateSheetID = this.properties.getProperty("template_sheet_id");
        this.resultParentFolderID = this.properties.getProperty("result_parent_folder_id");
        this.folderTitle = this.properties.getProperty("folder_title");
        this.buildtimeResults = this.properties.getProperty("buildtime_results");
        this.runtimeResults = this.properties.getProperty("runtime_results");
        this.useCsv = this.properties.getProperty("use_csv") == "true";
    }

    /*singleton instance to be used anytime avoiding I/O*/
    static public DroolsProperties getInstance() throws IOException {
        if (droolsProperties == null) {
            droolsProperties = new DroolsProperties("/home/drosa/Documents/Workspace/performance-reports/src/main/java/org/benchmarks/drools/reports/resources/drools-reports.properties");
        }
        return droolsProperties;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public String getOldVersion() {
        return oldVersion;
    }

    public String getReportDate() {
        return reportDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getEmailAuthor() {
        return emailAuthor;
    }

    public String getBuildtimeCsvPath() {
        return buildtimeCsvPath;
    }

    public String getRuntimeCsvPath() {
        return runtimeCsvPath;
    }

    public String getBuildtimeJsonPath() {
        return buildtimeJsonPath;
    }

    public String getRuntimeJsonPath() {
        return runtimeJsonPath;
    }

    public String getFilesTitle() {
        return filesTitle;
    }

    public String getTemplateTitle() {
        return templateTitle;
    }

    public String getTemplateDocID() {
        return templateDocID;
    }

    public String getTemplateSheetID() {
        return templateSheetID;
    }

    public String getResultParentFolderID() {
        return resultParentFolderID;
    }

    public String getFolderTitle() {
        return folderTitle;
    }

    public String getBuildtimeResults() {
        return buildtimeResults;
    }

    public String getRuntimeResults() {
        return runtimeResults;
    }

    public Boolean getUseCsv() {return useCsv;}

    public void setUseCsv(Boolean useCsv) {this.useCsv = useCsv;}
}
