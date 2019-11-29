package org.benchmarks.commons.api.helper;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.docs.v1.DocsScopes;
import com.google.api.services.sheets.v4.SheetsScopes;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.benchmarks.drools.definitions.DroolsPropertiesLoader;

public class GoogleAuthorizeAsService {

    public static GoogleCredential authorize() throws IOException {
        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS, DocsScopes.DOCUMENTS, SheetsScopes.DRIVE);

        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(DroolsPropertiesLoader.getInstance().getGoogleAppApiKeyFile()))
                .createScoped(scopes);

        return credential;
    }
}
