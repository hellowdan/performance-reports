package org.benchmarks.commons.util;

import java.io.File;
import java.io.IOException;

import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.json.simple.JSONArray;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CsvLoaderTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDataFromCSVWebTest() throws IOException {
        JSONArray dataJson;
        String filePath = "http://localhost:8585/buildtime.csv";

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