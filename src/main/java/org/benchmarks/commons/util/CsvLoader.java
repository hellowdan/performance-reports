package org.benchmarks.commons.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*This class exists if changing the Jenkins output to Json is not an option.
 * It can be removed later*/
public class CsvLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvLoader.class);

    public JSONArray getDataFromCSV(String csvFile, JenkinsReportLocation jenkinsReportLocation) {
        JSONArray result = null;
        String outputFileName = "output.json";
        File output = new File(outputFileName);
        Object input = null;

        try {
            if (jenkinsReportLocation == JenkinsReportLocation.WEB) {
                input = HttpOperations.getFileObjectFromWeb(csvFile);
            } else if (jenkinsReportLocation == JenkinsReportLocation.CLASSPATH) {
                input = this.getClass().getResourceAsStream(csvFile);
            } else if (jenkinsReportLocation == JenkinsReportLocation.LOCAL) {
                input = new File(csvFile);
            }

            CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
            CsvMapper csvMapper = new CsvMapper();

            List<Object> readAll = null;
            if (jenkinsReportLocation == JenkinsReportLocation.WEB) {
                readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues((BufferedReader) input).readAll();
            } else if (jenkinsReportLocation == JenkinsReportLocation.CLASSPATH) {
                readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues((BufferedInputStream) input).readAll();
            } else if (jenkinsReportLocation == JenkinsReportLocation.LOCAL) {
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