package org.benchmarks.reports.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.benchmarks.reports.data.FileLocation;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*This class exists if changing the Jenkins output to Json is not an option.
 * It can be removed later*/
public class CsvLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvLoader.class);

    public JSONArray getDataFromCSV(String csvFile, FileLocation fileLocation) {
        JSONArray result = null;
        String outputFileName = "output.json";
        File output = new File(outputFileName);
        Object input = null;

        try {
            if (fileLocation == FileLocation.WEB) {
                input = HttpOperations.getFileObjectFromWeb(csvFile);
            } else if (fileLocation == FileLocation.CLASSPATH) {
                input = this.getClass().getResourceAsStream(csvFile);
            } else if (fileLocation == FileLocation.LOCAL) {
                input = new File(csvFile);
            }

            CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
            CsvMapper csvMapper = new CsvMapper();

            List<Object> readAll = null;
            if (fileLocation == FileLocation.WEB) {
                readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues((BufferedReader) input).readAll();
            } else if (fileLocation == FileLocation.CLASSPATH) {
                readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues((BufferedInputStream) input).readAll();
            } else if (fileLocation == FileLocation.LOCAL) {
                readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues((File) input).readAll();
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(output, readAll);

            FileReader reader = new FileReader(output);
            JsonLoader jsonLoader = new JsonLoader();
            result = jsonLoader.getParsedData(reader);
        } catch (FileNotFoundException e) {
            LOGGER.debug("File not found: " + outputFileName, e);
        } catch (IOException e) {
            LOGGER.debug("File cannot be read: " + outputFileName, e);
        } finally {
            if (output != null) {
                output.delete();
            }
        }
        return result;
    }
}