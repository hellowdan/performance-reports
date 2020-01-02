package org.benchmarks.drools.definitions;

import java.io.IOException;

import org.benchmarks.commons.util.PropertiesLoader;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DroolsPropertiesLoaderTest {

    @Test
    public void getPropertiesTest() throws IOException {
        PropertiesLoader propertiesLoader;
        propertiesLoader = new PropertiesLoader("/droolsReportTest.properties");

        assertThat(propertiesLoader.getFilesTitle(), is("Red Hat Business Automation Decision Engine 7.5.0 Performance Report"));
    }
}