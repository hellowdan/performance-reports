package org.benchmarks.commons.util;

import java.io.File;
import java.io.IOException;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.api.services.drive.Drive;
import org.benchmarks.commons.api.helper.GoogleDriveService;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.exceptions.GoogleCredentialException;
import org.json.simple.JSONArray;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class CsvLoaderTest {

    private static WireMockServer wireMockServer;

    @BeforeClass
    public static void setUpServer() {
        wireMockServer = new WireMockServer(wireMockConfig().port(8888).httpsPort(8889).withRootDirectory("src/test/java/org/benchmarks/drools/resources/"));
        wireMockServer.start();
    }

    @AfterClass
    public static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    public void getDataFromCSVWebTest() throws IOException {
        JSONArray dataJson;
        String filePath = "http://127.0.0.1:8888/buildtime.csv";

        CsvLoader csvLoader = new CsvLoader();
        dataJson = csvLoader.getDataFromCSV(filePath, JenkinsReportLocation.WEB);

        Assert.assertNotNull(dataJson);
    }

    @Test
    public void getDataFromCSVClasspathTest() throws IOException {
        JSONArray dataJson;
        String filePath = "/buildtime.csv";

        CsvLoader csvLoader = new CsvLoader();
        dataJson = csvLoader.getDataFromCSV(filePath, JenkinsReportLocation.CLASSPATH);

        Assert.assertNotNull(dataJson);
    }

    @Test
    public void getDataFromCSVLocalTest() throws IOException {
        JSONArray dataJson;
        String filePath = "/buildtime.csv";
        File input;

        input = new File(this.getClass().getResource(filePath).getFile());
        CsvLoader csvLoader = new CsvLoader();
        dataJson = csvLoader.getDataFromCSV(input.getCanonicalPath(), JenkinsReportLocation.LOCAL);

        Assert.assertNotNull(dataJson);
    }
}