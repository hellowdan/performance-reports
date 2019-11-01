package org.benchmarks.drools.reports.builder;

import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Launcher {

    public static void main(String[] args) throws IOException, GeneralSecurityException, ParseException {

        DroolsReport droolsReport = new DroolsReport();
        droolsReport.generateReport();

    }
}
