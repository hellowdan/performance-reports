package org.benchmarks.commons.api.helper;

import com.google.auth.oauth2.GoogleCredentials;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GoogleAuthorizeAsServiceTest {

    @Test
    public void authorizeTest() {
        GoogleAuthorizeAsService googleAuthorizeAsService = new GoogleAuthorizeAsService();
        Boolean authorize = googleAuthorizeAsService.authorize();
        assertThat(authorize, is(true));
    }

    @Test
    public void getCredentialTest() {
        GoogleCredentials credentials = null;
        GoogleAuthorizeAsService googleAuthorizeAsService = new GoogleAuthorizeAsService();
        if(googleAuthorizeAsService.authorize()) {
            credentials = googleAuthorizeAsService.getCredentials();
        }
        Assert.assertNotNull(credentials);
    }
}