package org.benchmarks.util;

import java.io.File;
import java.io.IOException;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.benchmarks.definitions.SourceFileLocation;
import org.json.simple.JSONArray;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class CsvLoaderTest {

    private static WireMockServer wireMockServer;

    @BeforeClass
    public static void setUpServer() {
        wireMockServer = new WireMockServer(wireMockConfig().port(8888).httpsPort(8889).withRootDirectory("src/test/resources/"));
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
        dataJson = csvLoader.getDataFromCSV(filePath, SourceFileLocation.WEB);

        Assert.assertNotNull(dataJson);
    }

    @Test
    public void getDataFromCSVClasspathTest() throws IOException {
        JSONArray dataJson;
        String filePath = "/__files/buildtime.csv";

        CsvLoader csvLoader = new CsvLoader();
        dataJson = csvLoader.getDataFromCSV(filePath, SourceFileLocation.CLASSPATH);

        Assert.assertNotNull(dataJson);
    }

    @Test
    public void getDataFromCSVLocalTest() throws IOException {
        JSONArray dataJson;
        String filePath = "/__files/buildtime.csv";
        File input;

        input = new File(this.getClass().getResource(filePath).getFile());
        CsvLoader csvLoader = new CsvLoader();
        dataJson = csvLoader.getDataFromCSV(input.getCanonicalPath(), SourceFileLocation.LOCAL);

        Assert.assertNotNull(dataJson);
    }
}