package org.benchmarks.documents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.sheets.v4.model.ValueRange;

public class DMNGoogleDriveDocument extends DroolsGoogleDriveDocument {

    public DMNGoogleDriveDocument() {
        super();
    }

    public List<Request> getFormatAllTables(Docs docsService, String docID, ValueRange dmnBuildtimeValueRange, ValueRange dmnRuntimeValueRange, ValueRange dmnFEELValueRange) throws IOException {
        List<Request> requests = new ArrayList<>();
        requests.addAll(getTableUpdateCellColorRequests(docID, docsService, dmnBuildtimeValueRange));
        requests.addAll(getTableUpdateCellColorRequests(docID, docsService, dmnRuntimeValueRange));
        requests.addAll(getTableUpdateCellColorRequests(docID, docsService, dmnFEELValueRange));

        return requests;
    }

    public List<Request> getReplaceAllTables(ValueRange dmnBuildtimeValueRange, ValueRange dmnRuntimeValueRange, ValueRange dmnFEELValueRange) throws IOException {
        final String dmnBuildtimeTableName = "DMN_Buildtime_Test_Result_Table";
        final int dmnBuildtimeStartingCol = 1;
        final int dmnBuildtimeStartingRow = 3;

        final String dmnRuntimeTableName = "DMN_Runtime_Test_Result_Table";
        final int dmnRuntimeStartingCol = 1;
        final int dmnRuntimeStartingRow = 3;

        final String dmnFEELTableName = "DMN_FEEL_Test_Result_Table";
        final int dmnFEELStartingCol = 1;
        final int dmnFEELStartingRow = 3;

        final String cellFormat = "%s_%d_%d";

        List<Request> requests = new ArrayList<>();
        requests.addAll(getTableUpdateRequest(dmnBuildtimeValueRange, cellFormat, dmnBuildtimeTableName, dmnBuildtimeStartingCol, dmnBuildtimeStartingRow));
        requests.addAll(getTableUpdateRequest(dmnRuntimeValueRange, cellFormat, dmnRuntimeTableName, dmnRuntimeStartingCol, dmnRuntimeStartingRow));
        requests.addAll(getTableUpdateRequest(dmnFEELValueRange, cellFormat, dmnFEELTableName, dmnFEELStartingCol, dmnFEELStartingRow));

        return requests;
    }
}
