package org.benchmarks.commons.exceptions;

import org.benchmarks.commons.definitions.JenkinsReportFileExtension;

public class InvalidFileExtensionException extends IllegalArgumentException {

    public InvalidFileExtensionException(JenkinsReportFileExtension fileExtension) {
        super(ExceptionsConstants.INVALID_FILE_EXTENSION_PARAM.replace("%s", fileExtension.getExtension()));
    }

}
