package org.benchmarks.drools.reports.builder;

import com.google.api.services.docs.v1.model.Request;
import org.benchmarks.drools.reports.data.DroolsProperties;
import org.benchmarks.reports.builder.Report;
import org.benchmarks.reports.data.FileLocation;
import org.benchmarks.reports.data.Version;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;
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

    public void generateReport() throws Exception {
        this.folderNewId = createNewDir(this.reportProperties.getFolderTitle(), this.reportProperties.getResultParentFolderID());
        createSpreadSheet();
        createDoc();
    }

    protected Boolean createSpreadSheet() throws Exception {
        this.spreadSheetNewId = copyFile(this.reportProperties.getFilesTitle(), this.reportProperties.getTemplateSheetID());
        DroolsFileSpreadSheet droolsFileSpreadSheet = new DroolsFileSpreadSheet(this.spreadSheetNewId,
                this.reportProperties, this.getSheetService());

        prepareDriveFiles();

        droolsFileSpreadSheet.updateSpreadSheetInfo();
        droolsFileSpreadSheet.updateSpreadSheetValues(Version.NEW, this.reportProperties.getNewVersionFileLocation(), "K2");
        droolsFileSpreadSheet.updateSpreadSheetValues(Version.PREVIOUS, this.reportProperties.getPreviousVersionFileLocation(), "J2");
        droolsFileSpreadSheet.updateSpreadSheetValues(Version.OLDER, this.reportProperties.getOlderVersionFileLocation(), "I2");

        moveFile(this.spreadSheetNewId, this.folderNewId);
        return setPublishFile(this.spreadSheetNewId);
    }

    /*Supports only CSV format files stored in a Google Drive folder*/
    protected void prepareDriveFiles() throws Exception {
        String fileBuildtimeID;
        String fileBuildtimePath;
        ByteArrayOutputStream byteArrayOutputStreamBuildtime;

        String fileRuntimeID;
        String fileRuntimePath;
        ByteArrayOutputStream byteArrayOutputStreamRuntime;

        if (this.reportProperties.getNewVersionFileLocation() == FileLocation.DRIVE) {

            this.reportProperties.setNewVersionFileLocation(FileLocation.LOCAL);

            fileBuildtimeID = this.reportProperties.getNewVersionBuildtimePath();
            fileBuildtimePath = "new_version_buildtime.csv";
            this.reportProperties.setNewVersionBuildtimePath(fileBuildtimePath);
            byteArrayOutputStreamBuildtime = downloadFile(fileBuildtimeID);
            WriteToFile(byteArrayOutputStreamBuildtime, fileBuildtimePath);

            fileRuntimeID = this.reportProperties.getNewVersionRuntimePath();
            fileRuntimePath = "new_version_runtime.csv";
            this.reportProperties.setNewVersionRuntimePath(fileRuntimePath);
            byteArrayOutputStreamRuntime = downloadFile(fileRuntimeID);
            WriteToFile(byteArrayOutputStreamRuntime, fileRuntimePath);
        }

        if (this.reportProperties.getPreviousVersionFileLocation() == FileLocation.DRIVE) {
            this.reportProperties.setPreviousVersionFileLocation(FileLocation.LOCAL);

            fileBuildtimeID = this.reportProperties.getPreviousVersionBuildtimePath();
            fileBuildtimePath = "previous_version_buildtime.csv";
            this.reportProperties.setPreviousVersionBuildtimePath(fileBuildtimePath);
            byteArrayOutputStreamBuildtime = downloadFile(fileBuildtimeID);
            WriteToFile(byteArrayOutputStreamBuildtime, fileBuildtimePath);

            fileRuntimeID = this.reportProperties.getPreviousVersionRuntimePath();
            fileRuntimePath = "previous_version_runtime.csv";
            this.reportProperties.setPreviousVersionRuntimePath(fileRuntimePath);
            byteArrayOutputStreamRuntime = downloadFile(fileRuntimeID);
            WriteToFile(byteArrayOutputStreamRuntime, fileRuntimePath);
        }

        if (this.reportProperties.getOlderVersionFileLocation() == FileLocation.DRIVE) {
            this.reportProperties.setOlderVersionFileLocation(FileLocation.LOCAL);

            fileBuildtimeID = this.reportProperties.getOlderVersionBuildtimePath();
            fileBuildtimePath = "older_version_buildtime.csv";
            this.reportProperties.setOlderVersionBuildtimePath(fileBuildtimePath);
            byteArrayOutputStreamBuildtime = downloadFile(fileBuildtimeID);
            WriteToFile(byteArrayOutputStreamBuildtime, fileBuildtimePath);

            fileRuntimeID = this.reportProperties.getOlderVersionRuntimePath();
            fileRuntimePath = "older_version_runtime.csv";
            this.reportProperties.setOlderVersionRuntimePath(fileRuntimePath);
            byteArrayOutputStreamRuntime = downloadFile(fileRuntimeID);
            WriteToFile(byteArrayOutputStreamRuntime, fileRuntimePath);
        }
    }

    /*I intend to write upload methods to store datasources on google drive*/
    private String getDataFormatNameToUpload(String type, String version) {
        String newName;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer year = cal.get(Calendar.YEAR);
        Integer month = cal.get(Calendar.MONTH);
        Integer day = cal.get(Calendar.DAY_OF_MONTH);

        newName = year.toString() + month.toString() + day.toString() + type + version;

        return newName;
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
