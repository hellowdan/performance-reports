package org.benchmarks.drools.data;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.benchmarks.commons.definitions.JenkinsReportFileExtension;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.definitions.JenkinsReportColumns;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.commons.util.PropertiesLoader;
import org.benchmarks.commons.data.JenkinsReport;
import org.benchmarks.commons.data.JenkinsReportRow;
import org.benchmarks.commons.util.CsvLoader;
import org.benchmarks.commons.util.JsonLoader;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;

public class DroolsBuildtimeJenkinsReport extends JenkinsReport {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsBuildtimeJenkinsReport.class);

    protected String buildtimePath;
    protected String buildtimeFileExtension;

    public DroolsBuildtimeJenkinsReport(JenkinsReportVersion jenkinsReportVersion, JenkinsReportLocation jenkinsReportLocation) {
        super(jenkinsReportVersion, jenkinsReportLocation);

        try {
            DroolsPropertiesLoader droolsPropertiesLoader = DroolsPropertiesLoader.getInstance();
            setDataSourcePath(droolsPropertiesLoader);
        } catch (IOException e) {
            LOGGER.debug("File cannot be read.", e);
        }
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

        if (testJenkinsReportRow.get(JenkinsReportColumns.benchmark.getColumn()) != null) {
            droolsResultRow.setName((String) testJenkinsReportRow.get(JenkinsReportColumns.benchmark.getColumn()));
        }
        if (testJenkinsReportRow.get(JenkinsReportColumns.numberOfRules.getColumn()) != null) {
            droolsResultRow.setNumberOfRules(testJenkinsReportRow.get(JenkinsReportColumns.numberOfRules.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(JenkinsReportColumns.nrOfRules.getColumn()) != null) {
            droolsResultRow.setNrOfRules(testJenkinsReportRow.get(JenkinsReportColumns.nrOfRules.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(JenkinsReportColumns.useCanonicalModel.getColumn()) != null) {
            droolsResultRow.setUseCanonicalModel(testJenkinsReportRow.get(JenkinsReportColumns.useCanonicalModel.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(JenkinsReportColumns.rulesProviderId.getColumn()) != null) {
            droolsResultRow.setRulesProviderId((String) testJenkinsReportRow.get(JenkinsReportColumns.rulesProviderId.getColumn()));
        }
        if (testJenkinsReportRow.get(JenkinsReportColumns.score.getColumn()) != null) {
            droolsResultRow.setScore(testJenkinsReportRow.get(JenkinsReportColumns.score.getColumn()).toString());
        }
        droolsResultRow.setHashCode();

        return droolsResultRow;
    }
}
