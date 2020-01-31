package org.benchmarks.definitions;

import java.io.IOException;

import org.benchmarks.util.ReportProperties;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DroolsReportPropertiesTest {

    @Test
    public void getPropertiesTest() throws IOException {
        ReportProperties reportProperties;
        reportProperties = new ReportProperties("/droolsReportTest.properties");

        assertThat(reportProperties.getFilesTitle(), is("Red Hat Business Automation Decision Engine 7.5.0 Performance Report"));
    }
}