package org.benchmarks.reports.builder;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.docs.v1.Docs;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import org.benchmarks.reports.util.GoogleAuthorizeAsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class GoogleDriveService {

    private Sheets sheets;
    private Drive drive;
    private Docs docs;

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveHelper.class);

    public GoogleDriveService() {
        try {
            GoogleCredential credential = GoogleAuthorizeAsService.authorize();

            this.docs = new Docs.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential).setApplicationName("performance-reports").build();
            this.sheets = new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential).setApplicationName("performance-reports").build();
            this.drive = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential).setApplicationName("performance-reports").build();
        } catch (GeneralSecurityException | IOException e) {
            LOGGER.debug("Failed to get Google Drive credentials.", e);
        }
    }

    public Sheets getSheets() {
        return sheets;
    }

    public Drive getDrive() {
        return drive;
    }

    public Docs getDocs() {
        return docs;
    }
}