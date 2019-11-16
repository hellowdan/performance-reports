package org.benchmarks.drools.reports.data;

import org.benchmarks.reports.data.Version;
import org.benchmarks.reports.util.CsvLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

import org.benchmarks.reports.data.ResultData;
import org.benchmarks.reports.data.ResultRow;
import org.benchmarks.reports.util.JsonLoader;
import java.util.ArrayList;
import java.util.List;

public class DroolsResultData extends ResultData {

    public DroolsResultData(Version version) throws IOException {
        super(version);
        DroolsProperties reportProperties = DroolsProperties.getInstance();
        setDataSourcePaths(reportProperties);
    }

    /*for tests purposes*/
    public DroolsResultData(Version version, DroolsProperties reportProperties) {
        super(version);
        setDataSourcePaths(reportProperties);
    }

    private void setDataSourcePaths(DroolsProperties reportProperties){

        if (this.version == Version.NEW) {
            this.buildtimeJsonPath = reportProperties.getNewVersionBuildtimeJsonPath();
            this.runtimeJsonPath = reportProperties.getNewVersionRuntimeJsonPath();
            this.buildtimeCsvPath = reportProperties.getNewVersionBuildtimeCsvPath();
            this.runtimeCsvPath = reportProperties.getNewVersionRuntimeCsvPath();
        } else if (this.version == Version.PREVIOUS){
            this.buildtimeJsonPath = reportProperties.getPreviousVersionBuildtimeJsonPath();
            this.runtimeJsonPath = reportProperties.getPreviousVersionRuntimeJsonPath();
            this.buildtimeCsvPath = reportProperties.getPreviousVersionBuildtimeCsvPath();
            this.runtimeCsvPath = reportProperties.getPreviousVersionRuntimeCsvPath();
        } else if (this.version == Version.OLDER){
            this.buildtimeJsonPath = reportProperties.getOlderVersionBuildtimeJsonPath();
            this.runtimeJsonPath = reportProperties.getOlderVersionRuntimeJsonPath();
            this.buildtimeCsvPath = reportProperties.getOlderVersionBuildtimeCsvPath();
            this.runtimeCsvPath = reportProperties.getOlderVersionRuntimeCsvPath();
        }

        this.useCsv = reportProperties.getUseCsv();
    }

    @Override
    protected List<ResultRow> getDataFromBuildTime(){
        List<ResultRow> droolsTestResultData = new ArrayList<>();
        JSONArray dataJson;

        if(this.useCsv){
            dataJson = CsvLoader.getDataFromCSV(this.buildtimeCsvPath);}
        else{
            dataJson = JsonLoader.getDataFromJson(this.buildtimeJsonPath);}

        dataJson.forEach( testResultRow -> droolsTestResultData.add(parseBuildTimeTestResultRow( (JSONObject) testResultRow )) );

        return droolsTestResultData;
    }

    @Override
    protected List<ResultRow> getDataFromRunTime(){
        List<ResultRow> droolsTestResultData = new ArrayList<>();
        JSONArray dataJson;

        if(this.useCsv){
            dataJson = CsvLoader.getDataFromCSV(this.runtimeCsvPath);
        } else{
            dataJson = JsonLoader.getDataFromJson(this.runtimeJsonPath);
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
