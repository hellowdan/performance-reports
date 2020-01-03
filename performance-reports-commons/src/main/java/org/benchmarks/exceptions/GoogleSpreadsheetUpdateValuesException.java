package org.benchmarks.exceptions;

import java.io.IOException;

public class GoogleSpreadsheetUpdateValuesException extends IOException {

    public GoogleSpreadsheetUpdateValuesException(Throwable cause) {
        super(ExceptionsConstants.FAILED_UPDATE_SPREADSHEET_VALUES, cause);
    }
}
