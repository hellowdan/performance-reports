package org.benchmarks.reports.util;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.docs.v1.DocsScopes;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.benchmarks.drools.reports.data.DroolsProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GoogleCredencialAuthorize {

    public static GoogleCredential authorize() throws IOException {
        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS, DocsScopes.DOCUMENTS, SheetsScopes.DRIVE);

        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(DroolsProperties.getInstance().getGoogleAppApiKeyFile()))
                .createScoped(scopes);

        return credential;
    }
}
