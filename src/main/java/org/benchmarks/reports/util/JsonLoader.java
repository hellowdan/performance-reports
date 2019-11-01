package org.benchmarks.reports.util;

import org.json.simple.JSONArray;
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

    public static JSONArray getDataFromJson(String jsonFile) {
        JSONArray jsonArray = null;
        boolean isWeb = jsonFile.trim().toLowerCase().startsWith("http://") || jsonFile.trim().toLowerCase().startsWith("https://");
        Reader input = null;

        try {
            if (isWeb) {
                URL urlCSV = new URL(jsonFile);
                URLConnection urlConn = urlCSV.openConnection();
                InputStreamReader inputCSV = new InputStreamReader((urlConn).getInputStream());
                input = new BufferedReader(inputCSV);
            } else {
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

}
