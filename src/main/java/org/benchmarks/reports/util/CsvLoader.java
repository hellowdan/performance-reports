package org.benchmarks.reports.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.benchmarks.reports.data.FileLocation;
import org.json.simple.JSONArray;

import java.io.*;
import java.util.List;
import java.util.Map;

/*This class exists if changing the Jenkins output to Json is not an option.
 * It can be removed later*/
public class CsvLoader {

    public static JSONArray getDataFromCSV(String csvFile, FileLocation fileLocation) {
        JSONArray result = null;
        File output = new File("output.json");

        Object input = null;

        try {
            if (fileLocation == FileLocation.WEB) {
                input = HttpOperations.getFileObjectFromWeb(csvFile);
            } else if (fileLocation == FileLocation.LOCAL) {
                input = new File(csvFile);
            }

            CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
            CsvMapper csvMapper = new CsvMapper();

            List<Object> readAll = null;
            if (fileLocation == FileLocation.WEB) {
                readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues((BufferedReader) input).readAll();
            } else if (fileLocation == FileLocation.LOCAL) {
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