package org.benchmarks.reports.data;

public enum FileExtension {
    CSV("csv"),
    JSON("json");

    private String extension;

    FileExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public static FileExtension getExtension(String extension) {
        if(extension.equals(CSV.toString())){
            return FileExtension.CSV;
        } else if (extension.equals(JSON.toString())){
            return FileExtension.JSON;
        } else return null;
    }
}
