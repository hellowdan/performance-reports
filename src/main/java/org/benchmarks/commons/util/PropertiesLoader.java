package org.benchmarks.commons.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PropertiesLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesLoader.class);

    protected Properties properties = null;
    protected String fileName;

    public PropertiesLoader(String fileName) {
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
        } catch (IOException e) {
            LOGGER.debug("Properties file not found: " + fileName, e);
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
