package org.benchmarks.commons.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PropertiesLoaderTest {

    @Test
    public void getPropertiesTest() {
        PropertiesLoader propertiesLoader;
        propertiesLoader = new PropertiesLoader("/propertiesLoaderTest.properties");

        assertThat(propertiesLoader.getFilesTitle(), is("Properties file loaded successfully!"));
    }
}