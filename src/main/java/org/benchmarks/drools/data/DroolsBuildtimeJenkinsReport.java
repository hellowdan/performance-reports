package org.benchmarks.drools.data;

import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.benchmarks.commons.data.JenkinsReport;
import org.benchmarks.commons.data.JenkinsReportRow;
import org.benchmarks.commons.definitions.JenkinsReportFileExtension;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.commons.util.CsvLoader;
import org.benchmarks.commons.util.JsonLoader;
import org.benchmarks.commons.util.PropertiesLoader;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.benchmarks.drools.definitions.DroolsReportColumns;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DroolsBuildtimeJenkinsReport extends JenkinsReport {

    protected String buildtimePath;
    protected String buildtimeFileExtension;

    public DroolsBuildtimeJenkinsReport(JenkinsReportVersion jenkinsReportVersion, JenkinsReportLocation jenkinsReportLocation) {
        super(jenkinsReportVersion, jenkinsReportLocation);
        DroolsPropertiesLoader droolsPropertiesLoader = DroolsPropertiesLoader.getInstance();
        setDataSourcePath(droolsPropertiesLoader);
    }

    /*for tests purposes*/
    public DroolsBuildtimeJenkinsReport(JenkinsReportVersion jenkinsReportVersion, JenkinsReportLocation jenkinsReportLocation, DroolsPropertiesLoader droolsPropertiesLoader) {
        super(jenkinsReportVersion, jenkinsReportLocation);
        setDataSourcePath(droolsPropertiesLoader);
    }

    @Override
    public List<JenkinsReportRow> getData() {
        List<JenkinsReportRow> droolsTestResultData = new ArrayList<>();
        JSONArray dataJson;

        if (this.buildtimeFileExtension.equals(JenkinsReportFileExtension.CSV.getExtension())) {
            CsvLoader csvLoader = new CsvLoader();
            dataJson = csvLoader.getDataFromCSV(this.buildtimePath, this.jenkinsReportLocation);
        } else if (this.buildtimeFileExtension.equals(JenkinsReportFileExtension.JSON.getExtension())) {
            JsonLoader jsonLoader = new JsonLoader();
            dataJson = jsonLoader.getDataFromJson(this.buildtimePath, this.jenkinsReportLocation);
        } else {
            throw new InvalidPathException("Invalid file extension: ", this.buildtimePath + " - " + this.buildtimeFileExtension);
        }

        dataJson.forEach(testResultRow -> droolsTestResultData.add(parseJenkinsReportRow((JSONObject) testResultRow)));

        return droolsTestResultData;
    }

    @Override
    protected void setDataSourcePath(PropertiesLoader propertiesLoader) {
        DroolsPropertiesLoader droolsProperties = (DroolsPropertiesLoader) propertiesLoader;

        if (this.jenkinsReportVersion == JenkinsReportVersion.NEW) {
            this.buildtimePath = droolsProperties.getNewVersionBuildtimePath();
        } else if (this.jenkinsReportVersion == JenkinsReportVersion.PREVIOUS) {
            this.buildtimePath = droolsProperties.getPreviousVersionBuildtimePath();
        } else if (this.jenkinsReportVersion == JenkinsReportVersion.OLDER) {
            this.buildtimePath = droolsProperties.getOlderVersionBuildtimePath();
        }

        this.buildtimeFileExtension = FilenameUtils.getExtension(this.buildtimePath);
    }

    @Override
    protected JenkinsReportRow parseJenkinsReportRow(JSONObject testJenkinsReportRow) {
        DroolsJenkinsReportRow droolsResultRow = new DroolsJenkinsReportRow();

        if (testJenkinsReportRow.get(DroolsReportColumns.benchmark.getColumn()) != null) {
            droolsResultRow.setName((String) testJenkinsReportRow.get(DroolsReportColumns.benchmark.getColumn()));
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.numberOfRules.getColumn()) != null) {
            droolsResultRow.setNumberOfRules(testJenkinsReportRow.get(DroolsReportColumns.numberOfRules.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.nrOfRules.getColumn()) != null) {
            droolsResultRow.setNrOfRules(testJenkinsReportRow.get(DroolsReportColumns.nrOfRules.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.useCanonicalModel.getColumn()) != null) {
            droolsResultRow.setUseCanonicalModel(testJenkinsReportRow.get(DroolsReportColumns.useCanonicalModel.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.rulesProviderId.getColumn()) != null) {
            droolsResultRow.setRulesProviderId((String) testJenkinsReportRow.get(DroolsReportColumns.rulesProviderId.getColumn()));
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.score.getColumn()) != null) {
            droolsResultRow.setScore(testJenkinsReportRow.get(DroolsReportColumns.score.getColumn()).toString());
        }
        droolsResultRow.setHashCode();

        return droolsResultRow;
    }
}
