package org.benchmarks.reports.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ReportProperties {

    protected Properties properties = null;
    protected String fileName;

    public ReportProperties(String fileName) throws IOException {
        InputStream inputStream = null;
        this.fileName = fileName;
        this.properties = new Properties();

        try {
            inputStream = new FileInputStream(this.fileName);

            if (inputStream != null) {
                this.properties.load(inputStream);
            } else throw new FileNotFoundException("property file '" + this.fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }

    public Properties getProperties() {
        return properties;
    }

}
