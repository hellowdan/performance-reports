package org.benchmarks.commons.api.helper;

import com.google.auth.oauth2.GoogleCredentials;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GoogleAuthorizeAsServiceTest {

    @Test
    public void authorizeTest() {
        DroolsPropertiesLoader droolsProperties = new DroolsPropertiesLoader("/drools-auth-test.properties");

        GoogleAuthorizeAsService googleAuthorizeAsService = new GoogleAuthorizeAsService();
        Boolean authorize = googleAuthorizeAsService.authorize(droolsProperties.getGoogleAppApiKeyFile());
        assertThat(authorize, is(true));
    }

    @Test
    public void authorizeFailTest() {
        DroolsPropertiesLoader droolsProperties = new DroolsPropertiesLoader("/drools-auth-fail-test.properties");

        GoogleAuthorizeAsService googleAuthorizeAsService = new GoogleAuthorizeAsService();
        Boolean authorize = googleAuthorizeAsService.authorize(droolsProperties.getGoogleAppApiKeyFile());
        assertThat(authorize, is(false));
    }

    @Test
    public void getCredentialTest() {
        DroolsPropertiesLoader droolsProperties = new DroolsPropertiesLoader("/drools-auth-test.properties");
        GoogleCredentials credentials = null;
        GoogleAuthorizeAsService googleAuthorizeAsService = new GoogleAuthorizeAsService();
        if (googleAuthorizeAsService.authorize(droolsProperties.getGoogleAppApiKeyFile())) {
            credentials = googleAuthorizeAsService.getCredentials();
        }
        Assert.assertNotNull(credentials);
    }
}