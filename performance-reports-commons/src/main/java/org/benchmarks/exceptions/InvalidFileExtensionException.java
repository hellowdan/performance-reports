package org.benchmarks.exceptions;

import org.benchmarks.definitions.SourceFileExtension;

public class InvalidFileExtensionException extends IllegalArgumentException {

    public InvalidFileExtensionException(SourceFileExtension fileExtension) {
        super(String.format(ExceptionsConstants.INVALID_FILE_EXTENSION_PARAM, fileExtension.getExtension()));
    }
}
