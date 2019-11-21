package org.benchmarks.drools.reports.builder;

import com.google.api.services.docs.v1.model.Request;
import org.benchmarks.drools.reports.data.DroolsProperties;
import org.benchmarks.reports.builder.Report;
import org.benchmarks.reports.data.FileLocation;
import org.benchmarks.reports.data.Version;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
        //uploadDataSources();
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

    protected void prepareDriveFiles() throws Exception {
        String filePathBuildtime;
        String filePathBuildtimeToSave;
        ByteArrayOutputStream byteArrayOutputStreamBuildtime;

        String filePathRuntime;
        String filePathRuntimeToSave;
        ByteArrayOutputStream byteArrayOutputStreamRuntime;

        if (this.reportProperties.getNewVersionFileLocation() == FileLocation.DRIVE) {
            this.reportProperties.setNewVersionFileLocation(FileLocation.LOCAL);
            if (this.reportProperties.getUseCsv()) {
                filePathBuildtime = this.reportProperties.getNewVersionBuildtimeCsvPath();
                filePathBuildtimeToSave = "new_version_buildtime.csv";
                this.reportProperties.setNewVersionBuildtimeCsvPath(filePathBuildtimeToSave);

                filePathRuntime = this.reportProperties.getNewVersionRuntimeCsvPath();
                filePathRuntimeToSave = "new_version_runtime.csv";
                this.reportProperties.setNewVersionRuntimeCsvPath(filePathRuntimeToSave);
            } else {
                filePathBuildtime = this.reportProperties.getNewVersionBuildtimeJsonPath();
                filePathBuildtimeToSave = "new_version_buildtime.json";
                this.reportProperties.setNewVersionBuildtimeJsonPath(filePathBuildtimeToSave);

                filePathRuntime = this.reportProperties.getNewVersionRuntimeJsonPath();
                filePathRuntimeToSave = "new_version_runtime.json";
                this.reportProperties.setNewVersionRuntimeJsonPath(filePathRuntimeToSave);
            }
            byteArrayOutputStreamBuildtime = downloadFile(filePathBuildtime);
            WriteToFile(byteArrayOutputStreamBuildtime, filePathBuildtimeToSave);

            byteArrayOutputStreamRuntime = downloadFile(filePathRuntime);
            WriteToFile(byteArrayOutputStreamRuntime, filePathRuntimeToSave);
        }

        if (this.reportProperties.getPreviousVersionFileLocation() == FileLocation.DRIVE) {
            this.reportProperties.setPreviousVersionFileLocation(FileLocation.LOCAL);
            if (this.reportProperties.getUseCsv()) {
                filePathBuildtime = this.reportProperties.getPreviousVersionBuildtimeCsvPath();
                filePathBuildtimeToSave = "previous_version_buildtime.csv";
                this.reportProperties.setPreviousVersionBuildtimeCsvPath(filePathBuildtimeToSave);

                filePathRuntime = this.reportProperties.getPreviousVersionRuntimeCsvPath();
                filePathRuntimeToSave = "previous_version_runtime.csv";
                this.reportProperties.setPreviousVersionRuntimeCsvPath(filePathRuntimeToSave);
            } else {
                filePathBuildtime = this.reportProperties.getPreviousVersionBuildtimeJsonPath();
                filePathBuildtimeToSave = "previous_version_buildtime.json";
                this.reportProperties.setPreviousVersionBuildtimeJsonPath(filePathBuildtimeToSave);

                filePathRuntime = this.reportProperties.getPreviousVersionRuntimeJsonPath();
                filePathRuntimeToSave = "previous_version_runtime.json";
                this.reportProperties.setPreviousVersionRuntimeJsonPath(filePathRuntimeToSave);
            }
            byteArrayOutputStreamBuildtime = downloadFile(filePathBuildtime);
            WriteToFile(byteArrayOutputStreamBuildtime, filePathBuildtimeToSave);

            byteArrayOutputStreamRuntime = downloadFile(filePathRuntime);
            WriteToFile(byteArrayOutputStreamRuntime, filePathRuntimeToSave);
        }

        if (this.reportProperties.getOlderVersionFileLocation() == FileLocation.DRIVE) {
            this.reportProperties.setOlderVersionFileLocation(FileLocation.LOCAL);
            if (this.reportProperties.getUseCsv()) {
                filePathBuildtime = this.reportProperties.getOlderVersionBuildtimeCsvPath();
                filePathBuildtimeToSave = "older_version_buildtime.csv";
                this.reportProperties.setOlderVersionBuildtimeCsvPath(filePathBuildtimeToSave);

                filePathRuntime = this.reportProperties.getOlderVersionRuntimeCsvPath();
                filePathRuntimeToSave = "older_version_runtime.csv";
                this.reportProperties.setOlderVersionRuntimeCsvPath(filePathRuntimeToSave);
            } else {
                filePathBuildtime = this.reportProperties.getOlderVersionBuildtimeJsonPath();
                filePathBuildtimeToSave = "older_version_buildtime.json";
                this.reportProperties.setOlderVersionBuildtimeJsonPath(filePathBuildtimeToSave);

                filePathRuntime = this.reportProperties.getOlderVersionRuntimeJsonPath();
                filePathRuntimeToSave = "older_version_runtime.json";
                this.reportProperties.setOlderVersionRuntimeJsonPath(filePathRuntimeToSave);
            }
            byteArrayOutputStreamBuildtime = downloadFile(filePathBuildtime);
            WriteToFile(byteArrayOutputStreamBuildtime, filePathBuildtimeToSave);

            byteArrayOutputStreamRuntime = downloadFile(filePathRuntime);
            WriteToFile(byteArrayOutputStreamRuntime, filePathRuntimeToSave);
        }
    }

    public static void WriteToFile(ByteArrayOutputStream byteArrayOutputStream, String filePath) throws Exception {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            byteArrayOutputStream.writeTo(outputStream);
        }
    }

    protected void uploadDataSources() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer year = cal.get(Calendar.YEAR);
        Integer month = cal.get(Calendar.MONTH);
        Integer day = cal.get(Calendar.DAY_OF_MONTH);

        String newVersionBuildtimefileName = year.toString() + month.toString() + day.toString() + "buildtime" + this.reportProperties.getNewVersion();
        String previousVersionBuildtimefileName = year.toString() + month.toString() + day.toString() + "buildtime" + this.reportProperties.getPreviousVersion();
        String olderVersionBuildtimefileName = year.toString() + month.toString() + day.toString() + "buildtime" + this.reportProperties.getOlderVersion();
        String newVersionRuntimefileName = year.toString() + month.toString() + day.toString() + "runtime" + this.reportProperties.getNewVersion();
        String previousVersionRuntimefileName = year.toString() + month.toString() + day.toString() + "runtime" + this.reportProperties.getPreviousVersion();
        String olderVersionRuntimefileName = year.toString() + month.toString() + day.toString() + "runtime" + this.reportProperties.getOlderVersion();

        if (this.reportProperties.getUseCsv()) {
            uploadFile(this.reportProperties.getNewVersionBuildtimeCsvPath(), newVersionBuildtimefileName, this.reportProperties.getNewVersionRepositoryFolderID());
            uploadFile(this.reportProperties.getPreviousVersionBuildtimeCsvPath(), previousVersionBuildtimefileName, this.reportProperties.getPreviousVersionRepositoryFolderID());
            uploadFile(this.reportProperties.getOlderVersionBuildtimeCsvPath(), olderVersionBuildtimefileName, this.reportProperties.getOlderVersionRepositoryFolderID());
            uploadFile(this.reportProperties.getNewVersionRuntimeCsvPath(), newVersionRuntimefileName, this.reportProperties.getNewVersionRepositoryFolderID());
            uploadFile(this.reportProperties.getPreviousVersionRuntimeCsvPath(), previousVersionRuntimefileName, this.reportProperties.getPreviousVersionRepositoryFolderID());
            uploadFile(this.reportProperties.getOlderVersionRuntimeCsvPath(), olderVersionRuntimefileName, this.reportProperties.getOlderVersionRepositoryFolderID());
        } else {
            uploadFile(this.reportProperties.getNewVersionBuildtimeJsonPath(), newVersionBuildtimefileName, this.reportProperties.getNewVersionRepositoryFolderID());
            uploadFile(this.reportProperties.getPreviousVersionBuildtimeJsonPath(), previousVersionBuildtimefileName, this.reportProperties.getPreviousVersionRepositoryFolderID());
            uploadFile(this.reportProperties.getOlderVersionBuildtimeJsonPath(), olderVersionBuildtimefileName, this.reportProperties.getOlderVersionRepositoryFolderID());
            uploadFile(this.reportProperties.getNewVersionRuntimeJsonPath(), newVersionRuntimefileName, this.reportProperties.getNewVersionRepositoryFolderID());
            uploadFile(this.reportProperties.getPreviousVersionRuntimeJsonPath(), previousVersionRuntimefileName, this.reportProperties.getPreviousVersionRepositoryFolderID());
            uploadFile(this.reportProperties.getOlderVersionRuntimeJsonPath(), olderVersionRuntimefileName, this.reportProperties.getOlderVersionRepositoryFolderID());
        }
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
