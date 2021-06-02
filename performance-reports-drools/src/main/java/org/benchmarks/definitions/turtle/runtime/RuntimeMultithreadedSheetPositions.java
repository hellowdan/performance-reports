package org.benchmarks.definitions.turtle.runtime;

import org.benchmarks.definitions.ReportSheetPositions;
import org.benchmarks.definitions.RowDataToMap;
import org.benchmarks.definitions.oopath.OopathSheetPositions;
import org.benchmarks.definitions.operators.OperatorsSheetPositions;
import org.benchmarks.definitions.turtle.buildtime.BuildtimeSheetPositions;

public final class RuntimeMultithreadedSheetPositions extends ReportSheetPositions {

    static {
        positions.put(getFirstKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.StandardOperatorsExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Standard Expert operators"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperatorsExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Advanced Expert operators"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperators2ExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Advanced Expert operators 2"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperators3ExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Advanced Expert operators 3"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperators4ExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Advanced Expert operators 4"));

        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AfterBeforeFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'after' and 'before'"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.CoincidesDuringFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'coincides' and 'during'"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.FinishesFinishedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'finishes' and 'finishedby'"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.MeetsMetbyFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'meets' and 'metby'"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.OverlapsOverlappedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'overlaps' and 'overlappedby'"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.StartsStartedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'starts' and 'startedby'"));

        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.InsertFactsIntoWmExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic insert facts into working memory"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.InsertLogicalFactsIntoWmExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic insert logical facts into working memory"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.RetractFactsFromWmExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic retract facts from working memory"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.UpdateFactsInWmExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic update facts in working memory"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.WmManipulationExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic working memory manipulation"));

        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleDummyFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic facts match ratio 0 % - dummy facts"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|matchRatio=0.0",
                                          "Basic facts match ratio 0 % - real facts"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|matchRatio=0.5",
                                          "Basic facts match ratio 50 % - real facts"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|matchRatio=1.0",
                                          "Basic facts match ratio 100 % - real facts"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|matchRatio=0.0",
                                          "Basic facts match ratio 0 % - real facts – stateless"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|matchRatio=0.5",
                                          "Basic facts match ratio 50 % - real facts – stateless"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|matchRatio=1.0",
                                          "Basic facts match ratio 100 % - real facts – stateless"));
    }

    private static int getFirstKeyValue(){
        OperatorsSheetPositions operatorsSheetPositions = new OperatorsSheetPositions();
        return getNextKeyValue(operatorsSheetPositions.positions);
    }

    public RuntimeMultithreadedSheetPositions() {
    }
}
