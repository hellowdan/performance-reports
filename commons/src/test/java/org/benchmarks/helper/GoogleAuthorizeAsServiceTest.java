package org.benchmarks.helper;

import com.google.auth.oauth2.GoogleCredentials;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GoogleAuthorizeAsServiceTest {

    private static String serviceAccountJsonPath = "/service_account.json";
    private static String serviceAccountFailJsonPath = "/test.main.java.org.benchmarks/resources/service_account_auth_fail.json";

    @Test
    public void authorizeTest() {
        GoogleAuthorizeAsService googleAuthorizeAsService = new GoogleAuthorizeAsService();
        Boolean authorize = googleAuthorizeAsService.authorize(serviceAccountJsonPath);
        assertThat(authorize, is(true));
    }

    @Test
    public void getCredentialTest() {
        GoogleCredentials credentials = null;
        GoogleAuthorizeAsService googleAuthorizeAsService = new GoogleAuthorizeAsService();
        if (googleAuthorizeAsService.authorize(serviceAccountJsonPath)) {
            credentials = googleAuthorizeAsService.getCredentials();
        }
        Assert.assertNotNull(credentials);
    }
}