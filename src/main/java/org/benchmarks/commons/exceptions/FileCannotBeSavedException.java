package org.benchmarks.commons.exceptions;

import java.io.IOException;

public class FileCannotBeSavedException extends IOException {

    public FileCannotBeSavedException(String filePath, Throwable cause) {
        super(ExceptionsConstants.FAILED_SAVE_TO_DISK.replace("%s", filePath), cause);
    }

}
