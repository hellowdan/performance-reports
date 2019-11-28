package org.benchmarks.drools.reports.data;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.benchmarks.reports.data.FileExtension;
import org.benchmarks.reports.data.FileLocation;
import org.benchmarks.reports.data.ReportColumns;
import org.benchmarks.reports.data.ReportProperties;
import org.benchmarks.reports.data.ResultData;
import org.benchmarks.reports.data.ResultRow;
import org.benchmarks.reports.data.Version;
import org.benchmarks.reports.util.CsvLoader;
import org.benchmarks.reports.util.JsonLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DroolsBuildtimeData extends ResultData {

    protected String buildtimePath;
    protected String buildtimeFileExtension;

    public DroolsBuildtimeData(Version version, FileLocation fileLocation) throws IOException {
        super(version, fileLocation);
        DroolsProperties reportProperties = DroolsProperties.getInstance();
        setDataSourcePath(reportProperties);
    }

    /*for tests purposes*/
    public DroolsBuildtimeData(Version version, FileLocation fileLocation, DroolsProperties reportProperties) {
        super(version, fileLocation);
        setDataSourcePath(reportProperties);
    }

    @Override
    public List<ResultRow> getData() {
        List<ResultRow> droolsTestResultData = new ArrayList<>();
        JSONArray dataJson;

        if (this.buildtimeFileExtension.equals(FileExtension.CSV.getExtension())) {
            CsvLoader csvLoader = new CsvLoader();
            dataJson = csvLoader.getDataFromCSV(this.buildtimePath, this.fileLocation);
        } else if (this.buildtimeFileExtension.equals(FileExtension.JSON.getExtension())) {
            JsonLoader jsonLoader = new JsonLoader();
            dataJson = jsonLoader.getDataFromJson(this.buildtimePath, this.fileLocation);
        } else {
            throw new InvalidPathException("Invalid file extension: ", this.buildtimePath + " - " + this.buildtimeFileExtension);
        }

        dataJson.forEach(testResultRow -> droolsTestResultData.add(parseTestResultRow((JSONObject) testResultRow)));

        return droolsTestResultData;
    }

    @Override
    protected void setDataSourcePath(ReportProperties reportProperties) {
        DroolsProperties droolsProperties = (DroolsProperties) reportProperties;

        if (this.version == Version.NEW) {
            this.buildtimePath = droolsProperties.getNewVersionBuildtimePath();
        } else if (this.version == Version.PREVIOUS) {
            this.buildtimePath = droolsProperties.getPreviousVersionBuildtimePath();
        } else if (this.version == Version.OLDER) {
            this.buildtimePath = droolsProperties.getOlderVersionBuildtimePath();
        }

        this.buildtimeFileExtension = FilenameUtils.getExtension(this.buildtimePath);
    }

    @Override
    protected ResultRow parseTestResultRow(JSONObject testResultRow) {
        DroolsResultRow droolsResultRow = new DroolsResultRow();

        if (testResultRow.get(ReportColumns.benchmark.getColumn()) != null) {
            droolsResultRow.setName((String) testResultRow.get(ReportColumns.benchmark.getColumn()));
        }
        if (testResultRow.get(ReportColumns.numberOfRules.getColumn()) != null) {
            droolsResultRow.setNumberOfRules(testResultRow.get(ReportColumns.numberOfRules.getColumn()).toString());
        }
        if (testResultRow.get(ReportColumns.nrOfRules.getColumn()) != null) {
            droolsResultRow.setNrOfRules(testResultRow.get(ReportColumns.nrOfRules.getColumn()).toString());
        }
        if (testResultRow.get(ReportColumns.useCanonicalModel.getColumn()) != null) {
            droolsResultRow.setUseCanonicalModel(testResultRow.get(ReportColumns.useCanonicalModel.getColumn()).toString());
        }
        if (testResultRow.get(ReportColumns.rulesProviderId.getColumn()) != null) {
            droolsResultRow.setRulesProviderId((String) testResultRow.get(ReportColumns.rulesProviderId.getColumn()));
        }
        if (testResultRow.get(ReportColumns.score.getColumn()) != null) {
            droolsResultRow.setScore(testResultRow.get(ReportColumns.score.getColumn()).toString());
        }
        droolsResultRow.setHashCode();

        return droolsResultRow;
    }
}
