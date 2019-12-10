package org.benchmarks.commons.exceptions;

import java.io.IOException;

public class FileCannotBeFoundException extends IOException {

    public FileCannotBeFoundException(String filePath, Throwable cause) {
        super(ExceptionsConstants.FILE_NOT_FOUND_PARAM.replace("%s", filePath), cause);
    }

}
