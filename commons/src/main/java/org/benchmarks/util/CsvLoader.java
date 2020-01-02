package org.benchmarks.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.benchmarks.definitions.JenkinsReportLocation;
import org.benchmarks.exceptions.FileCannotBeFoundException;
import org.benchmarks.exceptions.FileCannotBeReadException;
import org.benchmarks.exceptions.JsonGenerationFromCSVException;
import org.benchmarks.exceptions.JsonMappingFromCSVException;
import org.json.simple.JSONArray;

/*This class exists if changing the Jenkins output to Json is not an option.
 * It can be removed later*/
public class CsvLoader {

    public JSONArray getDataFromCSV(String csvFile, JenkinsReportLocation jenkinsReportLocation) throws IOException {
        JSONArray result;
        String outputFileName = "output.json";
        File output = new File(outputFileName);
        Object input = null;
        FileReader reader = null;

        try {
            if (jenkinsReportLocation == JenkinsReportLocation.WEB) {
                input = HttpOperations.getFileObjectFromWeb(csvFile);
            } else if (jenkinsReportLocation == JenkinsReportLocation.CLASSPATH) {
                input = this.getClass().getResourceAsStream(csvFile);
            } else if (jenkinsReportLocation == JenkinsReportLocation.LOCAL) {
                input = new File(csvFile);
            }

            if (input == null) {
                throw new FileNotFoundException();
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

            reader = new FileReader(output);
            JsonLoader jsonLoader = new JsonLoader();
            result = jsonLoader.getParsedData(reader);
        } catch (FileNotFoundException e) {
            throw new FileCannotBeFoundException(outputFileName, e);
        } catch (JsonGenerationException e) {
            throw new JsonGenerationFromCSVException(outputFileName, e, e.getProcessor());
        } catch (JsonMappingException e) {
            throw new JsonMappingFromCSVException(outputFileName, e, (JsonGenerator) e.getProcessor());
        } catch (IOException e) {
            throw new FileCannotBeReadException(outputFileName, e);
        } finally {
            if (reader != null) {
                reader.close();
            }
            Files.delete(Paths.get(outputFileName));
        }
        return result;
    }
}