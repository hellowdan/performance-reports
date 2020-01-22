package org.benchmarks.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.benchmarks.definitions.DroolsPropertiesLoader;
import org.benchmarks.definitions.DroolsSheetPositions;
import org.benchmarks.definitions.JenkinsReportFileExtension;
import org.benchmarks.definitions.JenkinsReportLocation;
import org.benchmarks.definitions.JenkinsReportType;
import org.benchmarks.definitions.JenkinsReportVersion;
import org.benchmarks.model.DroolsJenkinsReportRowEntity;
import org.benchmarks.util.PropertiesLoader;

public class DroolsData {

    private DroolsJenkinsReportRowEntity getDroolsJenkinsReportRowDB(DroolsJenkinsReportRow testResultData, JenkinsReportType jenkinsReportType, Integer key, JenkinsReportVersion jenkinsReportVersion, String version) {
        return new DroolsJenkinsReportRowEntity(
                testResultData.getName(),
                DroolsSheetPositions.getFancyNames().get(key),
                testResultData.getNumberOfRules(),
                testResultData.getNrOfRules(),
                testResultData.getUseCanonicalModel(),
                testResultData.getRulesProviderId(),
                testResultData.getMatchRatio(),
                testResultData.getScore(),
                jenkinsReportType,
                jenkinsReportVersion,
                version
        );
    }

    public List<DroolsJenkinsReportRowEntity> getDroolsBuildtimeData(JenkinsReportFileExtension jenkinsReportFileExtension, JenkinsReportVersion jenkinsReportVersion, String version, JenkinsReportLocation jenkinsReportLocation, PropertiesLoader propertiesLoader) throws IOException {
        DroolsPropertiesLoader droolsPropertiesLoader = (DroolsPropertiesLoader) propertiesLoader;
        DroolsBuildtimeJenkinsReport droolsBuildtimeJenkinsReport = new DroolsBuildtimeJenkinsReport();
        String buildtimePath = droolsBuildtimeJenkinsReport.getDataSourcePath(jenkinsReportVersion, droolsPropertiesLoader);
        List<DroolsJenkinsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(buildtimePath, jenkinsReportFileExtension, jenkinsReportLocation);
        List<DroolsJenkinsReportRowEntity> DroolsJenkinsReportRows = new ArrayList();

        for (Integer key : DroolsSheetPositions.getPositions().keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(DroolsSheetPositions.getPositions().get(key))) {
                    DroolsJenkinsReportRows.add(getDroolsJenkinsReportRowDB(testResultData.get(i), JenkinsReportType.BUILDTIME, key, jenkinsReportVersion, version));
                    break;
                }
            }
        }

        return DroolsJenkinsReportRows;
    }

    public List<DroolsJenkinsReportRowEntity> getDroolsRuntimeData(JenkinsReportFileExtension jenkinsReportFileExtension, JenkinsReportVersion jenkinsReportVersion, String version, JenkinsReportLocation jenkinsReportLocation, PropertiesLoader propertiesLoader) throws IOException {
        DroolsPropertiesLoader droolsPropertiesLoader = (DroolsPropertiesLoader) propertiesLoader;
        DroolsRuntimeJenkinsReport droolsRuntimeJenkinsReport = new DroolsRuntimeJenkinsReport();
        String runtimePath = droolsRuntimeJenkinsReport.getDataSourcePath(jenkinsReportVersion, droolsPropertiesLoader);
        List<DroolsJenkinsReportRow> testResultData = droolsRuntimeJenkinsReport.getData(runtimePath, jenkinsReportFileExtension, jenkinsReportLocation);
        List<DroolsJenkinsReportRowEntity> DroolsJenkinsReportRows = new ArrayList();

        for (Integer key : DroolsSheetPositions.getPositions().keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(DroolsSheetPositions.getPositions().get(key))) {
                    DroolsJenkinsReportRows.add(getDroolsJenkinsReportRowDB(testResultData.get(i), JenkinsReportType.RUNTIME, key, jenkinsReportVersion, version));
                    break;
                }
            }
        }

        return DroolsJenkinsReportRows;
    }
}
