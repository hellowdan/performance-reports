package org.benchmarks.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.benchmarks.definitions.DroolsReportProperties;
import org.benchmarks.definitions.SheetPositions;
import org.benchmarks.definitions.SourceFileExtension;
import org.benchmarks.definitions.SourceFileLocation;
import org.benchmarks.definitions.ReportType;
import org.benchmarks.definitions.StaticVersion;
import org.benchmarks.model.BenchmarkDashboardEntity;
import org.benchmarks.util.ReportProperties;

public class BenchmarkDashboardData {

    private BenchmarkDashboardEntity getDroolsJenkinsReportRowDB(DroolsReportRow testResultData, ReportType reportType, Integer key, StaticVersion staticVersion, String version) {
        return new BenchmarkDashboardEntity(
                testResultData.getName(),
                SheetPositions.getFancyNames().get(key),
                testResultData.getNumberOfRules(),
                testResultData.getNrOfRules(),
                testResultData.getUseCanonicalModel(),
                testResultData.getRulesProviderId(),
                testResultData.getMatchRatio(),
                testResultData.getScore(),
                reportType,
                staticVersion,
                version
        );
    }

    public List<BenchmarkDashboardEntity> getDroolsBuildtimeData(SourceFileExtension sourceFileExtension, StaticVersion staticVersion, String version, SourceFileLocation reportLocation, ReportProperties propertiesLoader) throws IOException {
        DroolsReportProperties droolsReportProperties = (DroolsReportProperties) propertiesLoader;
        BuildtimeReportData droolsBuildtimeJenkinsReport = new BuildtimeReportData();
        String buildtimePath = droolsBuildtimeJenkinsReport.getDataSourcePath(staticVersion, droolsReportProperties);
        List<DroolsReportRow> testResultData = droolsBuildtimeJenkinsReport.getData(buildtimePath, sourceFileExtension, reportLocation);
        List<BenchmarkDashboardEntity> DroolsJenkinsReportRows = new ArrayList();

        for (Integer key : SheetPositions.getPositions().keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(SheetPositions.getPositions().get(key))) {
                    DroolsJenkinsReportRows.add(getDroolsJenkinsReportRowDB(testResultData.get(i), ReportType.BUILDTIME, key, staticVersion, version));
                    break;
                }
            }
        }

        return DroolsJenkinsReportRows;
    }

    public List<BenchmarkDashboardEntity> getDroolsRuntimeData(SourceFileExtension sourceFileExtension, StaticVersion staticVersion, String version, SourceFileLocation reportLocation, ReportProperties propertiesLoader) throws IOException {
        DroolsReportProperties droolsReportProperties = (DroolsReportProperties) propertiesLoader;
        RuntimeReportData droolsRuntimeJenkinsReport = new RuntimeReportData();
        String runtimePath = droolsRuntimeJenkinsReport.getDataSourcePath(staticVersion, droolsReportProperties);
        List<DroolsReportRow> testResultData = droolsRuntimeJenkinsReport.getData(runtimePath, sourceFileExtension, reportLocation);
        List<BenchmarkDashboardEntity> DroolsJenkinsReportRows = new ArrayList();

        for (Integer key : SheetPositions.getPositions().keySet()) {
            for (int i = 0; i < testResultData.size(); i++) {
                if (testResultData.get(i).getUniqueID().equals(SheetPositions.getPositions().get(key))) {
                    DroolsJenkinsReportRows.add(getDroolsJenkinsReportRowDB(testResultData.get(i), ReportType.RUNTIME, key, staticVersion, version));
                    break;
                }
            }
        }

        return DroolsJenkinsReportRows;
    }
}
