package org.benchmarks.definitions;

import java.io.IOException;

import org.benchmarks.util.ReportProperties;

public class DroolsReportProperties extends ReportProperties {

    private static final String NEXT_VERSION_DMN_PATH = "next_version_dmn_path";
    private static final String NEXT_VERSION_EVENT_PROCESSING_PATH ="next_version_event-processing_path";
    private static final String NEXT_VERSION_EVENT_PROCESSING_MULTITHREADED_PATH ="next_version_event-processing-multithreaded_path";
    private static final String NEXT_VERSION_OOPATH_PATH ="next_version_oopath_path";
    private static final String NEXT_VERSION_OPERATORS_PATH ="next_version_operators_path";
    private static final String NEXT_VERSION_SESSION_PATH ="next_version_session_path";
    private static final String NEXT_VERSION_THROUGHPUT_PATH ="next_version_throughput_path";
    private static final String NEXT_VERSION_BUILDTIME_PATH ="next_version_buildtime_path";
    private static final String NEXT_VERSION_RUNTIME_PATH ="next_version_runtime_path";
    private static final String NEXT_VERSION_RUNTIME_MULTITHREADED_PATH ="next_version_runtime-multithreaded_path";
    private static final String NEXT_VERSION_FILE_LOCATION = "next_version_file_location";
    private static final String NEXT_VERSION_FILE_EXTENSION = "next_version_file_extension";

    private static final String CURRENT_VERSION_DMN_PATH = "current_version_dmn_path";
    private static final String CURRENT_VERSION_EVENT_PROCESSING_PATH ="current_version_event-processing_path";
    private static final String CURRENT_VERSION_EVENT_PROCESSING_MULTITHREADED_PATH ="current_version_event-processing-multithreaded_path";
    private static final String CURRENT_VERSION_OOPATH_PATH ="current_version_oopath_path";
    private static final String CURRENT_VERSION_OPERATORS_PATH ="current_version_operators_path";
    private static final String CURRENT_VERSION_SESSION_PATH ="current_version_session_path";
    private static final String CURRENT_VERSION_THROUGHPUT_PATH ="current_version_throughput_path";
    private static final String CURRENT_VERSION_BUILDTIME_PATH ="current_version_buildtime_path";
    private static final String CURRENT_VERSION_RUNTIME_PATH ="current_version_runtime_path";
    private static final String CURRENT_VERSION_RUNTIME_MULTITHREADED_PATH ="current_version_runtime-multithreaded_path";
    private static final String CURRENT_VERSION_FILE_LOCATION = "current_version_file_location";
    private static final String CURRENT_VERSION_FILE_EXTENSION = "current_version_file_extension";

    private static final String PREVIOUS_VERSION_DMN_PATH = "previous_version_dmn_path";
    private static final String PREVIOUS_VERSION_EVENT_PROCESSING_PATH ="previous_version_event-processing_path";
    private static final String PREVIOUS_VERSION_EVENT_PROCESSING_MULTITHREADED_PATH ="previous_version_event-processing-multithreaded_path";
    private static final String PREVIOUS_VERSION_OOPATH_PATH ="previous_version_oopath_path";
    private static final String PREVIOUS_VERSION_OPERATORS_PATH ="previous_version_operators_path";
    private static final String PREVIOUS_VERSION_SESSION_PATH ="previous_version_session_path";
    private static final String PREVIOUS_VERSION_THROUGHPUT_PATH ="previous_version_throughput_path";
    private static final String PREVIOUS_VERSION_BUILDTIME_PATH ="previous_version_buildtime_path";
    private static final String PREVIOUS_VERSION_RUNTIME_PATH ="previous_version_runtime_path";
    private static final String PREVIOUS_VERSION_RUNTIME_MULTITHREADED_PATH ="previous_version_runtime-multithreaded_path";
    private static final String PREVIOUS_VERSION_FILE_LOCATION = "previous_version_file_location";
    private static final String PREVIOUS_VERSION_FILE_EXTENSION = "previous_version_file_extension";

    private static final String OLDER_VERSION_DMN_PATH = "older_version_dmn_path";
    private static final String OLDER_VERSION_EVENT_PROCESSING_PATH ="older_version_event-processing_path";
    private static final String OLDER_VERSION_EVENT_PROCESSING_MULTITHREADED_PATH ="older_version_event-processing-multithreaded_path";
    private static final String OLDER_VERSION_OOPATH_PATH ="older_version_oopath_path";
    private static final String OLDER_VERSION_OPERATORS_PATH ="older_version_operators_path";
    private static final String OLDER_VERSION_SESSION_PATH ="older_version_session_path";
    private static final String OLDER_VERSION_THROUGHPUT_PATH ="older_version_throughput_path";
    private static final String OLDER_VERSION_BUILDTIME_PATH ="older_version_buildtime_path";
    private static final String OLDER_VERSION_RUNTIME_PATH ="older_version_runtime_path";
    private static final String OLDER_VERSION_RUNTIME_MULTITHREADED_PATH ="older_version_runtime-multithreaded_path";
    private static final String OLDER_VERSION_FILE_LOCATION = "older_version_file_location";
    private static final String OLDER_VERSION_FILE_EXTENSION = "older_version_file_extension";

    private static final String NEXT_VERSION_REPOSITORY_FOLDER_ID = "next_version_repository_folder_id";
    private static final String CURRENT_VERSION_REPOSITORY_FOLDER_ID = "current_version_repository_folder_id";
    private static final String PREVIOUS_VERSION_REPOSITORY_FOLDER_ID = "previous_version_repository_folder_id";
    private static final String OLDER_VERSION_REPOSITORY_FOLDER_ID = "older_version_repository_folder_id";

    private static DroolsReportProperties droolsReportProperties = null;

    protected String nextVersionDMNPath;
    protected String nextVersionEventProcessingPath;
    protected String nextVersionEventProcessingMultithreadedPath;
    protected String nextVersionOopathPath;
    protected String nextVersionOperatorsPath;
    protected String nextVersionSessionPath;
    protected String nextVersionThroughputPath;
    protected String nextVersionBuildtimePath;
    protected String nextVersionRuntimePath;
    protected String nextVersionRuntimeMultithreadedPath;
    protected SourceFileLocation nextVersionSourceFileLocation;
    protected SourceFileExtension nextVersionSourceFileExtension;

    protected String currentVersionDMNPath;
    protected String currentVersionEventProcessingPath;
    protected String currentVersionEventProcessingMultithreadedPath;
    protected String currentVersionOopathPath;
    protected String currentVersionOperatorsPath;
    protected String currentVersionSessionPath;
    protected String currentVersionThroughputPath;
    protected String currentVersionBuildtimePath;
    protected String currentVersionRuntimePath;
    protected String currentVersionRuntimeMultithreadedPath;
    protected SourceFileLocation currentVersionSourceFileLocation;
    protected SourceFileExtension currentVersionSourceFileExtension;

    protected String previousVersionDMNPath;
    protected String previousVersionEventProcessingPath;
    protected String previousVersionEventProcessingMultithreadedPath;
    protected String previousVersionOopathPath;
    protected String previousVersionOperatorsPath;
    protected String previousVersionSessionPath;
    protected String previousVersionThroughputPath;
    protected String previousVersionBuildtimePath;
    protected String previousVersionRuntimePath;
    protected String previousVersionRuntimeMultithreadedPath;
    protected SourceFileLocation previousVersionSourceFileLocation;
    protected SourceFileExtension previousVersionSourceFileExtension;

    protected String olderVersionDMNPath;
    protected String olderVersionEventProcessingPath;
    protected String olderVersionEventProcessingMultithreadedPath;
    protected String olderVersionOopathPath;
    protected String olderVersionOperatorsPath;
    protected String olderVersionSessionPath;
    protected String olderVersionThroughputPath;
    protected String olderVersionBuildtimePath;
    protected String olderVersionRuntimePath;
    protected String olderVersionRuntimeMultithreadedPath;
    protected SourceFileLocation olderVersionSourceFileLocation;
    protected SourceFileExtension olderVersionSourceFileExtension;

    private String nextVersionRepositoryFolderID;
    private String currentVersionRepositoryFolderID;
    private String previousVersionRepositoryFolderID;
    private String olderVersionRepositoryFolderID;

    public DroolsReportProperties(String filename) throws IOException {
        super(filename);

        /*NEXT VERSION PROPERTIES*/
        if (this.properties.containsKey(NEXT_VERSION_DMN_PATH)) {
            this.nextVersionDMNPath = this.properties.getProperty(NEXT_VERSION_DMN_PATH);
        }
        if (this.properties.containsKey(NEXT_VERSION_EVENT_PROCESSING_PATH)) {
            this.nextVersionEventProcessingPath = this.properties.getProperty(NEXT_VERSION_EVENT_PROCESSING_PATH);
        }
        if (this.properties.containsKey(NEXT_VERSION_EVENT_PROCESSING_MULTITHREADED_PATH)) {
            this.nextVersionEventProcessingMultithreadedPath = this.properties.getProperty(NEXT_VERSION_EVENT_PROCESSING_MULTITHREADED_PATH);
        }
        if (this.properties.containsKey(NEXT_VERSION_OOPATH_PATH)) {
            this.nextVersionOopathPath = this.properties.getProperty(NEXT_VERSION_OOPATH_PATH);
        }
        if (this.properties.containsKey(NEXT_VERSION_OPERATORS_PATH)) {
            this.nextVersionOperatorsPath = this.properties.getProperty(NEXT_VERSION_OPERATORS_PATH);
        }
        if (this.properties.containsKey(NEXT_VERSION_SESSION_PATH)) {
            this.nextVersionSessionPath = this.properties.getProperty(NEXT_VERSION_SESSION_PATH);
        }
        if (this.properties.containsKey(NEXT_VERSION_THROUGHPUT_PATH)) {
            this.nextVersionThroughputPath = this.properties.getProperty(NEXT_VERSION_THROUGHPUT_PATH);
        }
        if (this.properties.containsKey(NEXT_VERSION_BUILDTIME_PATH)) {
            this.nextVersionBuildtimePath = this.properties.getProperty(NEXT_VERSION_BUILDTIME_PATH);
        }
        if (this.properties.containsKey(NEXT_VERSION_RUNTIME_PATH)) {
            this.nextVersionRuntimePath = this.properties.getProperty(NEXT_VERSION_RUNTIME_PATH);
        }
        if (this.properties.containsKey(NEXT_VERSION_RUNTIME_MULTITHREADED_PATH)) {
            this.nextVersionRuntimeMultithreadedPath = this.properties.getProperty(NEXT_VERSION_RUNTIME_MULTITHREADED_PATH);
        }
        if (this.properties.containsKey(NEXT_VERSION_FILE_LOCATION)) {
            this.nextVersionSourceFileLocation = SourceFileLocation.getLocation(this.properties.getProperty(NEXT_VERSION_FILE_LOCATION));
        }
        if (this.properties.containsKey(NEXT_VERSION_FILE_EXTENSION)) {
            this.nextVersionSourceFileExtension = SourceFileExtension.getExtension(this.properties.getProperty(NEXT_VERSION_FILE_EXTENSION));
        }
        if (this.properties.containsKey(NEXT_VERSION_REPOSITORY_FOLDER_ID)) {
            this.nextVersionRepositoryFolderID = this.properties.getProperty(NEXT_VERSION_REPOSITORY_FOLDER_ID);
        }

        /*CURRENT VERSION PROPERTIES*/
        if (this.properties.containsKey(CURRENT_VERSION_DMN_PATH)) {
            this.currentVersionDMNPath = this.properties.getProperty(CURRENT_VERSION_DMN_PATH);
        }
        if (this.properties.containsKey(CURRENT_VERSION_EVENT_PROCESSING_PATH)) {
            this.currentVersionEventProcessingPath = this.properties.getProperty(CURRENT_VERSION_EVENT_PROCESSING_PATH);
        }
        if (this.properties.containsKey(CURRENT_VERSION_EVENT_PROCESSING_MULTITHREADED_PATH)) {
            this.currentVersionEventProcessingMultithreadedPath = this.properties.getProperty(CURRENT_VERSION_EVENT_PROCESSING_MULTITHREADED_PATH);
        }
        if (this.properties.containsKey(CURRENT_VERSION_OOPATH_PATH)) {
            this.currentVersionOopathPath = this.properties.getProperty(CURRENT_VERSION_OOPATH_PATH);
        }
        if (this.properties.containsKey(CURRENT_VERSION_OPERATORS_PATH)) {
            this.currentVersionOperatorsPath = this.properties.getProperty(CURRENT_VERSION_OPERATORS_PATH);
        }
        if (this.properties.containsKey(CURRENT_VERSION_SESSION_PATH)) {
            this.currentVersionSessionPath = this.properties.getProperty(CURRENT_VERSION_SESSION_PATH);
        }
        if (this.properties.containsKey(CURRENT_VERSION_THROUGHPUT_PATH)) {
            this.currentVersionThroughputPath = this.properties.getProperty(CURRENT_VERSION_THROUGHPUT_PATH);
        }
        if (this.properties.containsKey(CURRENT_VERSION_BUILDTIME_PATH)) {
            this.currentVersionBuildtimePath = this.properties.getProperty(CURRENT_VERSION_BUILDTIME_PATH);
        }
        if (this.properties.containsKey(CURRENT_VERSION_RUNTIME_PATH)) {
            this.currentVersionRuntimePath = this.properties.getProperty(CURRENT_VERSION_RUNTIME_PATH);
        }
        if (this.properties.containsKey(CURRENT_VERSION_RUNTIME_MULTITHREADED_PATH)) {
            this.currentVersionRuntimeMultithreadedPath = this.properties.getProperty(CURRENT_VERSION_RUNTIME_MULTITHREADED_PATH);
        }
        if (this.properties.containsKey(CURRENT_VERSION_FILE_LOCATION)) {
            this.currentVersionSourceFileLocation = SourceFileLocation.getLocation(this.properties.getProperty(CURRENT_VERSION_FILE_LOCATION));
        }
        if (this.properties.containsKey(CURRENT_VERSION_FILE_EXTENSION)) {
            this.currentVersionSourceFileExtension = SourceFileExtension.getExtension(this.properties.getProperty(CURRENT_VERSION_FILE_EXTENSION));
        }
        if (this.properties.containsKey(CURRENT_VERSION_REPOSITORY_FOLDER_ID)) {
            this.currentVersionRepositoryFolderID = this.properties.getProperty(CURRENT_VERSION_REPOSITORY_FOLDER_ID);
        }

        /*PREVIOUS VERSION PROPERTIES*/
        if (this.properties.containsKey(PREVIOUS_VERSION_DMN_PATH)) {
            this.previousVersionDMNPath = this.properties.getProperty(PREVIOUS_VERSION_DMN_PATH);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_EVENT_PROCESSING_PATH)) {
            this.previousVersionEventProcessingPath = this.properties.getProperty(PREVIOUS_VERSION_EVENT_PROCESSING_PATH);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_EVENT_PROCESSING_MULTITHREADED_PATH)) {
            this.previousVersionEventProcessingMultithreadedPath = this.properties.getProperty(PREVIOUS_VERSION_EVENT_PROCESSING_MULTITHREADED_PATH);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_OOPATH_PATH)) {
            this.previousVersionOopathPath = this.properties.getProperty(PREVIOUS_VERSION_OOPATH_PATH);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_OPERATORS_PATH)) {
            this.previousVersionOperatorsPath = this.properties.getProperty(PREVIOUS_VERSION_OPERATORS_PATH);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_SESSION_PATH)) {
            this.previousVersionSessionPath = this.properties.getProperty(PREVIOUS_VERSION_SESSION_PATH);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_THROUGHPUT_PATH)) {
            this.previousVersionThroughputPath = this.properties.getProperty(PREVIOUS_VERSION_THROUGHPUT_PATH);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_BUILDTIME_PATH)) {
            this.previousVersionBuildtimePath = this.properties.getProperty(PREVIOUS_VERSION_BUILDTIME_PATH);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_RUNTIME_PATH)) {
            this.previousVersionRuntimePath = this.properties.getProperty(PREVIOUS_VERSION_RUNTIME_PATH);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_RUNTIME_MULTITHREADED_PATH)) {
            this.previousVersionRuntimeMultithreadedPath = this.properties.getProperty(PREVIOUS_VERSION_RUNTIME_MULTITHREADED_PATH);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_FILE_LOCATION)) {
            this.previousVersionSourceFileLocation = SourceFileLocation.getLocation(this.properties.getProperty(PREVIOUS_VERSION_FILE_LOCATION));
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_FILE_EXTENSION)) {
            this.previousVersionSourceFileExtension = SourceFileExtension.getExtension(this.properties.getProperty(PREVIOUS_VERSION_FILE_EXTENSION));
        }
        if (this.properties.containsKey(PREVIOUS_VERSION_REPOSITORY_FOLDER_ID)) {
            this.previousVersionRepositoryFolderID = this.properties.getProperty(PREVIOUS_VERSION_REPOSITORY_FOLDER_ID);
        }

        /*OLDER VERSION PROPERTIES*/
        if (this.properties.containsKey(OLDER_VERSION_DMN_PATH)) {
            this.olderVersionDMNPath = this.properties.getProperty(OLDER_VERSION_DMN_PATH);
        }
        if (this.properties.containsKey(OLDER_VERSION_EVENT_PROCESSING_PATH)) {
            this.olderVersionEventProcessingPath = this.properties.getProperty(OLDER_VERSION_EVENT_PROCESSING_PATH);
        }
        if (this.properties.containsKey(OLDER_VERSION_EVENT_PROCESSING_MULTITHREADED_PATH)) {
            this.olderVersionEventProcessingMultithreadedPath = this.properties.getProperty(OLDER_VERSION_EVENT_PROCESSING_MULTITHREADED_PATH);
        }
        if (this.properties.containsKey(OLDER_VERSION_OOPATH_PATH)) {
            this.olderVersionOopathPath = this.properties.getProperty(OLDER_VERSION_OOPATH_PATH);
        }
        if (this.properties.containsKey(OLDER_VERSION_OPERATORS_PATH)) {
            this.olderVersionOperatorsPath = this.properties.getProperty(OLDER_VERSION_OPERATORS_PATH);
        }
        if (this.properties.containsKey(OLDER_VERSION_SESSION_PATH)) {
            this.olderVersionSessionPath = this.properties.getProperty(OLDER_VERSION_SESSION_PATH);
        }
        if (this.properties.containsKey(OLDER_VERSION_THROUGHPUT_PATH)) {
            this.olderVersionThroughputPath = this.properties.getProperty(OLDER_VERSION_THROUGHPUT_PATH);
        }
        if (this.properties.containsKey(OLDER_VERSION_BUILDTIME_PATH)) {
            this.olderVersionBuildtimePath = this.properties.getProperty(OLDER_VERSION_BUILDTIME_PATH);
        }
        if (this.properties.containsKey(OLDER_VERSION_RUNTIME_PATH)) {
            this.olderVersionRuntimePath = this.properties.getProperty(OLDER_VERSION_RUNTIME_PATH);
        }
        if (this.properties.containsKey(OLDER_VERSION_RUNTIME_MULTITHREADED_PATH)) {
            this.olderVersionRuntimeMultithreadedPath = this.properties.getProperty(OLDER_VERSION_RUNTIME_MULTITHREADED_PATH);
        }
        if (this.properties.containsKey(OLDER_VERSION_FILE_LOCATION)) {
            this.olderVersionSourceFileLocation = SourceFileLocation.getLocation(this.properties.getProperty(OLDER_VERSION_FILE_LOCATION));
        }
        if (this.properties.containsKey(OLDER_VERSION_FILE_EXTENSION)) {
            this.olderVersionSourceFileExtension = SourceFileExtension.getExtension(this.properties.getProperty(OLDER_VERSION_FILE_EXTENSION));
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

    public SourceFileExtension getPreviousVersionSourceFileExtension() {
        return previousVersionSourceFileExtension;
    }

    public SourceFileExtension getOlderVersionSourceFileExtension() {
        return olderVersionSourceFileExtension;
    }

    public String getNextVersionBuildtimePath() {
        return nextVersionBuildtimePath;
    }

    public String getNextVersionRuntimePath() {
        return nextVersionRuntimePath;
    }

    public SourceFileLocation getNextVersionSourceFileLocation() {
        return nextVersionSourceFileLocation;
    }

    public SourceFileExtension getNextVersionSourceFileExtension() {
        return nextVersionSourceFileExtension;
    }

    public String getNextVersionDMNPath() {
        return nextVersionDMNPath;
    }

    public String getNextVersionEventProcessingPath() {
        return nextVersionEventProcessingPath;
    }

    public String getNextVersionEventProcessingMultithreadedPath() {
        return nextVersionEventProcessingMultithreadedPath;
    }

    public String getNextVersionOopathPath() {
        return nextVersionOopathPath;
    }

    public String getNextVersionOperatorsPath() {
        return nextVersionOperatorsPath;
    }

    public String getNextVersionSessionPath() {
        return nextVersionSessionPath;
    }

    public String getNextVersionThroughputPath() {
        return nextVersionThroughputPath;
    }

    public String getNextVersionRuntimeMultithreadedPath() {
        return nextVersionRuntimeMultithreadedPath;
    }

    public String getCurrentVersionEventProcessingPath() {
        return currentVersionEventProcessingPath;
    }

    public String getCurrentVersionEventProcessingMultithreadedPath() {
        return currentVersionEventProcessingMultithreadedPath;
    }

    public String getCurrentVersionOopathPath() {
        return currentVersionOopathPath;
    }

    public String getCurrentVersionOperatorsPath() {
        return currentVersionOperatorsPath;
    }

    public String getCurrentVersionSessionPath() {
        return currentVersionSessionPath;
    }

    public String getCurrentVersionThroughputPath() {
        return currentVersionThroughputPath;
    }

    public String getCurrentVersionRuntimeMultithreadedPath() {
        return currentVersionRuntimeMultithreadedPath;
    }

    public String getPreviousVersionEventProcessingPath() {
        return previousVersionEventProcessingPath;
    }

    public String getPreviousVersionEventProcessingMultithreadedPath() {
        return previousVersionEventProcessingMultithreadedPath;
    }

    public String getPreviousVersionOopathPath() {
        return previousVersionOopathPath;
    }

    public String getPreviousVersionOperatorsPath() {
        return previousVersionOperatorsPath;
    }

    public String getPreviousVersionSessionPath() {
        return previousVersionSessionPath;
    }

    public String getPreviousVersionThroughputPath() {
        return previousVersionThroughputPath;
    }

    public String getPreviousVersionRuntimeMultithreadedPath() {
        return previousVersionRuntimeMultithreadedPath;
    }

    public String getOlderVersionEventProcessingPath() {
        return olderVersionEventProcessingPath;
    }

    public String getOlderVersionEventProcessingMultithreadedPath() {
        return olderVersionEventProcessingMultithreadedPath;
    }

    public String getOlderVersionOopathPath() {
        return olderVersionOopathPath;
    }

    public String getOlderVersionOperatorsPath() {
        return olderVersionOperatorsPath;
    }

    public String getOlderVersionSessionPath() {
        return olderVersionSessionPath;
    }

    public String getOlderVersionThroughputPath() {
        return olderVersionThroughputPath;
    }

    public String getOlderVersionRuntimeMultithreadedPath() {
        return olderVersionRuntimeMultithreadedPath;
    }

    public String getCurrentVersionDMNPath() {
        return currentVersionDMNPath;
    }

    public String getPreviousVersionDMNPath() {
        return previousVersionDMNPath;
    }

    public String getOlderVersionDMNPath() {
        return olderVersionDMNPath;
    }

}
