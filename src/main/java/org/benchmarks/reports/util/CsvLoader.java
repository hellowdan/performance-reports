package org.benchmarks.reports.util;

import java.io.*;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.json.simple.JSONArray;

/*This class exists if changing the Jenkins output to Json is not an option.
* It can be removed later*/
public class CsvLoader {

    public static JSONArray getDataFromCSV(String csvFile){
        JSONArray result = null;
        File output = new File("output.json");

        try {
            File input = new File(csvFile);

            CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
            CsvMapper csvMapper = new CsvMapper();

            List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();
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