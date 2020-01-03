package org.benchmarks.exceptions;

public class FileCannotBeParsedException extends IllegalArgumentException {

    public FileCannotBeParsedException(String cause) {
        super(String.format(ExceptionsConstants.FILE_CANNOT_BE_PARSED_PARAM, cause));
    }
}
