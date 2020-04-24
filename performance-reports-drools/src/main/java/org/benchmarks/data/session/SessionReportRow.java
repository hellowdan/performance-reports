package org.benchmarks.data.session;

import org.benchmarks.data.ReportRow;

public class SessionReportRow implements ReportRow {

    private String benchmark = "";
    private String fancyName = "";
    private String async = "";
    private String asyncInserts = "";
    private String batchFire = "";
    private String cep = "";
    private String doUpdate = "";
    private String exerciseSession = "";
    private String factsNr = "";
    private String initialSessionPoolSize = "";
    private String joinsNr = "";
    private String loopCount = "";
    private String multithread = "";
    private String numberOfFacts = "";
    private String numberOfRules = "";
    private String rulelinked = "";
    private String rulesNr = "";
    private String treesNr = "";
    private String typesNr = "";
    private String useCanonicalModel = "";
    private String useNotExistingField = "";
    private String usePool = "";

    private String score = "";

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public String getAsync() {
        return async;
    }

    public void setAsync(String async) {
        this.async = async;
    }

    public String getAsyncInserts() {
        return asyncInserts;
    }

    public void setAsyncInserts(String asyncInserts) {
        this.asyncInserts = asyncInserts;
    }

    public String getBatchFire() {
        return batchFire;
    }

    public void setBatchFire(String batchFire) {
        this.batchFire = batchFire;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getDoUpdate() {
        return doUpdate;
    }

    public void setDoUpdate(String doUpdate) {
        this.doUpdate = doUpdate;
    }

    public String getExerciseSession() {
        return exerciseSession;
    }

    public void setExerciseSession(String exerciseSession) {
        this.exerciseSession = exerciseSession;
    }

    public String getFactsNr() {
        return factsNr;
    }

    public void setFactsNr(String factsNr) {
        this.factsNr = factsNr;
    }

    public String getInitialSessionPoolSize() {
        return initialSessionPoolSize;
    }

    public void setInitialSessionPoolSize(String initialSessionPoolSize) {
        this.initialSessionPoolSize = initialSessionPoolSize;
    }

    public String getJoinsNr() {
        return joinsNr;
    }

    public void setJoinsNr(String joinsNr) {
        this.joinsNr = joinsNr;
    }

    public String getLoopCount() {
        return loopCount;
    }

    public void setLoopCount(String loopCount) {
        this.loopCount = loopCount;
    }

    public String getMultithread() {
        return multithread;
    }

    public void setMultithread(String multithread) {
        this.multithread = multithread;
    }

    public String getNumberOfFacts() {
        return numberOfFacts;
    }

    public void setNumberOfFacts(String numberOfFacts) {
        this.numberOfFacts = numberOfFacts;
    }

    public String getNumberOfRules() {
        return numberOfRules;
    }

    public void setNumberOfRules(String numberOfRules) {
        this.numberOfRules = numberOfRules;
    }

    public String getRulelinked() {
        return rulelinked;
    }

    public void setRulelinked(String rulelinked) {
        this.rulelinked = rulelinked;
    }

    public String getRulesNr() {
        return rulesNr;
    }

    public void setRulesNr(String rulesNr) {
        this.rulesNr = rulesNr;
    }

    public String getTreesNr() {
        return treesNr;
    }

    public void setTreesNr(String treesNr) {
        this.treesNr = treesNr;
    }

    public String getTypesNr() {
        return typesNr;
    }

    public void setTypesNr(String typesNr) {
        this.typesNr = typesNr;
    }

    public String getUseCanonicalModel() {
        return useCanonicalModel;
    }

    public void setUseCanonicalModel(String useCanonicalModel) {
        this.useCanonicalModel = useCanonicalModel;
    }

    public String getUseNotExistingField() {
        return useNotExistingField;
    }

    public void setUseNotExistingField(String useNotExistingField) {
        this.useNotExistingField = useNotExistingField;
    }

    public String getUsePool() {
        return usePool;
    }

    public void setUsePool(String usePool) {
        this.usePool = usePool;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String getUniqueID() {
        return "benchmark=" + this.benchmark +
                "|async=" + this.async +
                "|asyncInserts=" + this.asyncInserts +
                "|batchFire=" + this.batchFire +
                "|cep=" + this.cep +
                "|doUpdate=" + this.doUpdate +
                "|exerciseSession=" + this.exerciseSession +
                "|factsNr=" + this.factsNr +
                "|initialSessionPoolSize=" + this.initialSessionPoolSize +
                "|joinsNr=" + this.joinsNr +
                "|loopCount=" + this.loopCount +
                "|multithread=" + this.multithread +
                "|numberOfFacts=" + this.numberOfFacts +
                "|numberOfRules=" + this.numberOfRules +
                "|rulelinked=" + this.rulelinked +
                "|rulesNr=" + this.rulesNr +
                "|treesNr=" + this.treesNr +
                "|typesNr=" + this.typesNr +
                "|useCanonicalModel=" + this.useCanonicalModel +
                "|useNotExistingField=" + this.useNotExistingField +
                "|usePool=" + this.usePool;
    }

    @Override
    public String toString() {
        return "DMNReportRow{" +
                "benchmark='" + this.benchmark + '\'' +
                ", fancyName=" + this.fancyName + '\'' +
                ", async=" + '\'' + this.async + '\'' +
                ", asyncInserts=" + '\'' + this.asyncInserts + '\'' +
                ", batchFire=" + '\'' + this.batchFire + '\'' +
                ", cep=" + '\'' + this.cep + '\'' +
                ", doUpdate=" + '\'' + this.doUpdate + '\'' +
                ", exerciseSession=" + '\'' + this.exerciseSession + '\'' +
                ", factsNr=" + '\'' + this.factsNr + '\'' +
                ", initialSessionPoolSize=" + '\'' + this.initialSessionPoolSize + '\'' +
                ", joinsNr=" + '\'' + this.joinsNr + '\'' +
                ", loopCount=" + '\'' + this.loopCount + '\'' +
                ", multithread=" + '\'' + this.multithread + '\'' +
                ", numberOfFacts=" + '\'' + this.numberOfFacts + '\'' +
                ", numberOfRules=" + '\'' + this.numberOfRules + '\'' +
                ", rulelinked=" + '\'' + this.rulelinked + '\'' +
                ", rulesNr=" + '\'' + this.rulesNr + '\'' +
                ", treesNr=" + '\'' + this.treesNr + '\'' +
                ", typesNr=" + '\'' + this.typesNr + '\'' +
                ", useCanonicalModel=" + '\'' + this.useCanonicalModel + '\'' +
                ", useNotExistingField=" + '\'' + this.useNotExistingField + '\'' +
                ", usePool=" + '\'' + this.usePool + '\'' +
                ", score=" + this.score +
                '}';
    }
}
