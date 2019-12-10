package org.benchmarks.drools.documents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.benchmarks.commons.definitions.JenkinsReportFileExtension;
import org.benchmarks.commons.util.PropertiesLoader;
import org.benchmarks.drools.data.DroolsBuildtimeJenkinsReport;
import org.benchmarks.drools.data.DroolsRuntimeJenkinsReport;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;
import org.benchmarks.drools.definitions.DroolsSheetPositions;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.benchmarks.commons.api.helper.GoogleDriveSpreadSheet;
import org.benchmarks.commons.definitions.JenkinsReportLocation;
import org.benchmarks.commons.data.JenkinsReportRow;

public class DroolsGoogleDriveSpreadSheet extends GoogleDriveSpreadSheet {


    static String currentVersion = "{{current_version}}";
    static String previousVersion = "{{previous_version}}";
    static String olderVersion = "{{older_version}}";

    public DroolsGoogleDriveSpreadSheet() {
        super();
    }

    @Override
    protected List<Request> getUpdateInfoRequestsBody(PropertiesLoader propertiesLoader) {
        DroolsPropertiesLoader droolsPropertiesLoader = (DroolsPropertiesLoader) propertiesLoader;

        List<Request> requests = new ArrayList<>();

        requests.add(getReplaceSpreadSheetBodyRequest(currentVersion, droolsPropertiesLoader.getCurrentVersion()));
        requests.add(getReplaceSpreadSheetBodyRequest(previousVersion, droolsPropertiesLoader.getPreviousVersion()));
        requests.add(getReplaceSpreadSheetBodyRequest(olderVersion, droolsPropertiesLoader.getOlderVersion()));

        return requests;
    }

    @Override
    protected ValueRange getUpdateDataRequestsBody(JenkinsReportFileExtension jenkinsReportFileExtension, JenkinsReportVersion jenkinsReportVersion, JenkinsReportLocation jenkinsReportLocation, PropertiesLoader propertiesLoader) throws IOException {
        DroolsPropertiesLoader droolsPropertiesLoader = (DroolsPropertiesLoader) propertiesLoader;

        ValueRange body = new ValueRange();

        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport();
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport();

        String buildtimePath = droolsBuildtimeJenkinsReport.getDataSourcePath(jenkinsReportVersion, droolsPropertiesLoader);
        String runtimePath = droolsRuntimeJenkinsReport.getDataSourcePath(jenkinsReportVersion, droolsPropertiesLoader);

        List values = new ArrayList();
        List<JenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(buildtimePath, jenkinsReportFileExtension, jenkinsReportLocation);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(runtimePath, jenkinsReportFileExtension, jenkinsReportLocation));

        for (Integer key : DroolsSheetPositions.getPositions().keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getHashCode() == DroolsSheetPositions.getPositions().get(key)) {
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
