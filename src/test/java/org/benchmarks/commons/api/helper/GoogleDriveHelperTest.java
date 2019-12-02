package org.benchmarks.commons.api.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;

import com.google.api.services.drive.Drive;
import org.benchmarks.commons.definitions.JenkinsReportFileExtension;
import org.benchmarks.commons.definitions.JenkinsReportType;
import org.benchmarks.commons.definitions.JenkinsReportVersion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GoogleDriveHelperTest {

    private static String newFolderTitle = "newFolderTest";
    private static String newFileTitle = "newFileTest";
    private static String ResultParentFolderID = "1nKgp9BcIoM0ZNAk40ovDrCcweu5OVS1T";
    private static String templateDocID = "1094iiurK5lwlcVHR4zRluW7YZOeeXm9VLxBRADpvSOo";
    private static String templateSheetID = "1V3ITyMR7aiHAvSbCy5aOv6U3xtu5KGWAVaLFiYqOoIU";
    private static String fileToDownloadID = "12ZUBiGYRqa4h73f-e06WJ9WzETcSrUGc";
    private static String filePathWriteToFileTest = "writeToFileTest.csv";
    private String filePathPrepareGoogleDriveFile = "";
    private Drive driveService;

    @Before
    public void setUp() throws Exception {
        GoogleDriveService googleDriveService = new GoogleDriveService("/service_account.json");
        this.driveService = googleDriveService.getDrive();
    }

    @Test
    public void createNewDirTest() {
        String folderNewId;
        folderNewId = GoogleDriveHelper.createNewDir(this.driveService, newFolderTitle, ResultParentFolderID);

        Assert.assertNotNull(folderNewId);
    }

    @Test
    public void moveFileTest() {
        String docCopiedFileNewId;
        String docMovedFileNewId;

        docCopiedFileNewId = GoogleDriveHelper.copyFile(this.driveService, newFileTitle, templateSheetID);
        docMovedFileNewId = GoogleDriveHelper.moveFile(this.driveService, docCopiedFileNewId, ResultParentFolderID);

        Assert.assertNotNull(docMovedFileNewId);
    }

    @Test
    public void copyFileTest() {
        String docCopiedFileNewId;

        docCopiedFileNewId = GoogleDriveHelper.copyFile(this.driveService, newFileTitle, templateDocID);

        Assert.assertNotNull(docCopiedFileNewId);
    }

    @Test
    public void downloadFileTest() {
        ByteArrayOutputStream byteArrayOutputStream;
        byteArrayOutputStream = GoogleDriveHelper.downloadFile(this.driveService, fileToDownloadID);

        Assert.assertNotNull(byteArrayOutputStream);
    }

    @Test
    public void setPublishFileTest() {
        Boolean result = GoogleDriveHelper.setPublishFile(this.driveService, templateDocID);

        assertThat(result, is(true));
    }

    @Test
    public void writeToFileTest() {
        Boolean result;
        ByteArrayOutputStream byteArrayOutputStream;

        byteArrayOutputStream = GoogleDriveHelper.downloadFile(this.driveService, fileToDownloadID);
        result = GoogleDriveHelper.writeToFile(byteArrayOutputStream, filePathWriteToFileTest);

        assertThat(result, is(true));
    }

    @Test
    public void prepareGoogleDriveFileTest() {
        this.filePathPrepareGoogleDriveFile = GoogleDriveHelper.prepareGoogleDriveFile(this.driveService, JenkinsReportType.BUILDTIME.getFileType(), fileToDownloadID, JenkinsReportVersion.NEW, JenkinsReportFileExtension.CSV);
        File downloadedFile = new File(this.filePathPrepareGoogleDriveFile);

        assertThat(downloadedFile.exists(), is(true));
    }
}