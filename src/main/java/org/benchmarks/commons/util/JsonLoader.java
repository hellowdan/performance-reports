package org.benchmarks.commons.util;

import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class JsonLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonLoader.class);

    public JSONArray getParsedData(Reader reader) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();

        try {
            Object obj = jsonParser.parse(reader);
            jsonArray = (JSONArray) obj;
        } catch (ParseException e) {
            LOGGER.debug("Content cannot be parsed.", e);
            throw new ParseException(e.getErrorType());
        } catch (IOException e) {
            LOGGER.debug("Content cannot be read.", e);
            throw new IOException("Content cannot be parsed.", e);
        }

        return jsonArray;
    }

    public JSONArray getDataFromJson(String jsonFile, JenkinsReportLocation jenkinsReportLocation) throws IOException {
        JSONArray jsonArray = null;
        Reader input = null;

        try {
            if (jenkinsReportLocation == JenkinsReportLocation.WEB) {
                input = HttpOperations.getFileReaderFromWeb(jsonFile);
            } else if (jenkinsReportLocation == JenkinsReportLocation.CLASSPATH) {
                input = new InputStreamReader(this.getClass().getResourceAsStream(jsonFile));
            } else if (jenkinsReportLocation == JenkinsReportLocation.LOCAL) {
                input = new FileReader(jsonFile);
            }
            jsonArray = getParsedData(input);
        } catch (FileNotFoundException | ParseException e) {
            LOGGER.debug("File not found: " + jsonFile, e);
            throw new IOException("File not found: " + jsonFile, e);
        }

        return jsonArray;
    }

    public static boolean isJSONValid(String jsonInString) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(jsonInString);
            JSONArray jsonArray = (JSONArray) obj;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
