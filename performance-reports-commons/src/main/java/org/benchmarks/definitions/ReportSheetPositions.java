package org.benchmarks.definitions;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class ReportSheetPositions {
    protected static final Map<Integer, RowDataToMap> positions = new LinkedHashMap<>();

    public static Map<Integer, RowDataToMap> getPositions() {
        return positions;
    }

    protected static int getNextKeyValue(Map<Integer, RowDataToMap> positions){
        LinkedList<Integer> listKeys = new LinkedList<Integer>(positions.keySet());
        return listKeys.getLast().intValue() + 1;
    }

    protected static int getNextKeyValue(){
        LinkedList<Integer> listKeys = new LinkedList<Integer>(positions.keySet());
        return listKeys.getLast().intValue() + 1;
    }
}
