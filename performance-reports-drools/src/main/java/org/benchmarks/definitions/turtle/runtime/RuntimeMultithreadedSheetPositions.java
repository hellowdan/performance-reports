package org.benchmarks.definitions.turtle.runtime;

import java.util.HashMap;
import java.util.Map;

import org.benchmarks.definitions.RowDataToMap;

public class RuntimeMultithreadedSheetPositions {

    private static final Map<Integer, RowDataToMap> positions = new HashMap<>();

    static {
        positions.put(747,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.StandardOperatorsExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Standard Expert operators"));
        positions.put(748,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperatorsExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Advanced Expert operators"));
        positions.put(749,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperators2ExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Advanced Expert operators 2"));
        positions.put(750,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperators3ExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Advanced Expert operators 3"));
        positions.put(751,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperators4ExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Advanced Expert operators 4"));

        positions.put(752,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AfterBeforeFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'after' and 'before'"));
        positions.put(753,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.CoincidesDuringFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'coincides' and 'during'"));
        positions.put(754,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.FinishesFinishedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'finishes' and 'finishedby'"));
        positions.put(755,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.MeetsMetbyFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'meets' and 'metby'"));
        positions.put(756,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.OverlapsOverlappedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'overlaps' and 'overlappedby'"));
        positions.put(757,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.StartsStartedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|matchRatio=",
                                          "Fusion operators 'starts' and 'startedby'"));

        positions.put(758,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.InsertFactsIntoWmExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic insert facts into working memory"));
        positions.put(759,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.InsertLogicalFactsIntoWmExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic insert logical facts into working memory"));
        positions.put(760,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.RetractFactsFromWmExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic retract facts from working memory"));
        positions.put(761,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.UpdateFactsInWmExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic update facts in working memory"));
        positions.put(762,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.WmManipulationExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic working memory manipulation"));

        positions.put(763,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleDummyFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiring|matchRatio=",
                                          "Basic facts match ratio 0 % - dummy facts"));
        positions.put(764,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|matchRatio=0.0",
                                          "Basic facts match ratio 0 % - real facts"));
        positions.put(765,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|matchRatio=0.5",
                                          "Basic facts match ratio 50 % - real facts"));
        positions.put(766,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|matchRatio=1.0",
                                          "Basic facts match ratio 100 % - real facts"));
        positions.put(767,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|matchRatio=0.0",
                                          "Basic facts match ratio 0 % - real facts – stateless"));
        positions.put(768,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|matchRatio=0.5",
                                          "Basic facts match ratio 50 % - real facts – stateless"));
        positions.put(769,new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|matchRatio=1.0",
                                          "Basic facts match ratio 100 % - real facts – stateless"));
    }

    private RuntimeMultithreadedSheetPositions() {
    }

    public static Map<Integer, RowDataToMap> getPositions() {
        return positions;
    }
}
