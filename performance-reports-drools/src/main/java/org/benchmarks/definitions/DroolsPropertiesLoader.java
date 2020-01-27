package org.benchmarks.definitions;

import java.io.IOException;

import org.benchmarks.util.PropertiesLoader;

public class DroolsPropertiesLoader extends PropertiesLoader {

    private static String CURRENT_VERSION_BUILDTIME_PATH = "current_version_buildtime_path";
    private static String CURRENT_VERSION_RUNTIME_PATH = "current_version_runtime_path";
    private static String CURRENT_VERSION_FILE_LOCATION = "current_version_file_location";
    private static String CURRENT_VERSION_FILE_EXTENSION = "current_version_file_extension";
    private static String PREVIOUS_VERSION_BUILDTIME_PATH = "previous_version_buildtime_path";
    private static String PREVIOUS_VERSION_RUNTIME_PATH = "previous_version_runtime_path";
    private static String PREVIOUS_VERSION_FILE_LOCATION = "previous_version_file_location";
    private static String PREVIOUS_VERSION_FILE_EXTENSION = "previous_version_file_extension";
    private static String OLDER_VERSION_BUILDTIME_PATH = "older_version_buildtime_path";
    private static String OLDER_VERSION_RUNTIME_PATH = "older_version_runtime_path";
    private static String OLDER_VERSION_FILE_LOCATION = "older_version_file_location";
    private static String OLDER_VERSION_FILE_EXTENSION = "older_version_file_extension";
    private static String CURRENT_VERSION_REPOSITORY_FOLDER_ID = "current_version_repository_folder_id";
    private static String PREVIOUS_VERSION_REPOSITORY_FOLDER_ID = "previous_version_repository_folder_id";
    private static String OLDER_VERSION_REPOSITORY_FOLDER_ID = "older_version_repository_folder_id";

    private static DroolsPropertiesLoader droolsPropertiesLoader = null;

    protected String currentVersionBuildtimePath;
    protected String currentVersionRuntimePath;
    protected JenkinsReportLocation currentVersionJenkinsReportLocation;
    protected JenkinsReportFileExtension currentVersionJenkinsReportFileExtension;

    protected String previousVersionBuildtimePath;
    protected String previousVersionRuntimePath;
    protected JenkinsReportLocation previousVersionJenkinsReportLocation;
    protected JenkinsReportFileExtension previousVersionJenkinsReportFileExtension;

    protected String olderVersionBuildtimePath;
    protected String olderVersionRuntimePath;
    protected JenkinsReportLocation olderVersionJenkinsReportLocation;
    protected JenkinsReportFileExtension olderVersionJenkinsReportFileExtension;

    private String currentVersionRepositoryFolderID;
    private String previousVersionRepositoryFolderID;
    private String olderVersionRepositoryFolderID;

    public DroolsPropertiesLoader(String filename) throws IOException {
        super(filename);

        if (this.properties.containsKey(CURRENT_VERSION_BUILDTIME_PATH)) {
            this.currentVersionBuildtimePath = this.properties.getProperty(CURRENT_VERSION_BUILDTIME_PATH);
        }
        if (this.properties.containsKey(CURRENT_VERSION_RUNTIME_PATH)) {
            this.currentVersionRuntimePath = this.properties.getProperty(CURRENT_VERSION_RUNTIME_PATH);
        }
        if (this.properties.containsKey(CURRENT_VERSION_FILE_LOCATION)) {
            this.currentVersionJenkinsReportLocation = JenkinsReportLocation.getLocation(this.properties.getProperty(CURRENT_VERSION_FILE_LOCATION));
        }
        if (this.properties.containsKey(CURRENT_VERSION_FILE_EXTENSION)) {
            this.currentVersionJenkinsReportFileExtension = JenkinsReportFileExtension.getExtension(this.properties.getProperty(CURRENT_VERSION_FILE_EXTENSION));
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_BUILDTIME_PATH)) {
            this.previousVersionBuildtimePath = this.properties.getProperty(PREVIOUS_VERSION_BUILDTIME_PATH);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_RUNTIME_PATH)) {
            this.previousVersionRuntimePath = this.properties.getProperty(PREVIOUS_VERSION_RUNTIME_PATH);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_FILE_LOCATION)) {
            this.previousVersionJenkinsReportLocation = JenkinsReportLocation.getLocation(this.properties.getProperty(PREVIOUS_VERSION_FILE_LOCATION));
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_FILE_EXTENSION)) {
            this.previousVersionJenkinsReportFileExtension = JenkinsReportFileExtension.getExtension(this.properties.getProperty(PREVIOUS_VERSION_FILE_EXTENSION));
        }
        if (this.properties.containsKey(OLDER_VERSION_BUILDTIME_PATH)) {
            this.olderVersionBuildtimePath = this.properties.getProperty(OLDER_VERSION_BUILDTIME_PATH);
        }
        if (this.properties.containsKey(OLDER_VERSION_RUNTIME_PATH)) {
            this.olderVersionRuntimePath = this.properties.getProperty(OLDER_VERSION_RUNTIME_PATH);
        }
        if (this.properties.containsKey(OLDER_VERSION_FILE_LOCATION)) {
            this.olderVersionJenkinsReportLocation = JenkinsReportLocation.getLocation(this.properties.getProperty(OLDER_VERSION_FILE_LOCATION));
        }
        if (this.properties.containsKey(OLDER_VERSION_FILE_EXTENSION)) {
            this.olderVersionJenkinsReportFileExtension = JenkinsReportFileExtension.getExtension(this.properties.getProperty(OLDER_VERSION_FILE_EXTENSION));
        }
        if (this.properties.containsKey(CURRENT_VERSION_REPOSITORY_FOLDER_ID)) {
            this.currentVersionRepositoryFolderID = this.properties.getProperty(CURRENT_VERSION_REPOSITORY_FOLDER_ID);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_REPOSITORY_FOLDER_ID)) {
            this.previousVersionRepositoryFolderID = this.properties.getProperty(PREVIOUS_VERSION_REPOSITORY_FOLDER_ID);
        }
        if (this.properties.containsKey(OLDER_VERSION_REPOSITORY_FOLDER_ID)) {
            this.olderVersionRepositoryFolderID = this.properties.getProperty(OLDER_VERSION_REPOSITORY_FOLDER_ID);
        }
    }

    /*singleton instance to be used anytime avoiding I/O*/
    public static DroolsPropertiesLoader getInstance() throws IOException {
        if (droolsPropertiesLoader == null) {
            droolsPropertiesLoader = new DroolsPropertiesLoader("/drools-reports.properties");
        }
        return droolsPropertiesLoader;
    }

    public static DroolsPropertiesLoader getInstance(String filename) throws IOException {
        if ((droolsPropertiesLoader == null) || ((droolsPropertiesLoader != null) && (droolsPropertiesLoader.fileName != filename))) {
            droolsPropertiesLoader = new DroolsPropertiesLoader(filename);
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
