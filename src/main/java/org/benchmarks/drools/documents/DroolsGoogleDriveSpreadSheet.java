package org.benchmarks.drools.documents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.benchmarks.drools.data.DroolsBuildtimeJenkinsReport;
import org.benchmarks.drools.data.DroolsRuntimeJenkinsReport;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.benchmarks.drools.definitions.DroolsSheetPositions;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.commons.api.helper.GoogleDriveSpreadSheet;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.data.JenkinsReportRow;

public class DroolsGoogleDriveSpreadSheet extends GoogleDriveSpreadSheet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DroolsGoogleDriveSpreadSheet.class);

    static String newVersion = "{{new_version}}";
    static String previousVersion = "{{previous_version}}";
    static String olderVersion = "{{older_version}}";
    private DroolsPropertiesLoader droolsPropertiesLoader;

    public DroolsGoogleDriveSpreadSheet(String spreadSheetNewId, DroolsPropertiesLoader droolsPropertiesLoader, Sheets sheetService) {
        super(spreadSheetNewId, sheetService);

        this.droolsPropertiesLoader = droolsPropertiesLoader;
    }

    @Override
    protected List<Request> getUpdateInfoRequestsBody() {
        List<Request> requests = new ArrayList<>();

        requests.add(getReplaceSpreadSheetBodyRequest(newVersion, this.droolsPropertiesLoader.getNewVersion()));
        requests.add(getReplaceSpreadSheetBodyRequest(previousVersion, this.droolsPropertiesLoader.getPreviousVersion()));
        requests.add(getReplaceSpreadSheetBodyRequest(olderVersion, this.droolsPropertiesLoader.getOlderVersion()));

        return requests;
    }

    @Override
    protected ValueRange getUpdateDataRequestsBody(JenkinsReportVersion jenkinsReportVersion, JenkinsReportLocation jenkinsReportLocation) {
        ValueRange body = new ValueRange();

        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport(jenkinsReportVersion, jenkinsReportLocation);
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport(jenkinsReportVersion, jenkinsReportLocation);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData();
        testResultData.addAll(droolsRuntimeJenkinsReport.getData());

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
