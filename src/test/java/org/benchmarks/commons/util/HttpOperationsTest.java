package org.benchmarks.commons.util;

import java.io.IOException;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class HttpOperationsTest {

    private static WireMockServer wireMockServer;

    @BeforeClass
    public static void setUp() {
        wireMockServer = new WireMockServer(wireMockConfig()
                                                    .port(8888)
                                                    .httpsPort(8889)
                                                    .withRootDirectory("src/test/java/org/benchmarks/drools/resources/")
        );
        wireMockServer.start();
    }

    @AfterClass
    public static void tearDown() {
        wireMockServer.stop();
    }

    private String buildApiMethodUrl(Boolean useSSL) {
        if(useSSL) {
            return String.format("https://127.0.0.1:%d/buildtime.json", wireMockServer.httpsPort());
        } else {
            return String.format("http://127.0.0.1:%d/buildtime.json", wireMockServer.port());
        }
    }

    @Test
    public void getFileObjectFromWebHTTPSTest() throws IOException {
        String serverUrl = buildApiMethodUrl(true);
        wireMockServer.stubFor(get(urlEqualTo(serverUrl))
                                       .withHeader("Accept", equalTo("text/xml"))
                                       .willReturn(aResponse()
                                                           .withStatus(200)
                                                           .withHeader("Content-Type", "application/json")
                                                           .withBodyFile("buildtime.json")));

        Object response = HttpOperations.getFileObjectFromWeb(serverUrl);

        Assert.assertNotNull(response);
    }

    @Test
    public void getJsonFileObjectFromWebHTTPTest() throws IOException {
        String serverUrl = buildApiMethodUrl(false);
        wireMockServer.stubFor(get(urlEqualTo(serverUrl))
                                       .withHeader("Accept", equalTo("text/xml"))
                                       .willReturn(aResponse()
                                       .withStatus(200)
                                       .withHeader("Content-Type", "application/json")
                                       .withBodyFile("buildtime.json")));

        Object response = HttpOperations.getFileObjectFromWeb(serverUrl);

        Assert.assertNotNull(response);
    }

    @Test
    public void getCsvFileObjectFromWebHTTPTest() throws IOException {
        String serverUrl = buildApiMethodUrl(false);
        wireMockServer.stubFor(get(urlEqualTo(serverUrl))
                                       .withHeader("Accept", equalTo("text/xml"))
                                       .willReturn(aResponse()
                                                           .withStatus(200)
                                                           .withHeader("Content-Type", "application/csv")
                                                           .withBodyFile("buildtime.csv")));

        Object response = HttpOperations.getFileObjectFromWeb(serverUrl);

        Assert.assertNotNull(response);
    }
}