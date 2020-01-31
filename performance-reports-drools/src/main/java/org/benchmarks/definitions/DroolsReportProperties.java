package org.benchmarks.definitions;

import java.io.IOException;

import org.benchmarks.util.ReportProperties;

public class DroolsReportProperties extends ReportProperties {

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

    private static DroolsReportProperties droolsReportProperties = null;

    protected String currentVersionBuildtimePath;
    protected String currentVersionRuntimePath;
    protected SourceFileLocation currentVersionSourceFileLocation;
    protected SourceFileExtension currentVersionSourceFileExtension;

    protected String previousVersionBuildtimePath;
    protected String previousVersionRuntimePath;
    protected SourceFileLocation previousVersionSourceFileLocation;
    protected SourceFileExtension previousVersionSourceFileExtension;

    protected String olderVersionBuildtimePath;
    protected String olderVersionRuntimePath;
    protected SourceFileLocation olderVersionSourceFileLocation;
    protected SourceFileExtension olderVersionSourceFileExtension;

    private String currentVersionRepositoryFolderID;
    private String previousVersionRepositoryFolderID;
    private String olderVersionRepositoryFolderID;

    public DroolsReportProperties(String filename) throws IOException {
        super(filename);

        if (this.properties.containsKey(CURRENT_VERSION_BUILDTIME_PATH)) {
            this.currentVersionBuildtimePath = this.properties.getProperty(CURRENT_VERSION_BUILDTIME_PATH);
        }
        if (this.properties.containsKey(CURRENT_VERSION_RUNTIME_PATH)) {
            this.currentVersionRuntimePath = this.properties.getProperty(CURRENT_VERSION_RUNTIME_PATH);
        }
        if (this.properties.containsKey(CURRENT_VERSION_FILE_LOCATION)) {
            this.currentVersionSourceFileLocation = SourceFileLocation.getLocation(this.properties.getProperty(CURRENT_VERSION_FILE_LOCATION));
        }
        if (this.properties.containsKey(CURRENT_VERSION_FILE_EXTENSION)) {
            this.currentVersionSourceFileExtension = SourceFileExtension.getExtension(this.properties.getProperty(CURRENT_VERSION_FILE_EXTENSION));
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_BUILDTIME_PATH)) {
            this.previousVersionBuildtimePath = this.properties.getProperty(PREVIOUS_VERSION_BUILDTIME_PATH);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_RUNTIME_PATH)) {
            this.previousVersionRuntimePath = this.properties.getProperty(PREVIOUS_VERSION_RUNTIME_PATH);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_FILE_LOCATION)) {
            this.previousVersionSourceFileLocation = SourceFileLocation.getLocation(this.properties.getProperty(PREVIOUS_VERSION_FILE_LOCATION));
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_FILE_EXTENSION)) {
            this.previousVersionSourceFileExtension = SourceFileExtension.getExtension(this.properties.getProperty(PREVIOUS_VERSION_FILE_EXTENSION));
        }
        if (this.properties.containsKey(OLDER_VERSION_BUILDTIME_PATH)) {
            this.olderVersionBuildtimePath = this.properties.getProperty(OLDER_VERSION_BUILDTIME_PATH);
        }
        if (this.properties.containsKey(OLDER_VERSION_RUNTIME_PATH)) {
            this.olderVersionRuntimePath = this.properties.getProperty(OLDER_VERSION_RUNTIME_PATH);
        }
        if (this.properties.containsKey(OLDER_VERSION_FILE_LOCATION)) {
            this.olderVersionSourceFileLocation = SourceFileLocation.getLocation(this.properties.getProperty(OLDER_VERSION_FILE_LOCATION));
        }
        if (this.properties.containsKey(OLDER_VERSION_FILE_EXTENSION)) {
            this.olderVersionSourceFileExtension = SourceFileExtension.getExtension(this.properties.getProperty(OLDER_VERSION_FILE_EXTENSION));
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
    public static DroolsReportProperties getInstance() throws IOException {
        if (droolsReportProperties == null) {
            droolsReportProperties = new DroolsReportProperties("/drools-reports.properties");
        }
        return droolsReportProperties;
    }

    public static DroolsReportProperties getInstance(String filename) throws IOException {
        if ((droolsReportProperties == null) || ((droolsReportProperties != null) && (droolsReportProperties.fileName != filename))) {
            droolsReportProperties = new DroolsReportProperties(filename);
        }
        return droolsReportProperties;
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

    public SourceFileLocation getCurrentVersionSourceFileLocation() {
        return currentVersionSourceFileLocation;
    }

    public void setCurrentVersionSourceFileLocation(SourceFileLocation currentVersionSourceFileLocation) {
        this.currentVersionSourceFileLocation = currentVersionSourceFileLocation;
    }

    public SourceFileLocation getPreviousVersionSourceFileLocation() {
        return previousVersionSourceFileLocation;
    }

    public void setPreviousVersionSourceFileLocation(SourceFileLocation previousVersionSourceFileLocation) {
        this.previousVersionSourceFileLocation = previousVersionSourceFileLocation;
    }

    public SourceFileLocation getOlderVersionSourceFileLocation() {
        return olderVersionSourceFileLocation;
    }

    public void setOlderVersionSourceFileLocation(SourceFileLocation olderVersionSourceFileLocation) {
        this.olderVersionSourceFileLocation = olderVersionSourceFileLocation;
    }

    public SourceFileExtension getCurrentVersionSourceFileExtension() {
        return currentVersionSourceFileExtension;
    }

    public void setCurrentVersionSourceFileExtension(SourceFileExtension currentVersionSourceFileExtension) {
        this.currentVersionSourceFileExtension = currentVersionSourceFileExtension;
    }

    public SourceFileExtension getPreviousVersionSourceFileExtension() {
        return previousVersionSourceFileExtension;
    }

    public void setPreviousVersionSourceFileExtension(SourceFileExtension previousVersionSourceFileExtension) {
        this.previousVersionSourceFileExtension = previousVersionSourceFileExtension;
    }

    public SourceFileExtension getOlderVersionSourceFileExtension() {
        return olderVersionSourceFileExtension;
    }

    public void setOlderVersionSourceFileExtension(SourceFileExtension olderVersionSourceFileExtension) {
        this.olderVersionSourceFileExtension = olderVersionSourceFileExtension;
    }
}
