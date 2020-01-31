package org.benchmarks.documents;

import org.benchmarks.definitions.DroolsReportProperties;

public class Launcher {

    public static void main(String[] args) throws Exception {

        DroolsReport droolsReport = new DroolsReport(DroolsReportProperties.getInstance());
        droolsReport.generateReport();
    }
}
