package org.benchmarks.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.benchmarks.exceptions.FileCannotBeFoundException;

public class PropertiesLoader {

    protected Properties properties = null;
    protected String fileName;
    private String currentVersion;
    private String previousVersion;
    private String olderVersion;
    private String reportDate;
    private String author;
    private String emailAuthor;
    private String filesTitle;
    private String templateTitle;
    private String templateDocID;
    private String templateSheetID;
    private String resultParentFolderID;
    private String folderTitle;
    private String googleAppApiKeyFile;

    public PropertiesLoader(String fileName) throws IOException {

        this.fileName = fileName;
        loadPropertiesFile();

        this.currentVersion = this.properties.getProperty("current_version");
        this.previousVersion = this.properties.getProperty("previous_version");
        this.olderVersion = this.properties.getProperty("older_version");
        this.reportDate = this.properties.getProperty("report_date");
        this.author = this.properties.getProperty("author");
        this.emailAuthor = this.properties.getProperty("email_author");
        this.filesTitle = this.properties.getProperty("files_title");
        this.templateTitle = this.properties.getProperty("template_title");
        this.templateDocID = this.properties.getProperty("template_doc_id");
        this.templateSheetID = this.properties.getProperty("template_sheet_id");
        this.resultParentFolderID = this.properties.getProperty("result_parent_folder_id");
        this.folderTitle = this.properties.getProperty("folder_title");
        this.googleAppApiKeyFile = this.properties.getProperty("google_app_api_key_file");
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
}
