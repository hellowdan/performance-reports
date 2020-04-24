package org.benchmarks.definitions.turtle.runtime;

import java.util.HashMap;
import java.util.Map;

import org.benchmarks.definitions.RowDataToMap;

public class RuntimeSheetPositions {

    private static final Map<Integer, RowDataToMap> positions = new HashMap<>();

    static {
        positions.put(24,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.StandardOperatorsExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Standard Expert operators"));
        positions.put(25,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperatorsExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Advanced Expert operators"));
        positions.put(26,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperators2ExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Advanced Expert operators 2"));
        positions.put(27,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperators3ExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Advanced Expert operators 3"));
        positions.put(28,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperators4ExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Advanced Expert operators 4"));

        positions.put(29,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AfterBeforeFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'after' and 'before'"));
        positions.put(30,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.CoincidesDuringFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'coincides' and 'during'"));
        positions.put(31,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.FinishesFinishedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'finishes' and 'finishedby'"));
        positions.put(32,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.MeetsMetbyFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'meets' and 'metby'"));
        positions.put(33,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.OverlapsOverlappedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'overlaps' and 'overlappedby'"));
        positions.put(34,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.StartsStartedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'starts' and 'startedby'"));

        positions.put(35,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.InsertFactsIntoWmExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic insert facts into working memory"));
        positions.put(36,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.InsertLogicalFactsIntoWmExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic insert logical facts into working memory"));
        positions.put(37,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.RetractFactsFromWmExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic retract facts from working memory"));
        positions.put(38,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.UpdateFactsInWmExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic update facts in working memory"));
        positions.put(39,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.WmManipulationExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic working memory manipulation"));

        positions.put(40,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleDummyFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic facts match ratio 0 % - dummy facts"));
        positions.put(41,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|matchRatio=0.0",
                                          "Basic facts match ratio 0 % - real facts"));
        positions.put(42,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|matchRatio=0.5",
                                          "Basic facts match ratio 50 % - real facts"));
        positions.put(43,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|matchRatio=1.0",
                                          "Basic facts match ratio 100 % - real facts"));
        positions.put(44,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|matchRatio=0.0",
                                          "Basic facts match ratio 0 % - real facts – stateless"));
        positions.put(45,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|matchRatio=0.5",
                                          "Basic facts match ratio 50 % - real facts – stateless"));
        positions.put(46,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|matchRatio=1.0",
                                          "Basic facts match ratio 100 % - real facts – stateless"));
    }

    private RuntimeSheetPositions() {
    }

    public static Map<Integer, RowDataToMap> getPositions() {
        return positions;
    }
}
