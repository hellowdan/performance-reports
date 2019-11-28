package org.benchmarks.reports.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ReportProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportProperties.class);

    protected Properties properties = null;
    protected String fileName;

    public ReportProperties(String fileName) throws IOException {
        InputStream inputStream = null;
        this.fileName = fileName;
        this.properties = new Properties();

        try {
            inputStream = this.getClass().getResourceAsStream(this.fileName);

            if (inputStream != null) {
                this.properties.load(inputStream);
            } else {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            LOGGER.debug("Properties file not found: " + fileName, e);
        } finally {
            inputStream.close();
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
