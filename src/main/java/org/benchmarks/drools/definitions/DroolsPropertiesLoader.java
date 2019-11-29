package org.benchmarks.drools.definitions;

import org.benchmarks.commons.definitions.JenkinsReportFileExtension;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.util.PropertiesLoader;

public class DroolsPropertiesLoader extends PropertiesLoader {

    static private DroolsPropertiesLoader droolsProperties = null;

    private String newVersion;
    private String previousVersion;
    private String olderVersion;
    private String reportDate;
    private String author;
    private String emailAuthor;
    private String newVersionBuildtimePath;
    private String newVersionRuntimePath;
    private JenkinsReportLocation newVersionJenkinsReportLocation;
    private JenkinsReportFileExtension newVersionJenkinsReportFileExtension;
    private String previousVersionBuildtimePath;
    private String previousVersionRuntimePath;
    private JenkinsReportLocation previousVersionJenkinsReportLocation;
    private JenkinsReportFileExtension previousVersionJenkinsReportFileExtension;
    private String olderVersionBuildtimePath;
    private String olderVersionRuntimePath;
    private JenkinsReportLocation olderVersionJenkinsReportLocation;
    private JenkinsReportFileExtension olderVersionJenkinsReportFileExtension;
    private String newVersionRepositoryFolderID;
    private String previousVersionRepositoryFolderID;
    private String olderVersionRepositoryFolderID;
    private String filesTitle;
    private String templateTitle;
    private String templateDocID;
    private String templateSheetID;
    private String resultParentFolderID;
    private String folderTitle;
    private String googleAppApiKeyFile;

    public DroolsPropertiesLoader(String filename) {
        super(filename);

        this.newVersion = this.properties.getProperty("new_version");
        this.previousVersion = this.properties.getProperty("previous_version");
        this.olderVersion = this.properties.getProperty("older_version");
        this.reportDate = this.properties.getProperty("report_date");
        this.author = this.properties.getProperty("author");
        this.emailAuthor = this.properties.getProperty("email_author");
        this.newVersionBuildtimePath = this.properties.getProperty("new_version_buildtime_path");
        this.newVersionRuntimePath = this.properties.getProperty("new_version_runtime_path");
        this.newVersionJenkinsReportLocation = JenkinsReportLocation.getLocation(this.properties.getProperty("new_version_file_location"));
        this.newVersionJenkinsReportFileExtension = JenkinsReportFileExtension.getExtension(this.properties.getProperty("new_version_file_extension"));
        this.previousVersionBuildtimePath = this.properties.getProperty("previous_version_buildtime_path");
        this.previousVersionRuntimePath = this.properties.getProperty("previous_version_runtime_path");
        this.previousVersionJenkinsReportLocation = JenkinsReportLocation.getLocation(this.properties.getProperty("previous_version_file_location"));
        this.previousVersionJenkinsReportFileExtension = JenkinsReportFileExtension.getExtension(this.properties.getProperty("previous_version_file_extension"));
        this.olderVersionBuildtimePath = this.properties.getProperty("older_version_buildtime_path");
        this.olderVersionRuntimePath = this.properties.getProperty("older_version_runtime_path");
        this.olderVersionJenkinsReportLocation = JenkinsReportLocation.getLocation(this.properties.getProperty("older_version_file_location"));
        this.olderVersionJenkinsReportFileExtension = JenkinsReportFileExtension.getExtension(this.properties.getProperty("older_version_file_extension"));
        this.newVersionRepositoryFolderID = this.properties.getProperty("new_version_repository_folder_id");
        this.previousVersionRepositoryFolderID = this.properties.getProperty("previous_version_repository_folder_id");
        this.olderVersionRepositoryFolderID = this.properties.getProperty("older_version_repository_folder_id");
        this.filesTitle = this.properties.getProperty("files_title");
        this.templateTitle = this.properties.getProperty("template_title");
        this.templateDocID = this.properties.getProperty("template_doc_id");
        this.templateSheetID = this.properties.getProperty("template_sheet_id");
        this.resultParentFolderID = this.properties.getProperty("result_parent_folder_id");
        this.folderTitle = this.properties.getProperty("folder_title");
        this.googleAppApiKeyFile = this.properties.getProperty("google_app_api_key_file");
    }

    /*singleton instance to be used anytime avoiding I/O*/
    static public DroolsPropertiesLoader getInstance(){
        if (droolsProperties == null) {
            droolsProperties = new DroolsPropertiesLoader("/drools-reports.properties");
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

    public String getGoogleAppApiKeyFile() {
        return googleAppApiKeyFile;
    }

    public JenkinsReportLocation getNewVersionJenkinsReportLocation() {
        return newVersionJenkinsReportLocation;
    }

    public void setNewVersionJenkinsReportLocation(JenkinsReportLocation newVersionJenkinsReportLocation) {
        this.newVersionJenkinsReportLocation = newVersionJenkinsReportLocation;
    }

    public JenkinsReportLocation getPreviousVersionJenkinsReportLocation() {
        return previousVersionJenkinsReportLocation;
    }

    public void setPreviousVersionJenkinsReportLocation(JenkinsReportLocation previousVersionJenkinsReportLocation) {
        this.previousVersionJenkinsReportLocation = previousVersionJenkinsReportLocation;
    }

    public JenkinsReportLocation getOlderVersionJenkinsReportLocation() {
        return olderVersionJenkinsReportLocation;
    }

    public void setOlderVersionJenkinsReportLocation(JenkinsReportLocation olderVersionJenkinsReportLocation) {
        this.olderVersionJenkinsReportLocation = olderVersionJenkinsReportLocation;
    }

    public JenkinsReportFileExtension getNewVersionJenkinsReportFileExtension() {
        return newVersionJenkinsReportFileExtension;
    }

    public void setNewVersionJenkinsReportFileExtension(JenkinsReportFileExtension newVersionJenkinsReportFileExtension) {
        this.newVersionJenkinsReportFileExtension = newVersionJenkinsReportFileExtension;
    }

    public JenkinsReportFileExtension getPreviousVersionJenkinsReportFileExtension() {
        return previousVersionJenkinsReportFileExtension;
    }

    public void setPreviousVersionJenkinsReportFileExtension(JenkinsReportFileExtension previousVersionJenkinsReportFileExtension) {
        this.previousVersionJenkinsReportFileExtension = previousVersionJenkinsReportFileExtension;
    }

    public JenkinsReportFileExtension getOlderVersionJenkinsReportFileExtension() {
        return olderVersionJenkinsReportFileExtension;
    }

    public void setOlderVersionJenkinsReportFileExtension(JenkinsReportFileExtension olderVersionJenkinsReportFileExtension) {
        this.olderVersionJenkinsReportFileExtension = olderVersionJenkinsReportFileExtension;
    }
}
