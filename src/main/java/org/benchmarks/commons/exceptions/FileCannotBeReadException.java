package org.benchmarks.commons.exceptions;

import java.io.IOException;

public class FileCannotBeReadException extends IOException {

    public FileCannotBeReadException(Throwable cause) {
        super(ExceptionsConstants.FILE_CANNOT_BE_READ, cause);
    }

    public FileCannotBeReadException(String fileName, Throwable cause) {
        super(ExceptionsConstants.FILE_CANNOT_BE_READ, cause);
    }

}
