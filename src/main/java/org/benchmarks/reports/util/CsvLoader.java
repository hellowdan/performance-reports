package org.benchmarks.reports.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.json.simple.JSONArray;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/*This class exists if changing the Jenkins output to Json is not an option.
 * It can be removed later*/
public class CsvLoader {

    public static JSONArray getDataFromCSV(String csvFile) {
        JSONArray result = null;
        File output = new File("output.json");

        boolean isWeb = csvFile.trim().toLowerCase().startsWith("http://") || csvFile.trim().toLowerCase().startsWith("https://");
        Object input = null;

        try {
            if (isWeb) {
                URL urlCSV = new URL(csvFile);
                URLConnection urlConn = urlCSV.openConnection();
                InputStreamReader inputCSV = new InputStreamReader((urlConn).getInputStream());
                input = new BufferedReader(inputCSV);
            } else {
                input = new File(csvFile);
            }

            CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
            CsvMapper csvMapper = new CsvMapper();

            List<Object> readAll = null;
            if (isWeb) {
                readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues((BufferedReader) input).readAll();
            } else {
                readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues((File) input).readAll();
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(output, readAll);

            FileReader reader = new FileReader(output);
            result = JsonLoader.getParsedData(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            output.delete();
        }

        return result;
    }
}