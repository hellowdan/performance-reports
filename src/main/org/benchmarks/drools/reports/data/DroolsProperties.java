package org.benchmarks.drools.reports.data;

import java.io.IOException;

import org.benchmarks.reports.data.FileExtension;
import org.benchmarks.reports.data.FileLocation;
import org.benchmarks.reports.data.ReportProperties;

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
    private FileExtension newVersionFileExtension;
    private String previousVersionBuildtimePath;
    private String previousVersionRuntimePath;
    private FileLocation previousVersionFileLocation;
    private FileExtension previousVersionFileExtension;
    private String olderVersionBuildtimePath;
    private String olderVersionRuntimePath;
    private FileLocation olderVersionFileLocation;
    private FileExtension olderVersionFileExtension;
    private String newVersionRepositoryFolderID;
    private String previousVersionRepositoryFolderID;
    private String olderVersionRepositoryFolderID;
    private String filesTitle;
    private String templateTitle;
    private String templateDocID;
    private String templateSheetID;
    private String resultParentFolderID;
    private String folderTitle;
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
        this.newVersionFileLocation = FileLocation.getLocation(this.properties.getProperty("new_version_file_location"));
        this.newVersionFileExtension = FileExtension.getExtension(this.properties.getProperty("new_version_file_extension"));
        this.previousVersionBuildtimePath = this.properties.getProperty("previous_version_buildtime_path");
        this.previousVersionRuntimePath = this.properties.getProperty("previous_version_runtime_path");
        this.previousVersionFileLocation = FileLocation.getLocation(this.properties.getProperty("previous_version_file_location"));
        this.previousVersionFileExtension = FileExtension.getExtension(this.properties.getProperty("previous_version_file_extension"));
        this.olderVersionBuildtimePath = this.properties.getProperty("older_version_buildtime_path");
        this.olderVersionRuntimePath = this.properties.getProperty("older_version_runtime_path");
        this.olderVersionFileLocation = FileLocation.getLocation(this.properties.getProperty("older_version_file_location"));
        this.olderVersionFileExtension = FileExtension.getExtension(this.properties.getProperty("older_version_file_extension"));
        this.newVersionRepositoryFolderID = this.properties.getProperty("new_version_repository_folder_id");
        this.previousVersionRepositoryFolderID = this.properties.getProperty("previous_version_repository_folder_id");
        this.olderVersionRepositoryFolderID = this.properties.getProperty("older_version_repository_folder_id");
        this.filesTitle = this.properties.getProperty("files_title");
        this.templateTitle = this.properties.getProperty("template_title");
        this.templateDocID = this.properties.getProperty("template_doc_id");
        this.templateSheetID = this.properties.getProperty("template_sheet_id");
        this.resultParentFolderID = this.properties.getProperty("result_parent_folder_id");
        this.folderTitle = this.properties.getProperty("folder_title");
        this.googleApiKeyFile = this.properties.getProperty("google_api_key_file");
        this.googleAppApiKeyFile = this.properties.getProperty("google_app_api_key_file");
    }

    /*singleton instance to be used anytime avoiding I/O*/
    static public DroolsProperties getInstance() throws IOException {
        if (droolsProperties == null) {
            droolsProperties = new DroolsProperties("/drools-reports.properties");
        }
        return droolsProperties;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public String getPreviousVersion() {
        return previousVersion;
    }

    public String getOlderVersion() {
        return olderVersion;
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

    public String getNewVersionBuildtimePath() {
        return newVersionBuildtimePath;
    }

    public void setNewVersionBuildtimePath(String newVersionBuildtimePath) {
        this.newVersionBuildtimePath = newVersionBuildtimePath;
    }

    public String getNewVersionRuntimePath() {
        return newVersionRuntimePath;
    }

    public void setNewVersionRuntimePath(String newVersionRuntimePath) {
        this.newVersionRuntimePath = newVersionRuntimePath;
    }

    public String getPreviousVersionBuildtimePath() {
        return previousVersionBuildtimePath;
    }

    public void setPreviousVersionBuildtimePath(String previousVersionBuildtimePath) {
        this.previousVersionBuildtimePath = previousVersionBuildtimePath;
    }

    public String getPreviousVersionRuntimePath() {
        return previousVersionRuntimePath;
    }

    public void setPreviousVersionRuntimePath(String previousVersionRuntimePath) {
        this.previousVersionRuntimePath = previousVersionRuntimePath;
    }

    public String getOlderVersionBuildtimePath() {
        return olderVersionBuildtimePath;
    }

    public void setOlderVersionBuildtimePath(String olderVersionBuildtimePath) {
        this.olderVersionBuildtimePath = olderVersionBuildtimePath;
    }

    public String getOlderVersionRuntimePath() {
        return olderVersionRuntimePath;
    }

    public void setOlderVersionRuntimePath(String olderVersionRuntimePath) {
        this.olderVersionRuntimePath = olderVersionRuntimePath;
    }

    public String getNewVersionRepositoryFolderID() {
        return newVersionRepositoryFolderID;
    }

    public void setNewVersionRepositoryFolderID(String newVersionRepositoryFolderID) {
        this.newVersionRepositoryFolderID = newVersionRepositoryFolderID;
    }

    public String getPreviousVersionRepositoryFolderID() {
        return previousVersionRepositoryFolderID;
    }

    public void setPreviousVersionRepositoryFolderID(String previousVersionRepositoryFolderID) {
        this.previousVersionRepositoryFolderID = previousVersionRepositoryFolderID;
    }

    public String getOlderVersionRepositoryFolderID() {
        return olderVersionRepositoryFolderID;
    }

    public void setOlderVersionRepositoryFolderID(String olderVersionRepositoryFolderID) {
        this.olderVersionRepositoryFolderID = olderVersionRepositoryFolderID;
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

    public String getGoogleApiKeyFile() {
        return googleApiKeyFile;
    }

    public String getGoogleAppApiKeyFile() {
        return googleAppApiKeyFile;
    }

    public FileLocation getNewVersionFileLocation() {
        return newVersionFileLocation;
    }

    public void setNewVersionFileLocation(FileLocation newVersionFileLocation) {
        this.newVersionFileLocation = newVersionFileLocation;
    }

    public FileLocation getPreviousVersionFileLocation() {
        return previousVersionFileLocation;
    }

    public void setPreviousVersionFileLocation(FileLocation previousVersionFileLocation) {
        this.previousVersionFileLocation = previousVersionFileLocation;
    }

    public FileLocation getOlderVersionFileLocation() {
        return olderVersionFileLocation;
    }

    public void setOlderVersionFileLocation(FileLocation olderVersionFileLocation) {
        this.olderVersionFileLocation = olderVersionFileLocation;
    }

    public FileExtension getNewVersionFileExtension() {
        return newVersionFileExtension;
    }

    public void setNewVersionFileExtension(FileExtension newVersionFileExtension) {
        this.newVersionFileExtension = newVersionFileExtension;
    }

    public FileExtension getPreviousVersionFileExtension() {
        return previousVersionFileExtension;
    }

    public void setPreviousVersionFileExtension(FileExtension previousVersionFileExtension) {
        this.previousVersionFileExtension = previousVersionFileExtension;
    }

    public FileExtension getOlderVersionFileExtension() {
        return olderVersionFileExtension;
    }

    public void setOlderVersionFileExtension(FileExtension olderVersionFileExtension) {
        this.olderVersionFileExtension = olderVersionFileExtension;
    }
}