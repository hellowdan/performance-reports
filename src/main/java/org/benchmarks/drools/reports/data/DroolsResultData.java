package org.benchmarks.drools.reports.data;

import org.apache.commons.io.FilenameUtils;
import org.benchmarks.reports.data.FileLocation;
import org.benchmarks.reports.data.Version;
import org.benchmarks.reports.util.CsvLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

import org.benchmarks.reports.data.ResultData;
import org.benchmarks.reports.data.ResultRow;
import org.benchmarks.reports.util.JsonLoader;

import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

public class DroolsResultData extends ResultData {

    public DroolsResultData(Version version, FileLocation fileLocation) throws IOException {
        super(version, fileLocation);
        DroolsProperties reportProperties = DroolsProperties.getInstance();
        setDataSourcePaths(reportProperties);
    }

    /*for tests purposes*/
    public DroolsResultData(Version version, FileLocation fileLocation, DroolsProperties reportProperties) {
        super(version, fileLocation);
        setDataSourcePaths(reportProperties);
    }

    private void setDataSourcePaths(DroolsProperties reportProperties){

        if (this.version == Version.NEW) {
            this.buildtimePath = reportProperties.getNewVersionBuildtimePath();
            this.runtimePath = reportProperties.getNewVersionRuntimePath();
        } else if (this.version == Version.PREVIOUS){
            this.buildtimePath = reportProperties.getPreviousVersionBuildtimePath();
            this.runtimePath = reportProperties.getPreviousVersionRuntimePath();
        } else if (this.version == Version.OLDER){
            this.buildtimePath = reportProperties.getOlderVersionBuildtimePath();
            this.runtimePath = reportProperties.getOlderVersionRuntimePath();
        }

        this.buildTimeFileExtension = FilenameUtils.getExtension(this.buildtimePath);
        this.runTimeFileExtension = FilenameUtils.getExtension(this.runtimePath);
    }

    @Override
    protected List<ResultRow> getDataFromBuildTime(){
        List<ResultRow> droolsTestResultData = new ArrayList<>();
        JSONArray dataJson;

        if(this.buildTimeFileExtension.equals("csv")){
            dataJson = CsvLoader.getDataFromCSV(this.buildtimePath, this.fileLocation);}
        else if(this.buildTimeFileExtension.equals("json")){
            dataJson = JsonLoader.getDataFromJson(this.buildtimePath, this.fileLocation);}
        else {
            throw new InvalidPathException("Invalid file extension: ", this.buildTimeFileExtension);
        }

        dataJson.forEach( testResultRow -> droolsTestResultData.add(parseBuildTimeTestResultRow( (JSONObject) testResultRow )) );

        return droolsTestResultData;
    }

    @Override
    protected List<ResultRow> getDataFromRunTime(){
        List<ResultRow> droolsTestResultData = new ArrayList<>();
        JSONArray dataJson;

        if(this.runTimeFileExtension.equals("csv")){
            dataJson = CsvLoader.getDataFromCSV(this.runtimePath, this.fileLocation);}
        else if(this.runTimeFileExtension.equals("json")){
            dataJson = JsonLoader.getDataFromJson(this.runtimePath, this.fileLocation);}
        else {
            throw new InvalidPathException("Invalid file extension: ", this.buildTimeFileExtension);
        }

        dataJson.forEach( testResultRow -> droolsTestResultData.add(parseRunTimeTestResultRow( (JSONObject) testResultRow )) );

        return droolsTestResultData;
    }

    private static DroolsResultRow parseRunTimeTestResultRow(JSONObject testResultRow)
    {
        DroolsResultRow droolsResultRow = new DroolsResultRow();

        if(testResultRow.get("Benchmark") != null) droolsResultRow.setName((String) testResultRow.get("Benchmark"));
        if(testResultRow.get("Param: matchRatio") != null) droolsResultRow.setMatchRatio(testResultRow.get("Param: matchRatio").toString());
        if(testResultRow.get("Score") != null) droolsResultRow.setScore(testResultRow.get("Score").toString());
        droolsResultRow.setHashCode();

        return droolsResultRow;
    }

    private static DroolsResultRow parseBuildTimeTestResultRow(JSONObject testResultRow)
    {
        DroolsResultRow droolsResultRow = new DroolsResultRow();

        if(testResultRow.get("Benchmark") != null) droolsResultRow.setName((String) testResultRow.get("Benchmark"));
        if(testResultRow.get("Param: numberOfRules") != null) droolsResultRow.setNumberOfRules(testResultRow.get("Param: numberOfRules").toString());
        if(testResultRow.get("Param: nrOfRules") != null) droolsResultRow.setNrOfRules(testResultRow.get("Param: nrOfRules").toString());
        if(testResultRow.get("Param: useCanonicalModel") != null) droolsResultRow.setUseCanonicalModel( testResultRow.get("Param: useCanonicalModel").toString());
        if(testResultRow.get("Param: rulesProviderId") != null) droolsResultRow.setRulesProviderId((String) testResultRow.get("Param: rulesProviderId"));
        if(testResultRow.get("Score") != null) droolsResultRow.setScore(testResultRow.get("Score").toString());
        droolsResultRow.setHashCode();

        return droolsResultRow;
    }
}
