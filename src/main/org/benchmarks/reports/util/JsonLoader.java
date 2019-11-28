package org.benchmarks.reports.util;

import org.benchmarks.reports.data.FileLocation;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class JsonLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonLoader.class);

    public JSONArray getParsedData(Reader reader) {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();

        try {
            Object obj = jsonParser.parse(reader);
            jsonArray = (JSONArray) obj;
        } catch (ParseException e) {
            LOGGER.debug("Content cannot be parsed.", e);
        } catch (IOException e) {
            LOGGER.debug("Content cannot be read.", e);
        }

        return jsonArray;
    }

    public JSONArray getDataFromJson(String jsonFile, FileLocation fileLocation) {
        JSONArray jsonArray = null;
        Reader input = null;

        try {
            if (fileLocation == FileLocation.WEB) {
                input = HttpOperations.getFileReaderFromWeb(jsonFile);
            } else if (fileLocation == FileLocation.CLASSPATH) {
                input = new InputStreamReader(this.getClass().getResourceAsStream(jsonFile));
            } else if (fileLocation == FileLocation.LOCAL) {
                input = new FileReader(jsonFile);
            }
            jsonArray = getParsedData(input);
        } catch (FileNotFoundException e) {
            LOGGER.debug("File not found: " + jsonFile, e);
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
