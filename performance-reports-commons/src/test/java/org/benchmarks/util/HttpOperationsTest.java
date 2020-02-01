package org.benchmarks.util;

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
                                                    .bindAddress("127.0.0.1")
                                                    .withRootDirectory("src/test/resources/")
        );
        wireMockServer.start();
    }

    @AfterClass
    public static void tearDown() {
        wireMockServer.stop();
    }

    private String getUrl(Boolean useSSL) {
        if (useSSL) {
            return String.format("https://127.0.0.1:%d/buildtime.json", wireMockServer.httpsPort());
        } else {
            return String.format("http://localhost:%d/buildtime.json", wireMockServer.port());
        }
    }

//    @Test
//    public void getFileObjectFromWebHTTPSTest() throws IOException {
//        String serverUrl = getUrl(true);
//        wireMockServer.stubFor(get(urlEqualTo(serverUrl))
//                                       .withHeader("Accept", equalTo("text/xml"))
//                                       .willReturn(aResponse()
//                                                           .withStatus(200)
//                                                           .withHeader("Content-Type", "application/json")
//                                                           .withBodyFile("test.main.java.org.benchmarks/resources/buildtime.json")));
//
//        Object response = HttpOperations.getFileObjectFromWeb(serverUrl);
//
//        Assert.assertNotNull(response);
//    }

    @Test
    public void getJsonFileObjectFromWebHTTPTest() throws IOException {
        String serverUrl = getUrl(false);
        wireMockServer.stubFor(get(urlEqualTo(serverUrl))
                                       .withHeader("Accept", equalTo("text/xml"))
                                       .willReturn(aResponse()
                                                           .withStatus(200)
                                                           .withHeader("Content-Type", "application/json")
                                                           .withBodyFile("test.main.java.org.benchmarks/resources/buildtime.json")));

        Object response = HttpOperations.getFileObjectFromWeb(serverUrl);

        Assert.assertNotNull(response);
    }

    @Test
    public void getCsvFileObjectFromWebHTTPTest() throws IOException {
        String serverUrl = getUrl(false);
        wireMockServer.stubFor(get(urlEqualTo(serverUrl))
                                       .withHeader("Accept", equalTo("text/xml"))
                                       .willReturn(aResponse()
                                                           .withStatus(200)
                                                           .withHeader("Content-Type", "application/csv")
                                                           .withBodyFile("test.main.java.org.benchmarks/resources/buildtime.csv")));

        Object response = HttpOperations.getFileObjectFromWeb(serverUrl);

        Assert.assertNotNull(response);
    }
}