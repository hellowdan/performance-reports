package org.benchmarks.definitions.turtle.runtime;

import org.benchmarks.definitions.ReportSheetPositions;
import org.benchmarks.definitions.RowDataToMap;
import org.benchmarks.definitions.turtle.buildtime.BuildtimeSheetPositions;

public final class RuntimeSheetPositions extends ReportSheetPositions {

    static {
        positions.put(getFirstKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.StandardOperatorsExpertBenchmark.timeFactsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Standard Expert operators"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperatorsExpertBenchmark.timeFactsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Advanced Expert operators"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperators2ExpertBenchmark.timeFactsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Advanced Expert operators 2"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperators3ExpertBenchmark.timeFactsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Advanced Expert operators 3"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AdvOperators4ExpertBenchmark.timeFactsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Advanced Expert operators 4"));

        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.AfterBeforeFusionBenchmark.timeEventsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Fusion operators 'after' and 'before'"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.CoincidesDuringFusionBenchmark.timeEventsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Fusion operators 'coincides' and 'during'"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.FinishesFinishedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Fusion operators 'finishes' and 'finishedby'"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.MeetsMetbyFusionBenchmark.timeEventsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Fusion operators 'meets' and 'metby'"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.OverlapsOverlappedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Fusion operators 'overlaps' and 'overlappedby'"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.StartsStartedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Fusion operators 'starts' and 'startedby'"));

        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.InsertFactsIntoWmExpertBenchmark.timeFactsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Basic insert facts into working memory"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.InsertLogicalFactsIntoWmExpertBenchmark.timeFactsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Basic insert logical facts into working memory"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.RetractFactsFromWmExpertBenchmark.timeFactsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Basic retract facts from working memory"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.UpdateFactsInWmExpertBenchmark.timeFactsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Basic update facts in working memory"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.WmManipulationExpertBenchmark.timeFactsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Basic working memory manipulation"));

        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleDummyFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiring|joinCount=|matchRatio=|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Basic facts match ratio 0 % - dummy facts"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|joinCount=|matchRatio=0.0|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Basic facts match ratio 0 % - real facts"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|joinCount=|matchRatio=0.5|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Basic facts match ratio 50 % - real facts"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|joinCount=|matchRatio=1.0|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Basic facts match ratio 100 % - real facts"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|joinCount=|matchRatio=0.0|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Basic facts match ratio 0 % - real facts – stateless"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|joinCount=|matchRatio=0.5|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Basic facts match ratio 50 % - real facts – stateless"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|joinCount=|matchRatio=1.0|nestedAccumulates=|objectsPerSegment=|perSegmentUpdate=|segmentCount=",
                                          "Basic facts match ratio 100 % - real facts – stateless"));

        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SubNetworkBenchmark.benchmark|joinCount=2|matchRatio=|nestedAccumulates=true|objectsPerSegment=5|perSegmentUpdate=true|segmentCount=3",
                                                         "Subnetwork - Nested Accumulates - 5 objects per segment - update per segment"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SubNetworkBenchmark.benchmark|joinCount=2|matchRatio=|nestedAccumulates=true|objectsPerSegment=5|perSegmentUpdate=false|segmentCount=3",
                                                         "Subnetwork - Nested Accumulates - 5 objects per segment"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SubNetworkBenchmark.benchmark|joinCount=2|matchRatio=|nestedAccumulates=true|objectsPerSegment=15|perSegmentUpdate=true|segmentCount=3",
                                                         "Subnetwork - Nested Accumulates - 15 objects per segment - update per segment"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SubNetworkBenchmark.benchmark|joinCount=2|matchRatio=|nestedAccumulates=true|objectsPerSegment=15|perSegmentUpdate=false|segmentCount=3",
                                                         "Subnetwork - Nested Accumulates - 15 objects per segment"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SubNetworkBenchmark.benchmark|joinCount=2|matchRatio=|nestedAccumulates=false|objectsPerSegment=5|perSegmentUpdate=true|segmentCount=3",
                                                         "Subnetwork - 5 objects per segment - update per segment"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SubNetworkBenchmark.benchmark|joinCount=2|matchRatio=|nestedAccumulates=false|objectsPerSegment=5|perSegmentUpdate=false|segmentCount=3",
                                                         "Subnetwork - 5 objects per segment"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SubNetworkBenchmark.benchmark|joinCount=2|matchRatio=|nestedAccumulates=false|objectsPerSegment=15|perSegmentUpdate=true|segmentCount=3",
                                                         "Subnetwork - 15 objects per segment - update per segment"));
        positions.put(getNextKeyValue(),new RowDataToMap("benchmark=org.drools.benchmarks.turtle.runtime.SubNetworkBenchmark.benchmark|joinCount=2|matchRatio=|nestedAccumulates=false|objectsPerSegment=15|perSegmentUpdate=false|segmentCount=3",
                                                         "Subnetwork - 15 objects per segment"));
    }

    private static int getFirstKeyValue(){
        BuildtimeSheetPositions buildtimeSheetPositions = new BuildtimeSheetPositions();
        return getNextKeyValue(buildtimeSheetPositions.positions);
    }

    public RuntimeSheetPositions() {
    }
}
