package org.benchmarks.commons.exceptions;

public class GoogleCredentialException extends Exception {

    public GoogleCredentialException(Throwable cause) {
        super(ExceptionsConstants.FAILED_GET_GOOGLE_CREDENCIALS, cause);
    }

}
