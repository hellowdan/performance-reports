package org.benchmarks.util;

import java.io.IOException;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReportPropertiesTest {

    @Test
    public void getPropertiesTest() throws IOException {
        ReportProperties reportProperties;
        reportProperties = new ReportProperties("/propertiesLoaderTest.properties");

        assertThat(reportProperties.getFilesTitle(), is("Properties file loaded successfully!"));
    }
}