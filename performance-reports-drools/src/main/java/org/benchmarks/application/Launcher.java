package org.benchmarks.application;

import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.documents.DroolsReport;

public class Launcher {

    public static void main(String[] args) throws Exception {

        DroolsReport droolsReport = new DroolsReport(DroolsReportProperties.getInstance());
        droolsReport.generateReport();
    }
}
