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
    private String newVersionBuildtimePath;
    private String newVersionRuntimePath;
    private FileLocation newVersionFileLocation;
    private String previousVersionBuildtimePath;
    private String previousVersionRuntimePath;
    private FileLocation previousVersionFileLocation;
    private String olderVersionBuildtimePath;
    private String olderVersionRuntimePath;
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
        this.newVersionBuildtimePath = this.properties.getProperty("new_version_buildtime_path");
        this.newVersionRuntimePath = this.properties.getProperty("new_version_runtime_path");
        this.newVersionFileLocation = getFileLocationEnum(this.properties.getProperty("new_version_file_location"));
        this.previousVersionBuildtimePath = this.properties.getProperty("previous_version_buildtime_path");
        this.previousVersionRuntimePath = this.properties.getProperty("previous_version_runtime_path");
        this.previousVersionFileLocation = getFileLocationEnum(this.properties.getProperty("previous_version_file_location"));
        this.olderVersionBuildtimePath = this.properties.getProperty("older_version_buildtime_path");
        this.olderVersionRuntimePath = this.properties.getProperty("older_version_runtime_path");
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

    public String getNewVersionBuildtimePath() {
        return newVersionBuildtimePath;
    }

    public String getNewVersionRuntimePath() {
        return newVersionRuntimePath;
    }

    public String getPreviousVersionBuildtimePath() {return previousVersionBuildtimePath;}

    public String getPreviousVersionRuntimePath() {return previousVersionRuntimePath;}

    public String getOlderVersionBuildtimePath() {return olderVersionBuildtimePath;}

    public String getOlderVersionRuntimePath() {return olderVersionRuntimePath;}

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

    public String getGoogleApiKeyFile() {return googleApiKeyFile;}

    public String getGoogleAppApiKeyFile() {return googleAppApiKeyFile;}

    public FileLocation getNewVersionFileLocation() {return newVersionFileLocation;}

    public FileLocation getPreviousVersionFileLocation() {return previousVersionFileLocation;}

    public FileLocation getOlderVersionFileLocation() {return olderVersionFileLocation;}

    public void setNewVersionBuildtimePath(String newVersionBuildtimePath) {
        this.newVersionBuildtimePath = newVersionBuildtimePath;
    }

    public void setNewVersionRuntimePath(String newVersionRuntimePath) {
        this.newVersionRuntimePath = newVersionRuntimePath;
    }

    public void setNewVersionFileLocation(FileLocation newVersionFileLocation) {
        this.newVersionFileLocation = newVersionFileLocation;
    }

    public void setPreviousVersionBuildtimePath(String previousVersionBuildtimePath) {
        this.previousVersionBuildtimePath = previousVersionBuildtimePath;
    }

    public void setPreviousVersionRuntimePath(String previousVersionRuntimePath) {
        this.previousVersionRuntimePath = previousVersionRuntimePath;
    }

    public void setPreviousVersionFileLocation(FileLocation previousVersionFileLocation) {
        this.previousVersionFileLocation = previousVersionFileLocation;
    }

    public void setOlderVersionBuildtimePath(String olderVersionBuildtimePath) {
        this.olderVersionBuildtimePath = olderVersionBuildtimePath;
    }

    public void setOlderVersionRuntimePath(String olderVersionRuntimePath) {
        this.olderVersionRuntimePath = olderVersionRuntimePath;
    }

    public void setOlderVersionFileLocation(FileLocation olderVersionFileLocation) {
        this.olderVersionFileLocation = olderVersionFileLocation;
    }
}
