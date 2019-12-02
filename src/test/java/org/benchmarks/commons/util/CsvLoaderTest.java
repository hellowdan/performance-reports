package org.benchmarks.commons.util;

import java.io.File;
import java.io.IOException;

import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.json.simple.JSONArray;
import org.junit.Assert;
import org.junit.Test;

public class CsvLoaderTest {

    @Test
    public void getDataFromCSVWebTest() {
        JSONArray dataJson;
        String filePath = "https://rhba-qe-jenkins.rhev-ci-vms.eng.rdu2.redhat.com/job/BxMS/job/RHPAM-7.5-brew/job/performance/job/bre-perf/job/blessed-perf-turtle-buildtime-benchmarks/lastSuccessfulBuild/artifact/results.csv";

        CsvLoader csvLoader = new CsvLoader();
        dataJson = csvLoader.getDataFromCSV(filePath, JenkinsReportLocation.WEB);

        Assert.assertNotNull(dataJson);

        //TO-DO need to publish a CSV file in a HTTP server (with HTTPS)
    }

    @Test
    public void getDataFromCSVClasspathTest() {
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