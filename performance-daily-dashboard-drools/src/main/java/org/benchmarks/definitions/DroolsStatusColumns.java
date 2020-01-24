package org.benchmarks.definitions;

    public enum DroolsStatusColumns {
        JOB("displayName"),
        URL("url"),
        LAST_BUILD("lastBuild.number"),
        LAST_SUCCESSFUL_BUILD("lastSuccessfulBuild.number"),
        LAST_FAILED_BUILD("lastFailedBuild.number"),
        LAST_BUILD_STATUS("result"),
        LAST_BUILD_TIMESTAMP("timestamp");

        private String column;

        DroolsStatusColumns(String column) {
            this.column = column;
        }

        public String getColumn() {
            return column;
        }
    }


