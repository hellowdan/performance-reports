package org.benchmarks.documents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.sheets.v4.model.ValueRange;

public class BREGoogleDriveDocument extends DroolsGoogleDriveDocument {

    public BREGoogleDriveDocument() {
        super();
    }

    public List<Request> getFormatAllTables(Docs docsService, String docID, ValueRange buildTimeValueRange, ValueRange runTimeValueRange) throws IOException {
        List<Request> requests = new ArrayList<>();
        requests.addAll(getTableUpdateCellColorRequests(docID, docsService, buildTimeValueRange));
        requests.addAll(getTableUpdateCellColorRequests(docID, docsService, runTimeValueRange));

        return requests;
    }

    public List<Request> getReplaceAllTables(ValueRange buildTimeValueRange, ValueRange runTimeValueRange) throws IOException {
        final String buildtimeTableName = "Buildtime_Test_Result_Table";
        final int buildtimeStartingCol = 1;
        final int buildtimeStartingRow = 3;
        final String runtimeTableName = "Runtime_Test_Result_Table";
        final int runtimeStartingCol = 1;
        final int runtimeStartingRow = 3;

        final String cellFormat = "%s_%d_%d";

        List<Request> requests = new ArrayList<>();
        requests.addAll(getTableUpdateRequest(buildTimeValueRange, cellFormat, buildtimeTableName, buildtimeStartingCol, buildtimeStartingRow));
        requests.addAll(getTableUpdateRequest(runTimeValueRange, cellFormat, runtimeTableName, runtimeStartingCol, runtimeStartingRow));

        return requests;
    }
}
