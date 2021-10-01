package org.benchmarks.definitions;

public enum DroolsDocumentsToGenerate {
    BRE("BRE"),
    DMN("DMN");

    private String droolsDocumentsToGenerate;

    DroolsDocumentsToGenerate(String droolsDocumentsToGenerate) {
        this.droolsDocumentsToGenerate = droolsDocumentsToGenerate;
    }

    public String getDocumentsToGenerate() {
        return droolsDocumentsToGenerate;
    }
}
