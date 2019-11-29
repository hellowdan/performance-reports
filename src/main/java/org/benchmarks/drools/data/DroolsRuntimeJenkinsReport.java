package org.benchmarks.drools.data;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.benchmarks.commons.definitions.JenkinsReportFileExtension;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.drools.definitions.DroolsReportColumns;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.commons.util.PropertiesLoader;
import org.benchmarks.commons.data.JenkinsReport;
import org.benchmarks.commons.data.JenkinsReportRow;
import org.benchmarks.commons.util.CsvLoader;
import org.benchmarks.commons.util.JsonLoader;

public class DroolsRuntimeJenkinsReport extends JenkinsReport {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsRuntimeJenkinsReport.class);

    protected String runtimePath;
    protected String runtimeFileExtension;

    public DroolsRuntimeJenkinsReport(JenkinsReportVersion jenkinsReportVersion, JenkinsReportLocation jenkinsReportLocation) {
        super(jenkinsReportVersion, jenkinsReportLocation);

        try {
            DroolsPropertiesLoader droolsPropertiesLoader = DroolsPropertiesLoader.getInstance();
            setDataSourcePath(droolsPropertiesLoader);
        } catch (IOException e) {
            LOGGER.debug("File cannot be read.", e);
        }
    }

    /*for tests purposes*/
    public DroolsRuntimeJenkinsReport(JenkinsReportVersion jenkinsReportVersion, JenkinsReportLocation jenkinsReportLocation, DroolsPropertiesLoader droolsPropertiesLoader) {
        super(jenkinsReportVersion, jenkinsReportLocation);
        setDataSourcePath(droolsPropertiesLoader);
    }

    @Override
    protected JenkinsReportRow parseJenkinsReportRow(JSONObject testJenkinsReportRow) {
        DroolsJenkinsReportRow droolsResultRow = new DroolsJenkinsReportRow();

        if (testJenkinsReportRow.get(DroolsReportColumns.benchmark.getColumn()) != null) {
            droolsResultRow.setName((String) testJenkinsReportRow.get(DroolsReportColumns.benchmark.getColumn()));
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.matchRatio.getColumn()) != null) {
            droolsResultRow.setMatchRatio(testJenkinsReportRow.get(DroolsReportColumns.matchRatio.getColumn()).toString());
        }
        if (testJenkinsReportRow.get(DroolsReportColumns.score.getColumn()) != null) {
            droolsResultRow.setScore(testJenkinsReportRow.get(DroolsReportColumns.score.getColumn()).toString());
        }
        droolsResultRow.setHashCode();

        return droolsResultRow;
    }

    @Override
    protected void setDataSourcePath(PropertiesLoader propertiesLoader) {
        DroolsPropertiesLoader droolsProperties = (DroolsPropertiesLoader) propertiesLoader;

        if (this.jenkinsReportVersion == JenkinsReportVersion.NEW) {
            this.runtimePath = droolsProperties.getNewVersionRuntimePath();
        } else if (this.jenkinsReportVersion == JenkinsReportVersion.PREVIOUS) {
            this.runtimePath = droolsProperties.getPreviousVersionRuntimePath();
        } else if (this.jenkinsReportVersion == JenkinsReportVersion.OLDER) {
            this.runtimePath = droolsProperties.getOlderVersionRuntimePath();
        }

        this.runtimeFileExtension = FilenameUtils.getExtension(this.runtimePath);
    }

    @Override
    public List<JenkinsReportRow> getData() {
        List<JenkinsReportRow> droolsTestResultData = new ArrayList<>();
        JSONArray dataJson;

        if (this.runtimeFileExtension.equals(JenkinsReportFileExtension.CSV.getExtension())) {
            CsvLoader csvLoader = new CsvLoader();
            dataJson = csvLoader.getDataFromCSV(this.runtimePath, this.jenkinsReportLocation);
        } else if (this.runtimeFileExtension.equals(JenkinsReportFileExtension.JSON.getExtension())) {
            JsonLoader jsonLoader = new JsonLoader();
            dataJson = jsonLoader.getDataFromJson(this.runtimePath, this.jenkinsReportLocation);
        } else {
            throw new InvalidPathException("Invalid file extension: ", this.runtimePath + " - " + this.runtimeFileExtension);
        }

        dataJson.forEach(testResultRow -> droolsTestResultData.add(parseJenkinsReportRow((JSONObject) testResultRow)));

        return droolsTestResultData;
    }
}
