package org.benchmarks.commons.api.helper;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GoogleAuthorizeAsServiceTest {

    private static String serviceAccountJsonPath = "/service_account.json";
    private static String serviceAccountFailJsonPath = "/service_account_auth_fail.json";

    @Test
    public void authorizeTest() throws IOException {
        GoogleAuthorizeAsService googleAuthorizeAsService = new GoogleAuthorizeAsService();
        Boolean authorize = googleAuthorizeAsService.authorize(serviceAccountJsonPath);
        assertThat(authorize, is(true));
    }

    @Test
    public void authorizeFailTest() throws IOException {
        try {
            GoogleAuthorizeAsService googleAuthorizeAsService = new GoogleAuthorizeAsService();
            Boolean authorize = googleAuthorizeAsService.authorize(serviceAccountFailJsonPath);
        } catch (IOException e) {
            assert(true);
        }
    }

    @Test
    public void getCredentialTest() throws IOException {
        GoogleCredentials credentials = null;
        GoogleAuthorizeAsService googleAuthorizeAsService = new GoogleAuthorizeAsService();
        if (googleAuthorizeAsService.authorize(serviceAccountJsonPath)) {
            credentials = googleAuthorizeAsService.getCredentials();
        }
        Assert.assertNotNull(credentials);
    }
}