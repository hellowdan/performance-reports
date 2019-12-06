package org.benchmarks.drools.definitions;

import java.io.IOException;

import org.benchmarks.commons.definitions.JenkinsReportFileExtension;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.util.PropertiesLoader;

public class DroolsPropertiesLoader extends PropertiesLoader {

    static private DroolsPropertiesLoader droolsPropertiesLoader = null;

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


    public DroolsPropertiesLoader(String filename) throws IOException {
        super(filename);

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
    }

    /*singleton instance to be used anytime avoiding I/O*/
    static public DroolsPropertiesLoader getInstance() throws IOException {
        if (droolsPropertiesLoader == null) {
            droolsPropertiesLoader = new DroolsPropertiesLoader("/drools-reports.properties");
        }
        return droolsPropertiesLoader;
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
