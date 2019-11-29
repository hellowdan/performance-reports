package org.benchmarks.commons.api.helper;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleAuthorizeAsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleAuthorizeAsService.class);
    private GoogleCredentials credentials;

    public Boolean authorize() {
        try {
            FileInputStream serviceAccountStream = new FileInputStream(DroolsPropertiesLoader.getInstance().getGoogleAppApiKeyFile());
            credentials = ServiceAccountCredentials.fromStream(serviceAccountStream);
        } catch (IOException e) {
            LOGGER.debug("File cannot be read.", e);
        }

        return (this.credentials != null);
    }

    public GoogleCredentials getCredentials() {
        return this.credentials;
    }
}
