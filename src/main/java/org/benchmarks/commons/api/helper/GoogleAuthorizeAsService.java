package org.benchmarks.commons.api.helper;

import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleAuthorizeAsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleAuthorizeAsService.class);
    private GoogleCredentials credentials;

    public Boolean authorize(String googleAppApiKeyFile) throws IOException {
        String scopeSheets = "https://www.googleapis.com/auth/spreadsheets";
        String scopeDocs = "https://www.googleapis.com/auth/documents";
        String scopeDrive = "https://www.googleapis.com/auth/drive";

        try {
            InputStream serviceAccountStream = this.getClass().getResourceAsStream(googleAppApiKeyFile);
            credentials = ServiceAccountCredentials.fromStream(serviceAccountStream).
                    createScoped(Lists.newArrayList(scopeSheets, scopeDocs, scopeDrive));
            ;
        } catch (IOException e) {
            LOGGER.debug("File cannot be read.", e);
            throw new IOException("File cannot be read.", e);
        }

        return (this.credentials != null);
    }

    public GoogleCredentials getCredentials() {
        return this.credentials;
    }
}
