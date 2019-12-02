package org.benchmarks.commons.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.json.simple.JSONArray;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonLoaderTest {

    @Test
    public void getParsedDataTest() {
        String filePath = "/buildtime.json";

        Reader reader = new InputStreamReader(this.getClass().getResourceAsStream(filePath));
        JsonLoader jsonLoader = new JsonLoader();
        JSONArray jsonArray = jsonLoader.getParsedData(reader);

        Assert.assertNotNull(jsonArray);
    }

    @Test
    public void getDataFromJsonWebTest() {

//        TO-DO need to publish a JSON file in a HTTP server
    }

    @Test
    public void getDataFromJsonClasspathTest() {
        JSONArray dataJson;
        String filePath = "/buildtime.json";

        JsonLoader jsonLoader = new JsonLoader();
        dataJson = jsonLoader.getDataFromJson(filePath, JenkinsReportLocation.CLASSPATH);

        Assert.assertNotNull(dataJson);
    }

    @Test
    public void getDataFromJsonLocalTest() throws IOException {
        JSONArray dataJson;
        String filePath = "/buildtime.json";
        File input;

        input = new File(this.getClass().getResource(filePath).getFile());
        JsonLoader jsonLoader = new JsonLoader();
        dataJson = jsonLoader.getDataFromJson(input.getCanonicalPath(), JenkinsReportLocation.LOCAL);

        Assert.assertNotNull(dataJson);
    }

    @Test
    public void isJSONValidTest() {
    }
}