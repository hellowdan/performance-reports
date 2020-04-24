package org.benchmarks.data.session;

import org.benchmarks.data.ReportData;
import org.benchmarks.data.ReportRow;
import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.definitions.session.SessionSourceFileColumns;
import org.benchmarks.util.ReportProperties;
import org.json.simple.JSONObject;

public class SessionReportData extends ReportData {

    public SessionReportData() {
        super();
    }

    @Override
    protected ReportRow parseReportRow(JSONObject jenkinsReportRow) {
        SessionReportRow sessionReportRow = new SessionReportRow();

        if (jenkinsReportRow.get(SessionSourceFileColumns.BENCHMARK) != null) {
            sessionReportRow.setBenchmark(jenkinsReportRow.get(SessionSourceFileColumns.BENCHMARK).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.ASYNC.getColumn()) != null) {
            sessionReportRow.setAsync(jenkinsReportRow.get(SessionSourceFileColumns.ASYNC.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.ASYNC_INSERTS.getColumn()) != null) {
            sessionReportRow.setAsyncInserts(jenkinsReportRow.get(SessionSourceFileColumns.ASYNC_INSERTS.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.BATCH_FIRE.getColumn()) != null) {
            sessionReportRow.setBatchFire(jenkinsReportRow.get(SessionSourceFileColumns.BATCH_FIRE.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.CEP.getColumn()) != null) {
            sessionReportRow.setCep(jenkinsReportRow.get(SessionSourceFileColumns.CEP.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.DO_UPDATE.getColumn()) != null) {
            sessionReportRow.setDoUpdate(jenkinsReportRow.get(SessionSourceFileColumns.DO_UPDATE.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.EXERCISE_SESSION.getColumn()) != null) {
            sessionReportRow.setExerciseSession(jenkinsReportRow.get(SessionSourceFileColumns.EXERCISE_SESSION.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.FACTS_NR.getColumn()) != null) {
            sessionReportRow.setFactsNr(jenkinsReportRow.get(SessionSourceFileColumns.FACTS_NR.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.INITIAL_SESSION_POOL_SIZE.getColumn()) != null) {
            sessionReportRow.setInitialSessionPoolSize(jenkinsReportRow.get(SessionSourceFileColumns.INITIAL_SESSION_POOL_SIZE.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.JOINS_NR.getColumn()) != null) {
            sessionReportRow.setJoinsNr(jenkinsReportRow.get(SessionSourceFileColumns.JOINS_NR.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.LOOP_COUNT.getColumn()) != null) {
            sessionReportRow.setLoopCount(jenkinsReportRow.get(SessionSourceFileColumns.LOOP_COUNT.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.MULTITHREAD.getColumn()) != null) {
            sessionReportRow.setMultithread(jenkinsReportRow.get(SessionSourceFileColumns.MULTITHREAD.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.NUMBER_OF_FACTS.getColumn()) != null) {
            sessionReportRow.setNumberOfFacts(jenkinsReportRow.get(SessionSourceFileColumns.NUMBER_OF_FACTS.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.NUMBER_OF_RULES.getColumn()) != null) {
            sessionReportRow.setNumberOfRules(jenkinsReportRow.get(SessionSourceFileColumns.NUMBER_OF_RULES.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.RULE_LINKED.getColumn()) != null) {
            sessionReportRow.setRulelinked(jenkinsReportRow.get(SessionSourceFileColumns.RULE_LINKED.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.RULES_NR.getColumn()) != null) {
            sessionReportRow.setRulesNr(jenkinsReportRow.get(SessionSourceFileColumns.RULES_NR.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.TREES_NR.getColumn()) != null) {
            sessionReportRow.setTreesNr(jenkinsReportRow.get(SessionSourceFileColumns.TREES_NR.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.TYPES_NR.getColumn()) != null) {
            sessionReportRow.setTypesNr(jenkinsReportRow.get(SessionSourceFileColumns.TYPES_NR.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.USE_CANONICAL_MODEL.getColumn()) != null) {
            sessionReportRow.setUseCanonicalModel(jenkinsReportRow.get(SessionSourceFileColumns.USE_CANONICAL_MODEL.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.USE_NOT_EXISTING_FIELD.getColumn()) != null) {
            sessionReportRow.setUseNotExistingField(jenkinsReportRow.get(SessionSourceFileColumns.USE_NOT_EXISTING_FIELD.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.USE_POOL.getColumn()) != null) {
            sessionReportRow.setUsePool(jenkinsReportRow.get(SessionSourceFileColumns.USE_POOL.getColumn()).toString());
        }

        if (jenkinsReportRow.get(SessionSourceFileColumns.SCORE) != null) {
            sessionReportRow.setScore(jenkinsReportRow.get(SessionSourceFileColumns.SCORE).toString());
        }

        return sessionReportRow;
    }

    @Override
    public String getDataSourcePath(StaticVersion staticVersion, ReportProperties reportProperties) {
        DroolsReportProperties droolsProperties = (DroolsReportProperties) reportProperties;

        if (staticVersion == StaticVersion.NEXT) {
            return droolsProperties.getNextVersionSessionPath();
        } else if (staticVersion == StaticVersion.CURRENT) {
            return droolsProperties.getCurrentVersionSessionPath();
        } else if (staticVersion == StaticVersion.PREVIOUS) {
            return droolsProperties.getPreviousVersionSessionPath();
        } else if (staticVersion == StaticVersion.OLDER) {
            return droolsProperties.getOlderVersionSessionPath();
        } else {
            return "";
        }
    }
}
