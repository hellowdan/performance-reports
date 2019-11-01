package org.benchmarks.reports.builder;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.docs.v1.Docs;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import org.benchmarks.reports.util.GoogleAuthorize;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class GoogleService {

    private Sheets sheets;
    private Drive drive;
    private Docs docs;

    public GoogleService() throws IOException, GeneralSecurityException {
        Credential credential = GoogleAuthorize.authorize();

        this.docs = new Docs.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential).setApplicationName("performance-reports").build();
        this.sheets = new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential).setApplicationName("performance-reports").build();
        this.drive = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential).setApplicationName("performance-reports").build();
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
