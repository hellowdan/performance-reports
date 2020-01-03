package org.benchmarks.documents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.benchmarks.data.DroolsBuildtimeJenkinsReport;
import org.benchmarks.data.DroolsRuntimeJenkinsReport;
import org.benchmarks.data.JenkinsReportRow;
import org.benchmarks.definitions.DroolsPropertiesLoader;
import org.benchmarks.definitions.DroolsSheetPositions;
import org.benchmarks.definitions.JenkinsReportFileExtension;
import org.benchmarks.definitions.JenkinsReportLocation;
import org.benchmarks.definitions.JenkinsReportVersion;
import org.benchmarks.helper.GoogleDriveSpreadSheet;
import org.benchmarks.util.PropertiesLoader;

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
                if (testResultData.get(i).getUniqueID().equals(DroolsSheetPositions.getPositions().get(key))) {
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
