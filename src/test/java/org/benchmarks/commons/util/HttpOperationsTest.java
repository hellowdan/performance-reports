package org.benchmarks.commons.util;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HttpOperationsTest {

    @Test
    public void getFileObjectFromWebHTTPSTest() {
        String filePath = "https://rhba-qe-jenkins.rhev-ci-vms.eng.rdu2.redhat.com/job/BxMS/job/RHPAM-7.5-brew/job/performance/job/bre-perf/job/blessed-perf-turtle-buildtime-benchmarks/lastSuccessfulBuild/artifact/results.csv";
        Object input = HttpOperations.getFileObjectFromWeb(filePath);

        Assert.assertNotNull(input);

        //TO-DO need to publish a CSV file in a HTTP server (with HTTPS)
    }

    @Test
    public void getFileObjectFromWebHTTPTest() {
        String filePath = "http://rhba-qe-jenkins.rhev-ci-vms.eng.rdu2.redhat.com/job/BxMS/job/RHPAM-7.5-brew/job/performance/job/bre-perf/job/blessed-perf-turtle-buildtime-benchmarks/lastSuccessfulBuild/artifact/results.csv";
        Object input = HttpOperations.getFileObjectFromWeb(filePath);

        Assert.assertNotNull(input);

        //TO-DO need to publish a CSV file in a HTTP server
    }

    @Test
    public void getFileReaderFromWebTest() {
//        String filePath = "http://rhba-qe-jenkins.rhev-ci-vms.eng.rdu2.redhat.com/job/BxMS/job/RHPAM-7.5-brew/job/performance/job/bre-perf/job/blessed-perf-turtle-buildtime-benchmarks/lastSuccessfulBuild/artifact/results.json";
//        Reader input = HttpOperations.getFileReaderFromWeb(filePath);
//
//        Assert.assertNotNull(input);
//
//        TO-DO need to publish a JSON file in a HTTP server
    }
}