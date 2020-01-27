package org.benchmarks.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.benchmarks.definitions.JenkinsReportLocation;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class JsonLoaderTest {

    @Test
    public void getParsedDataTest() throws IOException, ParseException {
        String filePath = "/__files/buildtime.json";

        Reader reader = new InputStreamReader(this.getClass().getResourceAsStream(filePath));
        JsonLoader jsonLoader = new JsonLoader();
        JSONArray jsonArray = jsonLoader.getParsedDataArray(reader);

        Assert.assertNotNull(jsonArray);
    }

    @Test
    public void getDataFromJsonClasspathTest() throws IOException {
        JSONArray dataJson;
        String filePath = "/__files/buildtime.json";

        JsonLoader jsonLoader = new JsonLoader();
        dataJson = jsonLoader.getDataFromJsonArray(filePath, JenkinsReportLocation.CLASSPATH);

        Assert.assertNotNull(dataJson);
    }

    @Test
    public void getDataFromJsonLocalTest() throws IOException {
        JSONArray dataJson;
        String filePath = "/__files/buildtime.json";
        File input;

        input = new File(this.getClass().getResource(filePath).getFile());
        JsonLoader jsonLoader = new JsonLoader();
        dataJson = jsonLoader.getDataFromJsonArray(input.getCanonicalPath(), JenkinsReportLocation.LOCAL);

        Assert.assertNotNull(dataJson);
    }

}