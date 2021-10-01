package org.benchmarks.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.benchmarks.exceptions.FileCannotBeFoundException;

public class ReportProperties {

    private static String NEXT_VERSION = "next_version";
    private static String CURRENT_VERSION = "current_version";
    private static String PREVIOUS_VERSION = "previous_version";
    private static String OLDER_VERSION = "older_version";
    private static String REPORT_DATE = "report_date";
    private static String AUTHOR = "author";
    private static String EMAIL_AUTHOR = "email_author";

    private static String FILES_TITLE = "files_title";
    private static String TEMPLATE_TITLE = "template_title";
    private static String TEMPLATE_BRE_DOC_ID = "template_bre_doc_id";
    private static String TEMPLATE_DMN_DOC_ID = "template_dmn_doc_id";


    private static String RESULT_PARENT_FOLDER_ID = "result_parent_folder_id";
    private static String FOLDER_TITLE = "folder_title";
    private static String GOOGLE_APP_API_KEY_FILE = "google_app_api_key_file";

    protected Properties properties = null;
    protected String fileName;
    private String nextVersion;
    private String currentVersion;
    private String previousVersion;
    private String olderVersion;
    private String reportDate;
    private String author;
    private String emailAuthor;

    private String filesTitle;
    private String templateTitle;
    private String templateBreDocID;
    private String templateDmnDocID;

    private String resultParentFolderID;
    private String folderTitle;
    private String googleAppApiKeyFile;

    public ReportProperties(String fileName) throws IOException {

        this.fileName = fileName;
        loadPropertiesFile();

        if (this.properties.containsKey(NEXT_VERSION)) {
            this.nextVersion = this.properties.getProperty(NEXT_VERSION);
        }
        if (this.properties.containsKey(CURRENT_VERSION)) {
            this.currentVersion = this.properties.getProperty(CURRENT_VERSION);
        }
        if (this.properties.containsKey(PREVIOUS_VERSION)) {
            this.previousVersion = this.properties.getProperty(PREVIOUS_VERSION);
        }
        if (this.properties.containsKey(OLDER_VERSION)) {
            this.olderVersion = this.properties.getProperty(OLDER_VERSION);
        }
        if (this.properties.containsKey(REPORT_DATE)) {
            this.reportDate = this.properties.getProperty(REPORT_DATE);
            if(this.reportDate.equals("")){
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime now = LocalDateTime.now();
                this.reportDate = dtf.format(now);
            }
        }
        if (this.properties.containsKey(AUTHOR)) {
            this.author = this.properties.getProperty(AUTHOR);
        }
        if (this.properties.containsKey(EMAIL_AUTHOR)) {
            this.emailAuthor = this.properties.getProperty(EMAIL_AUTHOR);
        }
        if (this.properties.containsKey(FILES_TITLE)) {
            this.filesTitle = this.properties.getProperty(FILES_TITLE);
        }
        if (this.properties.containsKey(TEMPLATE_TITLE)) {
            this.templateTitle = this.properties.getProperty(TEMPLATE_TITLE);
        }
        if (this.properties.containsKey(TEMPLATE_BRE_DOC_ID)) {
            this.templateBreDocID = this.properties.getProperty(TEMPLATE_BRE_DOC_ID);
        }
        if (this.properties.containsKey(TEMPLATE_DMN_DOC_ID)) {
            this.templateDmnDocID = this.properties.getProperty(TEMPLATE_DMN_DOC_ID);
        }

        if (this.properties.containsKey(RESULT_PARENT_FOLDER_ID)) {
            this.resultParentFolderID = this.properties.getProperty(RESULT_PARENT_FOLDER_ID);
        }
        if (this.properties.containsKey(FOLDER_TITLE)) {
            this.folderTitle = this.properties.getProperty(FOLDER_TITLE);
            if(this.folderTitle.equals("")){
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                this.folderTitle = dtf.format(now);
            }
        }
        if (this.properties.containsKey(GOOGLE_APP_API_KEY_FILE)) {
            this.googleAppApiKeyFile = this.properties.getProperty(GOOGLE_APP_API_KEY_FILE);
        }
    }

    private Boolean loadPropertiesFile() throws IOException {
        Boolean result;
        InputStream inputStream;
        this.properties = new Properties();

        try {
            inputStream = this.getClass().getResourceAsStream(this.fileName);

            if (inputStream != null) {
                this.properties.load(inputStream);
                result = true;
            } else {
                throw new FileNotFoundException();
            }
        } catch (IOException e) {
            throw new FileCannotBeFoundException(this.fileName, e);
        }

        return result;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getCurrentVersion() {
        return currentVersion;
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

    public String getFilesTitle() {
        return filesTitle;
    }

    public String getTemplateTitle() {
        return templateTitle;
    }

    public String getTemplateBreDocID() { return templateBreDocID; }

    public String getTemplateDmnDocID() { return templateDmnDocID; }

    public String getResultParentFolderID() {
        return resultParentFolderID;
    }

    public String getFolderTitle() {
        return folderTitle;
    }

    public String getGoogleAppApiKeyFile() {
        return googleAppApiKeyFile;
    }

    public String getNextVersion() {
        return nextVersion;
    }

}
