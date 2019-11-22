package org.benchmarks.reports.data;

import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*Already implemented for Drools. If the others follow the buildtime/runtime json format
* it can be extended and implemented. Otherwise it can be removed and the methods moved*/
public abstract class ResultData {
    protected String buildtimePath;
    protected String runtimePath;
    protected Version version;
    protected FileLocation fileLocation;
    protected String buildTimeFileExtension;
    protected String runTimeFileExtension;

    private List<ResultRow> testResultData;

    public ResultData(Version version, FileLocation fileLocation) {
        this.version = version;
        this.fileLocation = fileLocation;
    }

    public List<ResultRow> getTestResultData() throws IOException, ParseException {
        this.testResultData = new ArrayList<>();
        this.testResultData.addAll(getDataFromBuildTime());
        this.testResultData.addAll(getDataFromRunTime());

        return testResultData;
    }

    protected abstract List<ResultRow> getDataFromBuildTime() throws IOException, ParseException;

    protected abstract List<ResultRow> getDataFromRunTime() throws IOException, ParseException;

}
