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

public class DroolsRuntimeData extends ResultData {

    protected String runtimePath;
    protected String runtimeFileExtension;


    public DroolsRuntimeData(Version version, FileLocation fileLocation) throws IOException {
        super(version, fileLocation);
        DroolsProperties reportProperties = DroolsProperties.getInstance();
        setDataSourcePath(reportProperties);
    }

    /*for tests purposes*/
    public DroolsRuntimeData(Version version, FileLocation fileLocation, DroolsProperties reportProperties) {
        super(version, fileLocation);
        setDataSourcePath(reportProperties);
    }

    @Override
    protected ResultRow parseTestResultRow(JSONObject testResultRow) {
        DroolsResultRow droolsResultRow = new DroolsResultRow();

        if (testResultRow.get(ReportColumns.benchmark.getColumn()) != null) {
            droolsResultRow.setName((String) testResultRow.get(ReportColumns.benchmark.getColumn()));
        }
        if (testResultRow.get(ReportColumns.matchRatio.getColumn()) != null) {
            droolsResultRow.setMatchRatio(testResultRow.get(ReportColumns.matchRatio.getColumn()).toString());
        }
        if (testResultRow.get(ReportColumns.score.getColumn()) != null) {
            droolsResultRow.setScore(testResultRow.get(ReportColumns.score.getColumn()).toString());
        }
        droolsResultRow.setHashCode();

        return droolsResultRow;
    }

    @Override
    protected void setDataSourcePath(ReportProperties reportProperties) {
        DroolsProperties droolsProperties = (DroolsProperties) reportProperties;

        if (this.version == Version.NEW) {
            this.runtimePath = droolsProperties.getNewVersionRuntimePath();
        } else if (this.version == Version.PREVIOUS) {
            this.runtimePath = droolsProperties.getPreviousVersionRuntimePath();
        } else if (this.version == Version.OLDER) {
            this.runtimePath = droolsProperties.getOlderVersionRuntimePath();
        }

        this.runtimeFileExtension = FilenameUtils.getExtension(this.runtimePath);
    }

    @Override
    public List<ResultRow> getData() {
        List<ResultRow> droolsTestResultData = new ArrayList<>();
        JSONArray dataJson;

        if (this.runtimeFileExtension.equals(FileExtension.CSV.getExtension())) {
            CsvLoader csvLoader = new CsvLoader();
            dataJson = csvLoader.getDataFromCSV(this.runtimePath, this.fileLocation);
        } else if (this.runtimeFileExtension.equals(FileExtension.JSON.getExtension())) {
            JsonLoader jsonLoader = new JsonLoader();
            dataJson = jsonLoader.getDataFromJson(this.runtimePath, this.fileLocation);
        } else {
            throw new InvalidPathException("Invalid file extension: ", this.runtimePath + " - " + this.runtimeFileExtension);
        }

        dataJson.forEach(testResultRow -> droolsTestResultData.add(parseTestResultRow((JSONObject) testResultRow)));

        return droolsTestResultData;
    }
}
