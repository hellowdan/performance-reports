package org.benchmarks.helper;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.docs.v1.Docs;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.benchmarks.exceptions.GoogleCredentialException;

public class GoogleDriveService {

    private Sheets sheets;
    private Drive drive;
    private Docs docs;

    public GoogleDriveService(String googleAppApiKeyFile) throws GoogleCredentialException {
        GoogleCredentials credentials = null;
        final String applicationName = "performance-reports";

        try {
            GoogleAuthorizeAsService googleAuthorizeAsService = new GoogleAuthorizeAsService();
            if (googleAuthorizeAsService.authorize(googleAppApiKeyFile)) {
                credentials = googleAuthorizeAsService.getCredentials();
            }

            this.docs = new Docs.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), new HttpCredentialsAdapter(credentials)).setApplicationName(applicationName).build();
            this.sheets = new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), new HttpCredentialsAdapter(credentials)).setApplicationName(applicationName).build();
            this.drive = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), new HttpCredentialsAdapter(credentials)).setApplicationName(applicationName).build();
        } catch (GeneralSecurityException | IOException e) {
            throw new GoogleCredentialException(e);
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
