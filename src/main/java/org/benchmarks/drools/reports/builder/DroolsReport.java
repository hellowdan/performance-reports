package org.benchmarks.drools.reports.builder;

import java.io.IOException;

import com.google.api.services.docs.v1.model.Request;
import org.benchmarks.drools.reports.data.DroolsProperties;
import org.benchmarks.reports.builder.Report;
import org.benchmarks.reports.data.Version;
import org.json.simple.parser.ParseException;

import java.security.GeneralSecurityException;
import java.util.List;

public class DroolsReport extends Report {

    private DroolsProperties reportProperties;
    private String folderNewId;
    private String spreadSheetNewId;
    private String docNewId;

    public DroolsReport() throws IOException, GeneralSecurityException {
        this.reportProperties = DroolsProperties.getInstance();
    }

    /*for tests purposes*/
    public DroolsReport(DroolsProperties reportProperties) throws IOException, GeneralSecurityException {
        this.reportProperties = reportProperties;
    }

    public void generateReport() throws IOException, ParseException {
        this.folderNewId = createNewDir(this.reportProperties.getFolderTitle(), this.reportProperties.getResultParentFolderID());
        createSpreadSheet();
        createDoc();
    }

    protected Boolean createSpreadSheet() throws IOException, ParseException {
        this.spreadSheetNewId = copyFile(this.reportProperties.getFilesTitle(), this.reportProperties.getTemplateSheetID());
        DroolsFileSpreadSheet droolsFileSpreadSheet = new DroolsFileSpreadSheet(this.spreadSheetNewId,
                this.reportProperties, this.getSheetService());

        droolsFileSpreadSheet.updateSpreadSheetInfo();
        droolsFileSpreadSheet.updateSpreadSheetValues(Version.NEW, "K2");
        droolsFileSpreadSheet.updateSpreadSheetValues(Version.PREVIOUS, "J2");
        droolsFileSpreadSheet.updateSpreadSheetValues(Version.OLDER, "I2");

        moveFile(this.spreadSheetNewId, this.folderNewId);
        return setPublishFile(this.spreadSheetNewId);
    }

    protected String createDoc() throws IOException {
        this.docNewId = copyFile(this.reportProperties.getFilesTitle(), this.reportProperties.getTemplateDocID());
        DroolsFileDoc droolsFileDoc = new DroolsFileDoc(this.docNewId, this.spreadSheetNewId, this.reportProperties, this.getDocsService(), this.getSheetService());

        List<Request> requests = droolsFileDoc.getReplaceAllBody();
        droolsFileDoc.requestsExecute(requests);

        droolsFileDoc.updateChartWithLink();

        return moveFile(this.docNewId, this.folderNewId);
    }
}
