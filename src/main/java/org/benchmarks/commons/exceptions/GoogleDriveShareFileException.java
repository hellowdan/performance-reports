package org.benchmarks.commons.exceptions;

import java.io.IOException;

public class GoogleDriveShareFileException extends IOException {

    public GoogleDriveShareFileException(String fileID, Throwable cause) {
        super(ExceptionsConstants.FAILED_SHARE_FILE_GOOGLE_DRIVE_PARAM.replace("%s", fileID), cause);
    }

}
