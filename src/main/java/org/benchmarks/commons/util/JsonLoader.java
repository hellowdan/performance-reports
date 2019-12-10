package org.benchmarks.commons.util;

import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.exceptions.FileCannotBeFoundException;
import org.benchmarks.commons.exceptions.FileCannotBeParsedException;
import org.benchmarks.commons.exceptions.FileCannotBeReadException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.Reader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class JsonLoader {

    public JSONArray getParsedData(Reader reader) throws IOException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray;

        try {
            Object obj = jsonParser.parse(reader);
            jsonArray = (JSONArray) obj;
        } catch (ParseException e) {
            throw new FileCannotBeParsedException(e.toString());
        } catch (IOException e) {
            throw new FileCannotBeReadException(e);
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
        } catch (FileNotFoundException e) {
            throw new FileCannotBeFoundException(jsonFile, e);
        }

        return jsonArray;
    }

    public static Boolean isJSONValid(String jsonInString) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(jsonInString);
            JSONArray jsonArray = (JSONArray) obj;
            return jsonArray != null;
        } catch (Exception e) {
            return false;
        }
    }
}
