package org.benchmarks.documents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.benchmarks.data.ReportData;
import org.benchmarks.data.ReportRow;
import org.benchmarks.data.cep.CEPReportData;
import org.benchmarks.data.dmn.DMNReportData;
import org.benchmarks.data.oopath.OopathReportData;
import org.benchmarks.data.operators.OperatorsReportData;
import org.benchmarks.data.session.SessionReportData;
import org.benchmarks.data.turtle.buildtime.BuildtimeReportData;
import org.benchmarks.data.turtle.runtime.RuntimeReportData;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.ReportSheetPositions;
import org.benchmarks.definitions.ReportType;
import org.benchmarks.definitions.RowDataToMap;
import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.definitions.cep.CEPMultithreadedSheetPositions;
import org.benchmarks.definitions.cep.CEPSheetPositions;
import org.benchmarks.definitions.dmn.DMNSheetPositions;
import org.benchmarks.definitions.oopath.OopathSheetPositions;
import org.benchmarks.definitions.operators.OperatorsSheetPositions;
import org.benchmarks.definitions.session.SessionSheetPositions;
import org.benchmarks.definitions.turtle.buildtime.BuildtimeSheetPositions;
import org.benchmarks.definitions.turtle.runtime.RuntimeMultithreadedSheetPositions;
import org.benchmarks.definitions.turtle.runtime.RuntimeSheetPositions;
import org.benchmarks.helper.GoogleDriveSpreadSheet;
import org.benchmarks.util.ReportProperties;

public class DroolsGoogleDriveSpreadSheet extends GoogleDriveSpreadSheet {

    static String nextVersion = "{{next_version}}";
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

        requests.add(getReplaceSpreadSheetBodyRequest(nextVersion, droolsReportProperties.getNextVersion()));
        requests.add(getReplaceSpreadSheetBodyRequest(currentVersion, droolsReportProperties.getCurrentVersion()));
        requests.add(getReplaceSpreadSheetBodyRequest(previousVersion, droolsReportProperties.getPreviousVersion()));
        requests.add(getReplaceSpreadSheetBodyRequest(olderVersion, droolsReportProperties.getOlderVersion()));

        return requests;
    }

    @Override
    protected ValueRange getUpdateDataRequestsBody(SourceFileExtension sourceFileExtension, StaticVersion staticVersion, SourceFileLocation sourceFileLocation,
                                                   ReportProperties reportProperties, ReportType reportType) {
        DroolsReportProperties droolsReportProperties = (DroolsReportProperties) reportProperties;

        ValueRange body = new ValueRange();

        switch (reportType) {
            case DMN: {
                body.setValues(getBodyValues(new DMNReportData(), new DMNSheetPositions(), sourceFileExtension, staticVersion, sourceFileLocation, droolsReportProperties));
                break;
            }
            case EVENT_PROCESSING: {
                body.setValues(getBodyValues(new CEPReportData(), new CEPSheetPositions(), sourceFileExtension, staticVersion, sourceFileLocation, droolsReportProperties));
                break;
            }
            case EVENT_PROCESSING_MULTITHREADED: {
                body.setValues(getBodyValues(new CEPReportData(true), new CEPMultithreadedSheetPositions(), sourceFileExtension, staticVersion, sourceFileLocation, droolsReportProperties));
                break;
            }
            case OOPATH: {
                body.setValues(getBodyValues(new OopathReportData(), new OopathSheetPositions(), sourceFileExtension, staticVersion, sourceFileLocation, droolsReportProperties));
                break;
            }
            case OPERATORS: {
                body.setValues(getBodyValues(new OperatorsReportData(), new OperatorsSheetPositions(), sourceFileExtension, staticVersion, sourceFileLocation, droolsReportProperties));
                break;
            }
            case SESSION: {
                body.setValues(getBodyValues(new SessionReportData(), new SessionSheetPositions(), sourceFileExtension, staticVersion, sourceFileLocation, droolsReportProperties));
                break;
            }
            case BUILDTIME: {
                body.setValues(getBodyValues(new BuildtimeReportData(), new BuildtimeSheetPositions(), sourceFileExtension, staticVersion, sourceFileLocation, droolsReportProperties));
                break;
            }
            case RUNTIME: {
                body.setValues(getBodyValues(new RuntimeReportData(), new RuntimeSheetPositions(), sourceFileExtension, staticVersion, sourceFileLocation, droolsReportProperties));
                break;
            }
            case RUNTIME_MULTITHREADED: {
                body.setValues(getBodyValues(new RuntimeReportData(true), new RuntimeMultithreadedSheetPositions(), sourceFileExtension, staticVersion, sourceFileLocation, droolsReportProperties));
                break;
            }
        }

        return body;
    }

    private List getBodyValues(ReportData reportData, ReportSheetPositions
            reportSheetPositions, SourceFileExtension sourceFileExtension, StaticVersion staticVersion,
                               SourceFileLocation sourceFileLocation, DroolsReportProperties droolsReportProperties) {
        List values = new ArrayList();

        String path = reportData.getDataSourcePath(staticVersion, droolsReportProperties);
        if (!path.isEmpty()) {
            values.addAll(filterValues(reportSheetPositions.getPositions(), reportData.getData(path, sourceFileExtension, sourceFileLocation)));
        }

        return values;
    }

    private List filterValues(Map<Integer, RowDataToMap> positions, List<ReportRow> reportRows) {
        List values = new ArrayList();

        for (Integer key : positions.keySet()) {
            for (int i = 0; i < reportRows.size(); i++) {
                if (reportRows.get(i).getUniqueID().equals(positions.get(key).getRowId())) {
                    String score = reportRows.get(i).getScore();
                    values.add(Arrays.asList(score));

                    break;
                }
            }
        }

        return values;
    }
}
