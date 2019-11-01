package org.benchmarks.reports.util;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonLoader {

    public static JSONArray getParsedData(FileReader reader) {
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

    public static JSONArray getDataFromJson(String filename) {
        JSONArray jsonArray = null;

        try {
            FileReader reader = new FileReader(filename);
            jsonArray = getParsedData(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

}
