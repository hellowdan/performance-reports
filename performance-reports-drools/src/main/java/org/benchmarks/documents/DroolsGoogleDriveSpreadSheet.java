package org.benchmarks.documents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.benchmarks.data.BuildtimeReportData;
import org.benchmarks.data.RuntimeReportData;
import org.benchmarks.data.ReportRow;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.SheetPositions;
import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.helper.GoogleDriveSpreadSheet;
import org.benchmarks.util.ReportProperties;

public class DroolsGoogleDriveSpreadSheet extends GoogleDriveSpreadSheet {


    static String currentVersion = "{{current_version}}";
    static String previousVersion = "{{previous_version}}";
    static String olderVersion = "{{older_version}}";

    public DroolsGoogleDriveSpreadSheet() {
        super();
    }

    @Override
    protected List<Request> getUpdateInfoRequestsBody(ReportProperties reportProperties) {
        DroolsReportProperties droolsReportProperties = (DroolsReportProperties) reportProperties;

        List<Request> requests = new ArrayList<>();

        requests.add(getReplaceSpreadSheetBodyRequest(currentVersion, droolsReportProperties.getCurrentVersion()));
        requests.add(getReplaceSpreadSheetBodyRequest(previousVersion, droolsReportProperties.getPreviousVersion()));
        requests.add(getReplaceSpreadSheetBodyRequest(olderVersion, droolsReportProperties.getOlderVersion()));

        return requests;
    }

    @Override
    protected ValueRange getUpdateDataRequestsBody(SourceFileExtension sourceFileExtension, StaticVersion staticVersion, SourceFileLocation sourceFileLocation, ReportProperties reportProperties) throws IOException {
        DroolsReportProperties droolsReportProperties = (DroolsReportProperties) reportProperties;

        ValueRange body = new ValueRange();

        BuildtimeReportData droolsBuildtimeJenkinsReport = new BuildtimeReportData();
        RuntimeReportData droolsRuntimeJenkinsReport = new RuntimeReportData();

        String buildtimePath = droolsBuildtimeJenkinsReport.getDataSourcePath(staticVersion, droolsReportProperties);
        String runtimePath = droolsRuntimeJenkinsReport.getDataSourcePath(staticVersion, droolsReportProperties);

        List values = new ArrayList();
        List<ReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(buildtimePath, sourceFileExtension, sourceFileLocation);
        testResultData.addAll(droolsRuntimeJenkinsReport.getData(runtimePath, sourceFileExtension, sourceFileLocation));

        for (Integer key : SheetPositions.getPositions().keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(SheetPositions.getPositions().get(key))) {
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
