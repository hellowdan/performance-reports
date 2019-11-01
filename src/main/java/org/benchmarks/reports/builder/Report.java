package org.benchmarks.reports.builder;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.security.GeneralSecurityException;

/*Provides the general Google authentication for the inherited classes.
 *Already implemented for Drools.*/
public class Report {

    protected Docs docsService;
    protected Drive driveService;
    protected Sheets sheetService;

    public Report() throws IOException, GeneralSecurityException {
        GoogleService googleService = new GoogleService();
        this.setDocsService(googleService.getDocs());
        this.setSheetService(googleService.getSheets());
        this.setDriveService(googleService.getDrive());
    }

    public Docs getDocsService() {
        return docsService;
    }

    public void setDocsService(Docs docsService) {
        this.docsService = docsService;
    }

    public Drive getDriveService() {
        return driveService;
    }

    public void setDriveService(Drive driveService) {
        this.driveService = driveService;
    }

    public Sheets getSheetService() {
        return sheetService;
    }

    public void setSheetService(Sheets sheetService) {
        this.sheetService = sheetService;
    }

}
