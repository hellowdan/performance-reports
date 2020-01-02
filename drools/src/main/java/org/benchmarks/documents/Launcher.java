package org.benchmarks.documents;

import org.benchmarks.definitions.DroolsPropertiesLoader;

public class Launcher {

    public static void main(String[] args) throws Exception {

        DroolsReport droolsReport = new DroolsReport(DroolsPropertiesLoader.getInstance());
        droolsReport.generateReport();
    }
}
