package org.benchmarks.commons.exceptions;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;

public class JsonMappingFromCSVException extends JsonGenerationException {

    public JsonMappingFromCSVException(String filePath, Throwable cause, JsonGenerator g) {
        super(ExceptionsConstants.FAILED_MAP_JSON_FROM_CSV_PARAM.replace("%s", filePath), cause, g);
    }

}
