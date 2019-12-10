package org.benchmarks.drools.definitions;

import java.io.IOException;

import org.benchmarks.commons.definitions.JenkinsReportFileExtension;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.util.PropertiesLoader;

public class DroolsPropertiesLoader extends PropertiesLoader {

    private static DroolsPropertiesLoader droolsPropertiesLoader = null;

    private String currentVersionBuildtimePath;
    private String currentVersionRuntimePath;
    private JenkinsReportLocation currentVersionJenkinsReportLocation;
    private JenkinsReportFileExtension currentVersionJenkinsReportFileExtension;

    private String previousVersionBuildtimePath;
    private String previousVersionRuntimePath;
    private JenkinsReportLocation previousVersionJenkinsReportLocation;
    private JenkinsReportFileExtension previousVersionJenkinsReportFileExtension;

    private String olderVersionBuildtimePath;
    private String olderVersionRuntimePath;
    private JenkinsReportLocation olderVersionJenkinsReportLocation;
    private JenkinsReportFileExtension olderVersionJenkinsReportFileExtension;

    private String currentVersionRepositoryFolderID;
    private String previousVersionRepositoryFolderID;
    private String olderVersionRepositoryFolderID;


    public DroolsPropertiesLoader(String filename) throws IOException {
        super(filename);

        this.currentVersionBuildtimePath = this.properties.getProperty("current_version_buildtime_path");
        this.currentVersionRuntimePath = this.properties.getProperty("current_version_runtime_path");
        this.currentVersionJenkinsReportLocation = JenkinsReportLocation.getLocation(this.properties.getProperty("current_version_file_location"));
        this.currentVersionJenkinsReportFileExtension = JenkinsReportFileExtension.getExtension(this.properties.getProperty("current_version_file_extension"));
        this.previousVersionBuildtimePath = this.properties.getProperty("previous_version_buildtime_path");
        this.previousVersionRuntimePath = this.properties.getProperty("previous_version_runtime_path");
        this.previousVersionJenkinsReportLocation = JenkinsReportLocation.getLocation(this.properties.getProperty("previous_version_file_location"));
        this.previousVersionJenkinsReportFileExtension = JenkinsReportFileExtension.getExtension(this.properties.getProperty("previous_version_file_extension"));
        this.olderVersionBuildtimePath = this.properties.getProperty("older_version_buildtime_path");
        this.olderVersionRuntimePath = this.properties.getProperty("older_version_runtime_path");
        this.olderVersionJenkinsReportLocation = JenkinsReportLocation.getLocation(this.properties.getProperty("older_version_file_location"));
        this.olderVersionJenkinsReportFileExtension = JenkinsReportFileExtension.getExtension(this.properties.getProperty("older_version_file_extension"));
        this.currentVersionRepositoryFolderID = this.properties.getProperty("current_version_repository_folder_id");
        this.previousVersionRepositoryFolderID = this.properties.getProperty("previous_version_repository_folder_id");
        this.olderVersionRepositoryFolderID = this.properties.getProperty("older_version_repository_folder_id");
    }

    /*singleton instance to be used anytime avoiding I/O*/
    public static DroolsPropertiesLoader getInstance() throws IOException {
        if (droolsPropertiesLoader == null) {
            droolsPropertiesLoader = new DroolsPropertiesLoader("/drools-reports.properties");
        }
        return droolsPropertiesLoader;
    }

    public String getCurrentVersionBuildtimePath() {
        return currentVersionBuildtimePath;
    }

    public void setCurrentVersionBuildtimePath(String currentVersionBuildtimePath) {
        this.currentVersionBuildtimePath = currentVersionBuildtimePath;
    }

    public String getCurrentVersionRuntimePath() {
        return currentVersionRuntimePath;
    }

    public void setCurrentVersionRuntimePath(String currentVersionRuntimePath) {
        this.currentVersionRuntimePath = currentVersionRuntimePath;
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

    public String getCurrentVersionRepositoryFolderID() {
        return currentVersionRepositoryFolderID;
    }

    public void setCurrentVersionRepositoryFolderID(String currentVersionRepositoryFolderID) {
        this.currentVersionRepositoryFolderID = currentVersionRepositoryFolderID;
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

    public JenkinsReportLocation getCurrentVersionJenkinsReportLocation() {
        return currentVersionJenkinsReportLocation;
    }

    public void setCurrentVersionJenkinsReportLocation(JenkinsReportLocation currentVersionJenkinsReportLocation) {
        this.currentVersionJenkinsReportLocation = currentVersionJenkinsReportLocation;
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

    public JenkinsReportFileExtension getCurrentVersionJenkinsReportFileExtension() {
        return currentVersionJenkinsReportFileExtension;
    }

    public void setCurrentVersionJenkinsReportFileExtension(JenkinsReportFileExtension currentVersionJenkinsReportFileExtension) {
        this.currentVersionJenkinsReportFileExtension = currentVersionJenkinsReportFileExtension;
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
