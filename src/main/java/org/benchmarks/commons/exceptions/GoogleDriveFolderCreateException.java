package org.benchmarks.commons.exceptions;

import java.io.IOException;

public class GoogleDriveFolderCreateException extends IOException {

    public GoogleDriveFolderCreateException(String folderName, Throwable cause) {
        super(ExceptionsConstants.FAILED_CREATE_FOLDER_GOOGLE_DRIVE.replace("%s", folderName), cause);
    }

}
