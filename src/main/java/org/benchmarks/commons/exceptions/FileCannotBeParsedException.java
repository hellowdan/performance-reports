package org.benchmarks.commons.exceptions;

public class FileCannotBeParsedException extends IllegalArgumentException {

    public FileCannotBeParsedException(String cause) {
        super(ExceptionsConstants.FILE_CANNOT_BE_PARSED_PARAM.replace("%s", cause));
    }

}
