package org.benchmarks.commons.definitions;

public enum JenkinsReportLocation {
    LOCAL("LOCAL"),
    WEB("WEB"),
    DRIVE("DRIVE"),
    CLASSPATH("CLASSPATH");

    private String location;

    JenkinsReportLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public static JenkinsReportLocation getLocation(String location) {
        if(location.equals(LOCAL.toString())){
            return JenkinsReportLocation.LOCAL;
        } else if (location.equals(WEB.toString())){
            return JenkinsReportLocation.WEB;
        } else if (location.equals(DRIVE.toString())){
            return JenkinsReportLocation.DRIVE;
        } else if (location.equals(CLASSPATH.toString())){
            return JenkinsReportLocation.CLASSPATH;
        } else return null;
    }
}
