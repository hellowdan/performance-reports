package org.benchmarks.application;

import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.ProcessIteration;
import org.benchmarks.definitions.ProcessScope;
import org.benchmarks.documents.DroolsReport;

public class Launcher {

    public static void main(String[] args) throws Exception {

        ProcessScope processScope = null;

        if (args[0].equals(ProcessScope.FULL.getScope())) {
            processScope = ProcessScope.FULL;
        } else if (args[0].equals(ProcessScope.SPREADSHEETS_ONLY.getScope())) {
            processScope = ProcessScope.SPREADSHEETS_ONLY;
        } else {
            processScope = ProcessScope.FULL;
        }

        String propertiesFilePath = null;
        if(args[1].equals(ProcessIteration.GA.getIteration())){
            propertiesFilePath = "/drools-reports.properties";
        } else if(args[1].equals(ProcessIteration.WEEKLY.getIteration())){
            propertiesFilePath = "/drools-weekly-reports.properties";
        } else {
            propertiesFilePath = "/drools-reports.properties";
        }

        DroolsReport droolsReport = new DroolsReport(DroolsReportProperties.getInstance(propertiesFilePath), processScope);
        droolsReport.generateReport();
    }
}
