package org.benchmarks.reports.metadata;

public abstract class SpreadSheetReportChartMetadata {

    protected String ID;
    protected String chartUrl;
    protected String chartTitle;
    protected String tabID;
    protected String tabUrl;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getChartUrl() {
        return chartUrl;
    }

    public void setChartUrl(String chartUrl) {
        chartUrl = chartUrl;
    }

    public String getChartTitle() {
        return chartTitle;
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
    }

    public String getTabID() {
        return tabID;
    }

    public void setTabID(String tabID) {
        this.tabID = tabID;
    }

    public String getTabUrl() {
        return tabUrl;
    }

    public void setTabUrl(String tabUrl) {
        this.tabUrl = tabUrl;
    }
}
