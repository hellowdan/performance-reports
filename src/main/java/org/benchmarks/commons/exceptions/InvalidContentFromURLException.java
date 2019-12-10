package org.benchmarks.commons.exceptions;

import java.io.IOException;

public class InvalidContentFromURLException extends IOException {

    public InvalidContentFromURLException(String url, Throwable cause) {
        super(ExceptionsConstants.CONTENT_FROM_URL_CANNOT_BE_READ_PARAM.replace("%s", url), cause);
    }

}
