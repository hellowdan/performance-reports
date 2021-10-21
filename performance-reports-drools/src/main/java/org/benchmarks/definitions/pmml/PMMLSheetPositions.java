package org.benchmarks.definitions.pmml;

import org.benchmarks.definitions.ReportSheetPositions;
import org.benchmarks.definitions.RowDataToMap;
import org.benchmarks.definitions.session.SessionSheetPositions;

public final class PMMLSheetPositions extends ReportSheetPositions {

    static {

        positions.put(getFirstKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.pmml.runtime.clustering.PMMLEvaluateSingleIrisKMeansClusteringBenchmark.evaluatePrediction",
                                                          "Evaluate Single IrisK Means Clustering"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.pmml.runtime.mining.PMMLEvaluateRandomForestBenchmark.evaluatePrediction",
                                                         "Evaluate Random Forest"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.pmml.runtime.regression.PMMLEvaluateLinearRegressionSampleWithTransformationsBenchmark.evaluatePrediction",
                                                         "Evaluate Linear Regression Sample With Transformations"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.pmml.runtime.scorecard.PMMLEvaluateSimpleScorecardWithTransformationsBenchmark.evaluatePrediction",
                                                         "Evaluate Simple Scorecard With Transformations"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.pmml.runtime.tree.PMMLEvaluateSampleMineTreeModelWithTransformationsBenchmark.evaluatePrediction",
                                                         "Evaluate Sample Mine Tree Model With Transformations"));
    }

    private static int getFirstKeyValue(){
        SessionSheetPositions sessionSheetPositions = new SessionSheetPositions();
        return getNextKeyValue(sessionSheetPositions.positions);
    }

    public PMMLSheetPositions() {
    }
}
