package org.benchmarks.commons.data;

import java.util.List;
import org.json.simple.JSONObject;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.commons.util.PropertiesLoader;

/*Already implemented for Drools. If the others follow the buildtime/runtime json format
 * it can be extended and implemented. Otherwise it can be removed and the methods moved*/
public abstract class JenkinsReport {

    protected JenkinsReportVersion jenkinsReportVersion;
    protected JenkinsReportLocation jenkinsReportLocation;

    public JenkinsReport(JenkinsReportVersion jenkinsReportVersion, JenkinsReportLocation jenkinsReportLocation) {
        this.jenkinsReportVersion = jenkinsReportVersion;
        this.jenkinsReportLocation = jenkinsReportLocation;
    }

    public abstract List<JenkinsReportRow> getData();

    protected abstract void setDataSourcePath(PropertiesLoader propertiesLoader);

    protected abstract JenkinsReportRow parseJenkinsReportRow(JSONObject testJenkinsReportRow);
}
