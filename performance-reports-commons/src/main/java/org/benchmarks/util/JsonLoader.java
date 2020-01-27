package org.benchmarks.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.benchmarks.definitions.JenkinsReportLocation;
import org.benchmarks.exceptions.FileCannotBeFoundException;
import org.benchmarks.exceptions.FileCannotBeParsedException;
import org.benchmarks.exceptions.FileCannotBeReadException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonLoader {

    public JSONArray getParsedDataArray(Reader reader) throws IOException {
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

    public JSONObject getParsedDataObject(Reader reader) throws IOException {
        JSONParser jsonParser = new JSONParser();
        Object obj = null;

        try {
            obj = jsonParser.parse(reader);
        } catch (ParseException e) {
            throw new FileCannotBeParsedException(e.toString());
        } catch (IOException e) {
            throw new FileCannotBeReadException(e);
        }

        return (JSONObject) obj;
    }

    public JSONArray getDataFromJsonArray(String jsonFile, JenkinsReportLocation jenkinsReportLocation) throws IOException {
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
            jsonArray = getParsedDataArray(input);
        } catch (FileNotFoundException e) {
            throw new FileCannotBeFoundException(jsonFile, e);
        }

        return jsonArray;
    }

    public JSONObject getDataFromJsonObject(String jsonFile, JenkinsReportLocation jenkinsReportLocation) throws IOException {
        Object obj = null;
        Reader input = null;

        try {
            if (jenkinsReportLocation == JenkinsReportLocation.WEB) {
                input = HttpOperations.getFileReaderFromWeb(jsonFile);
            } else if (jenkinsReportLocation == JenkinsReportLocation.CLASSPATH) {
                input = new InputStreamReader(this.getClass().getResourceAsStream(jsonFile));
            } else if (jenkinsReportLocation == JenkinsReportLocation.LOCAL) {
                input = new FileReader(jsonFile);
            }
            obj = getParsedDataObject(input);
        } catch (FileNotFoundException e) {
            throw new FileCannotBeFoundException(jsonFile, e);
        }

        return (JSONObject) obj;
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
