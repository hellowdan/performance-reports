package org.benchmarks.documents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;
import org.benchmarks.data.cep.CEPReportData;
import org.benchmarks.data.dmn.DMNReportData;
import org.benchmarks.data.oopath.OopathReportData;
import org.benchmarks.data.operators.OperatorsReportData;
import org.benchmarks.data.session.SessionReportData;
import org.benchmarks.data.turtle.buildtime.BuildtimeReportData;
import org.benchmarks.data.turtle.runtime.RuntimeReportData;
import org.benchmarks.data.ReportRow;
import org.benchmarks.definitions.DroolsReportProperties;
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
    protected ValueRange getUpdateDataRequestsBody(SourceFileExtension sourceFileExtension, StaticVersion staticVersion, SourceFileLocation sourceFileLocation, ReportProperties reportProperties) throws IOException {
        DroolsReportProperties droolsReportProperties = (DroolsReportProperties) reportProperties;

        ValueRange body = new ValueRange();

        BuildtimeReportData buildtimeReportData = new BuildtimeReportData();
        RuntimeReportData runtimeReportData = new RuntimeReportData();
        DMNReportData dmnReportData = new DMNReportData();
        CEPReportData cepReportData = new CEPReportData();
        CEPReportData cepMultithreadedReportData = new CEPReportData(true);
        OopathReportData oopathReportData = new OopathReportData();
        OperatorsReportData operatorsReportData = new OperatorsReportData();
        RuntimeReportData runtimeMultithreadedReportData = new RuntimeReportData(true);
        SessionReportData sessionReportData = new SessionReportData();

        String buildtimePath = buildtimeReportData.getDataSourcePath(staticVersion, droolsReportProperties);
        String runtimePath = runtimeReportData.getDataSourcePath(staticVersion, droolsReportProperties);
        String dmnPath = dmnReportData.getDataSourcePath(staticVersion, droolsReportProperties);
        String cepPath = cepReportData.getDataSourcePath(staticVersion, droolsReportProperties);
        String cepMultithreadedPath = cepMultithreadedReportData.getDataSourcePath(staticVersion, droolsReportProperties);
        String oopathPath = oopathReportData.getDataSourcePath(staticVersion, droolsReportProperties);
        String operatorsPath = operatorsReportData.getDataSourcePath(staticVersion, droolsReportProperties);
        String runtimeMultithreadedPath = runtimeMultithreadedReportData.getDataSourcePath(staticVersion, droolsReportProperties);
        String sessionPath = sessionReportData.getDataSourcePath(staticVersion, droolsReportProperties);

        List values = new ArrayList();
        values.addAll(getValuesAsList(BuildtimeSheetPositions.getPositions(), buildtimeReportData.getData(buildtimePath, sourceFileExtension, sourceFileLocation)));
        values.addAll(getValuesAsList(RuntimeSheetPositions.getPositions(), runtimeReportData.getData(runtimePath, sourceFileExtension, sourceFileLocation)));
        values.addAll(getValuesAsList(DMNSheetPositions.getPositions(), dmnReportData.getData(dmnPath, sourceFileExtension, sourceFileLocation)));
        values.addAll(getValuesAsList(CEPSheetPositions.getPositions(), cepReportData.getData(cepPath, sourceFileExtension, sourceFileLocation)));
        values.addAll(getValuesAsList(CEPMultithreadedSheetPositions.getPositions(), cepMultithreadedReportData.getData(cepMultithreadedPath, sourceFileExtension, sourceFileLocation)));
        values.addAll(getValuesAsList(OopathSheetPositions.getPositions(), oopathReportData.getData(oopathPath, sourceFileExtension, sourceFileLocation)));
        values.addAll(getValuesAsList(OperatorsSheetPositions.getPositions(), operatorsReportData.getData(operatorsPath, sourceFileExtension, sourceFileLocation)));
        values.addAll(getValuesAsList(RuntimeMultithreadedSheetPositions.getPositions(), runtimeMultithreadedReportData.getData(runtimeMultithreadedPath, sourceFileExtension, sourceFileLocation)));
        values.addAll(getValuesAsList(SessionSheetPositions.getPositions(), sessionReportData.getData(sessionPath, sourceFileExtension, sourceFileLocation)));
        body.setValues(values);

        return body;
    }

    private List getValuesAsList(Map<Integer, RowDataToMap> positions, List<ReportRow> reportRows) {
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
