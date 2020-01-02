package org.benchmarks.exceptions;

import org.benchmarks.definitions.JenkinsReportFileExtension;

public class InvalidFileExtensionException extends IllegalArgumentException {

    public InvalidFileExtensionException(JenkinsReportFileExtension fileExtension) {
        super(String.format(ExceptionsConstants.INVALID_FILE_EXTENSION_PARAM, fileExtension.getExtension()));
    }
}
