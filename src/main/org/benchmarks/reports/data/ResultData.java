package org.benchmarks.reports.data;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*Already implemented for Drools. If the others follow the buildtime/runtime json format
 * it can be extended and implemented. Otherwise it can be removed and the methods moved*/
public abstract class ResultData {

    protected Version version;
    protected FileLocation fileLocation;

    public ResultData(Version version, FileLocation fileLocation) {
        this.version = version;
        this.fileLocation = fileLocation;
    }

    public abstract List<ResultRow> getData();

    protected abstract void setDataSourcePath(ReportProperties reportProperties);

    protected abstract ResultRow parseTestResultRow(JSONObject testResultRow);
}
