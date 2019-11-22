package org.benchmarks.reports.util;

import org.benchmarks.reports.data.FileLocation;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class JsonLoader {

    public static JSONArray getParsedData(Reader reader) {
        org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
        JSONArray jsonArray = new JSONArray();

        try {
            Object obj = jsonParser.parse(reader);
            jsonArray = (JSONArray) obj;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static JSONArray getDataFromJson(String jsonFile, FileLocation fileLocation) {
        JSONArray jsonArray = null;
        Reader input = null;

        try {
            if (fileLocation == FileLocation.WEB) {
                input = HttpOperations.getFileReaderFromWeb(jsonFile);
            } else if (fileLocation == FileLocation.LOCAL) {
                input = new FileReader(jsonFile);
            }
            jsonArray = getParsedData(input);

        } catch (FileNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
