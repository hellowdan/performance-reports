package org.benchmarks.drools.reports.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.benchmarks.drools.reports.data.DroolsBuildtimeData;
import org.benchmarks.drools.reports.data.DroolsProperties;
import org.benchmarks.drools.reports.data.DroolsRuntimeData;
import org.benchmarks.drools.reports.definitions.DroolsSheetPositions;
import org.benchmarks.reports.builder.GoogleDriveSpreadSheet;
import org.benchmarks.reports.data.FileLocation;
import org.benchmarks.reports.data.ResultRow;
import org.benchmarks.reports.data.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolsGoogleDriveSpreadSheet extends GoogleDriveSpreadSheet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsGoogleDriveSpreadSheet.class);

    static String newVersion = "{{new_version}}";
    static String previousVersion = "{{previous_version}}";
    static String olderVersion = "{{older_version}}";
    private DroolsProperties reportProperties;

    public DroolsGoogleDriveSpreadSheet(String spreadSheetNewId, DroolsProperties reportProperties, Sheets sheetService) {
        super(spreadSheetNewId, sheetService);

        this.reportProperties = reportProperties;
    }

    @Override
    protected List<Request> getUpdateInfoRequestsBody() {
        List<Request> requests = new ArrayList<>();

        requests.add(getReplaceSpreadSheetBodyRequest(newVersion, this.reportProperties.getNewVersion()));
        requests.add(getReplaceSpreadSheetBodyRequest(previousVersion, this.reportProperties.getPreviousVersion()));
        requests.add(getReplaceSpreadSheetBodyRequest(olderVersion, this.reportProperties.getOlderVersion()));

        return requests;
    }

    @Override
    protected ValueRange getUpdateDataRequestsBody(Version version, FileLocation fileLocation) {
        ValueRange body = new ValueRange();

        DroolsBuildtimeData droolsBuildtimeData = new DroolsBuildtimeData(version, fileLocation);
        DroolsRuntimeData droolsRuntimeData = new DroolsRuntimeData(version, fileLocation);

        List values = new ArrayList();
        List<ResultRow> testResultData = null;
        testResultData.addAll(droolsBuildtimeData.getData());
        testResultData.addAll(droolsRuntimeData.getData());

        for (Integer key : DroolsSheetPositions.droolsSheetPositions.keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getHashCode() == DroolsSheetPositions.droolsSheetPositions.get(key)) {
                    String score = testResultData.get(i).getScore();
                    values.add(Arrays.asList(score));
                    break;
                }
            }
        }

        body.setValues(values);
        return body;
    }
}
