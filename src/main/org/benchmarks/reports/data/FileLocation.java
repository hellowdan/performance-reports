package org.benchmarks.reports.data;

public enum FileLocation {
    LOCAL("LOCAL"),
    WEB("WEB"),
    DRIVE("DRIVE"),
    CLASSPATH("CLASSPATH");

    private String location;

    FileLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public static FileLocation getLocation(String location) {
        if(location.equals(LOCAL.toString())){
            return FileLocation.LOCAL;
        } else if (location.equals(WEB.toString())){
            return FileLocation.WEB;
        } else if (location.equals(DRIVE.toString())){
            return FileLocation.DRIVE;
        } else if (location.equals(CLASSPATH.toString())){
            return FileLocation.CLASSPATH;
        } else return null;
    }
}
