package org.benchmarks.definitions;

public class RowDataToMap {

    private String rowId;
    private String rowTitle;

    public RowDataToMap(String rowId, String rowTitle) {
        this.rowId = rowId;
        this.rowTitle = rowTitle;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getRowTitle() {
        return rowTitle;
    }

    public void setRowTitle(String rowTitle) {
        this.rowTitle = rowTitle;
    }
}