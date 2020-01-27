package org.benchmarks.definitions;

    public enum DroolsStatusColumns {
        JOB("fullDisplayName"),
        URL("url"),
        LAST_BUILD("lastBuild"),
        LAST_SUCCESSFUL_BUILD("lastSuccessfulBuild"),
        LAST_FAILED_BUILD("lastFailedBuild"),
        LAST_BUILD_STATUS("result"),
        LAST_BUILD_TIMESTAMP("timestamp"),
        BUILD_NUMBER("number");

        private String column;

        DroolsStatusColumns(String column) {
            this.column = column;
        }

        public String getColumn() {
            return column;
        }
    }


