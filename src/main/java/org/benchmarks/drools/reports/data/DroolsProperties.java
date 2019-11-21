package org.benchmarks.drools.reports.data;

import java.io.IOException;

import org.benchmarks.reports.data.FileLocation;
import org.benchmarks.reports.util.ReportProperties;

public class DroolsProperties extends ReportProperties {

    static private DroolsProperties droolsProperties = null;

    private String newVersion;
    private String previousVersion;
    private String olderVersion;
    private String reportDate;
    private String author;
    private String emailAuthor;
    private String newVersionBuildtimeCsvPath;
    private String newVersionRuntimeCsvPath;
    private String newVersionBuildtimeJsonPath;
    private String newVersionRuntimeJsonPath;
    private FileLocation newVersionFileLocation;
    private String previousVersionBuildtimeCsvPath;
    private String previousVersionRuntimeCsvPath;
    private String previousVersionBuildtimeJsonPath;
    private String previousVersionRuntimeJsonPath;
    private FileLocation previousVersionFileLocation;
    private String olderVersionBuildtimeCsvPath;
    private String olderVersionRuntimeCsvPath;
    private String olderVersionBuildtimeJsonPath;
    private String olderVersionRuntimeJsonPath;
    private FileLocation olderVersionFileLocation;
    private String newVersionRepositoryFolderID;
    private String previousVersionRepositoryFolderID;
    private String olderVersionRepositoryFolderID;
    private String filesTitle;
    private String templateTitle;
    private String templateDocID;
    private String templateSheetID;
    private String resultParentFolderID;
    private String folderTitle;
    private String buildtimeResults;
    private String runtimeResults;
    private Boolean useCsv;
    private String googleApiKeyFile;
    private String googleAppApiKeyFile;

    public DroolsProperties(String filename) throws IOException {
        super(filename);

        this.newVersion = this.properties.getProperty("new_version");
        this.previousVersion = this.properties.getProperty("previous_version");
        this.olderVersion = this.properties.getProperty("older_version");
        this.reportDate = this.properties.getProperty("report_date");
        this.author = this.properties.getProperty("author");
        this.emailAuthor = this.properties.getProperty("email_author");
        this.newVersionBuildtimeCsvPath = this.properties.getProperty("new_version_buildtime_csv_path");
        this.newVersionRuntimeCsvPath = this.properties.getProperty("new_version_runtime_csv_path");
        this.newVersionBuildtimeJsonPath = this.properties.getProperty("new_version_buildtime_json_path");
        this.newVersionRuntimeJsonPath = this.properties.getProperty("new_version_runtime_json_path");
        this.newVersionFileLocation = getFileLocationEnum(this.properties.getProperty("new_version_file_location"));
        this.previousVersionBuildtimeCsvPath = this.properties.getProperty("previous_version_buildtime_csv_path");
        this.previousVersionRuntimeCsvPath = this.properties.getProperty("previous_version_runtime_csv_path");
        this.previousVersionBuildtimeJsonPath = this.properties.getProperty("previous_version_buildtime_json_path");
        this.previousVersionRuntimeJsonPath = this.properties.getProperty("previous_version_runtime_json_path");
        this.previousVersionFileLocation = getFileLocationEnum(this.properties.getProperty("previous_version_file_location"));
        this.olderVersionBuildtimeCsvPath = this.properties.getProperty("older_version_buildtime_csv_path");
        this.olderVersionRuntimeCsvPath = this.properties.getProperty("older_version_runtime_csv_path");
        this.olderVersionBuildtimeJsonPath = this.properties.getProperty("older_version_buildtime_json_path");
        this.olderVersionRuntimeJsonPath = this.properties.getProperty("older_version_runtime_json_path");
        this.olderVersionFileLocation = getFileLocationEnum(this.properties.getProperty("older_version_file_location"));
        this.newVersionRepositoryFolderID = this.properties.getProperty("new_version_repository_folder_id");
        this.previousVersionRepositoryFolderID = this.properties.getProperty("previous_version_repository_folder_id");
        this.olderVersionRepositoryFolderID = this.properties.getProperty("older_version_repository_folder_id");
        this.filesTitle = this.properties.getProperty("files_title");
        this.templateTitle = this.properties.getProperty("template_title");
        this.templateDocID = this.properties.getProperty("template_doc_id");
        this.templateSheetID = this.properties.getProperty("template_sheet_id");
        this.resultParentFolderID = this.properties.getProperty("result_parent_folder_id");
        this.folderTitle = this.properties.getProperty("folder_title");
        this.buildtimeResults = this.properties.getProperty("buildtime_results");
        this.runtimeResults = this.properties.getProperty("runtime_results");
        this.useCsv = this.properties.getProperty("use_csv").equals("true");
        this.googleApiKeyFile = this.properties.getProperty("google_api_key_file");
        this.googleAppApiKeyFile = this.properties.getProperty("google_app_api_key_file");

    }

    private FileLocation getFileLocationEnum(String fileLocation){
        FileLocation result = null;

        if (fileLocation.equals("WEB")){result = FileLocation.WEB;}
        else if (fileLocation.equals("LOCAL")){result = FileLocation.LOCAL;}
        else if (fileLocation.equals("DRIVE")){result = FileLocation.DRIVE;}

        return result;
    }

    /*singleton instance to be used anytime avoiding I/O*/
    static public DroolsProperties getInstance() throws IOException {
        if (droolsProperties == null) {
            droolsProperties = new DroolsProperties("/home/drosa/Documents/Workspace/performance-reports-new-requirements/performance-reports/src/main/java/org/benchmarks/drools/reports/resources/drools-reports.properties");
        }
        return droolsProperties;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public String getPreviousVersion() {return previousVersion;}

    public String getOlderVersion() {return olderVersion;}

    public String getReportDate() {
        return reportDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getEmailAuthor() {
        return emailAuthor;
    }

    public String getNewVersionBuildtimeCsvPath() {
        return newVersionBuildtimeCsvPath;
    }

    public String getNewVersionRuntimeCsvPath() {
        return newVersionRuntimeCsvPath;
    }

    public String getNewVersionBuildtimeJsonPath() {
        return newVersionBuildtimeJsonPath;
    }

    public String getNewVersionRuntimeJsonPath() {
        return newVersionRuntimeJsonPath;
    }

    public String getPreviousVersionBuildtimeCsvPath() {return previousVersionBuildtimeCsvPath;}

    public String getPreviousVersionRuntimeCsvPath() {return previousVersionRuntimeCsvPath;}

    public String getPreviousVersionBuildtimeJsonPath() {return previousVersionBuildtimeJsonPath;}

    public String getPreviousVersionRuntimeJsonPath() {return previousVersionRuntimeJsonPath;}

    public String getOlderVersionBuildtimeCsvPath() {return olderVersionBuildtimeCsvPath;}

    public String getOlderVersionRuntimeCsvPath() {return olderVersionRuntimeCsvPath;}

    public String getOlderVersionBuildtimeJsonPath() {return olderVersionBuildtimeJsonPath;}

    public String getOlderVersionRuntimeJsonPath() {return olderVersionRuntimeJsonPath;}

    public String getNewVersionRepositoryFolderID() {return newVersionRepositoryFolderID;}

    public String getPreviousVersionRepositoryFolderID() {return previousVersionRepositoryFolderID;}

    public String getOlderVersionRepositoryFolderID() {return olderVersionRepositoryFolderID;}

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

    public String getGoogleApiKeyFile() {return googleApiKeyFile;}

    public String getGoogleAppApiKeyFile() {return googleAppApiKeyFile;}

    public FileLocation getNewVersionFileLocation() {return newVersionFileLocation;}

    public FileLocation getPreviousVersionFileLocation() {return previousVersionFileLocation;}

    public FileLocation getOlderVersionFileLocation() {return olderVersionFileLocation;}

    public void setNewVersionBuildtimeCsvPath(String newVersionBuildtimeCsvPath) {
        this.newVersionBuildtimeCsvPath = newVersionBuildtimeCsvPath;
    }

    public void setNewVersionRuntimeCsvPath(String newVersionRuntimeCsvPath) {
        this.newVersionRuntimeCsvPath = newVersionRuntimeCsvPath;
    }

    public void setNewVersionBuildtimeJsonPath(String newVersionBuildtimeJsonPath) {
        this.newVersionBuildtimeJsonPath = newVersionBuildtimeJsonPath;
    }

    public void setNewVersionRuntimeJsonPath(String newVersionRuntimeJsonPath) {
        this.newVersionRuntimeJsonPath = newVersionRuntimeJsonPath;
    }

    public void setNewVersionFileLocation(FileLocation newVersionFileLocation) {
        this.newVersionFileLocation = newVersionFileLocation;
    }

    public void setPreviousVersionBuildtimeCsvPath(String previousVersionBuildtimeCsvPath) {
        this.previousVersionBuildtimeCsvPath = previousVersionBuildtimeCsvPath;
    }

    public void setPreviousVersionRuntimeCsvPath(String previousVersionRuntimeCsvPath) {
        this.previousVersionRuntimeCsvPath = previousVersionRuntimeCsvPath;
    }

    public void setPreviousVersionBuildtimeJsonPath(String previousVersionBuildtimeJsonPath) {
        this.previousVersionBuildtimeJsonPath = previousVersionBuildtimeJsonPath;
    }

    public void setPreviousVersionRuntimeJsonPath(String previousVersionRuntimeJsonPath) {
        this.previousVersionRuntimeJsonPath = previousVersionRuntimeJsonPath;
    }

    public void setPreviousVersionFileLocation(FileLocation previousVersionFileLocation) {
        this.previousVersionFileLocation = previousVersionFileLocation;
    }

    public void setOlderVersionBuildtimeCsvPath(String olderVersionBuildtimeCsvPath) {
        this.olderVersionBuildtimeCsvPath = olderVersionBuildtimeCsvPath;
    }

    public void setOlderVersionRuntimeCsvPath(String olderVersionRuntimeCsvPath) {
        this.olderVersionRuntimeCsvPath = olderVersionRuntimeCsvPath;
    }

    public void setOlderVersionBuildtimeJsonPath(String olderVersionBuildtimeJsonPath) {
        this.olderVersionBuildtimeJsonPath = olderVersionBuildtimeJsonPath;
    }

    public void setOlderVersionRuntimeJsonPath(String olderVersionRuntimeJsonPath) {
        this.olderVersionRuntimeJsonPath = olderVersionRuntimeJsonPath;
    }

    public void setOlderVersionFileLocation(FileLocation olderVersionFileLocation) {
        this.olderVersionFileLocation = olderVersionFileLocation;
    }
}
