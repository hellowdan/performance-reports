package org.benchmarks.exceptions;

import java.io.IOException;

public class GoogleSpreadsheetUpdateTextException extends IOException {

    public GoogleSpreadsheetUpdateTextException(Throwable cause) {
        super(ExceptionsConstants.FAILED_UPDATE_SPREADSHEET_TEXT, cause);
    }
}
