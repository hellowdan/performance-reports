package org.benchmarks.commons.exceptions;

import java.io.IOException;

public class GoogleDriveDownloadFileException extends IOException {

    public GoogleDriveDownloadFileException(String fileID, Throwable cause) {
        super(ExceptionsConstants.FAILED_DOWNLOAD_FILE_GOOGLE_DRIVE_PARAM.replace("%s", fileID), cause);
    }

}
