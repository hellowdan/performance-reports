package org.benchmarks.drools.definitions;

import java.util.HashMap;
import java.util.Map;

public class DroolsSheetPositions {

    public static Map<Integer, String> getPositions() {
        return positions;
    }

    private static final Map<Integer, String> positions = new HashMap<>();

    static {
        positions.put(2, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDTablesBenchmark.timeKBaseCreationFromOneBigAndOneSmallDTable|numberOfRules=|nrOfRules=|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        positions.put(3, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDrlBenchmark.timeKBaseCreationFromDrl|numberOfRules=|nrOfRules=1k|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        positions.put(4, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDrlBenchmark.timeKBaseCreationFromDrl|numberOfRules=|nrOfRules=5k|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        positions.put(5, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDrlBenchmark.timeKBaseCreationFromDrl|numberOfRules=|nrOfRules=10k|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        positions.put(6, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDslAndDslrBenchmark.timeBaseCreationFromSimpleDslrAndDsl|numberOfRules=|nrOfRules=|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        positions.put(7, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromMultipleResourcesBenchmark.timeKBaseCreationFromMultipleResources|numberOfRules=|nrOfRules=|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        positions.put(8, "name=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=1000|nrOfRules=|useCanonicalModel=true|rulesProviderId=|matchRatio=null");
        positions.put(9, "name=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=1000|nrOfRules=|useCanonicalModel=false|rulesProviderId=|matchRatio=null");
        positions.put(10,"name=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=2000|nrOfRules=|useCanonicalModel=true|rulesProviderId=|matchRatio=null");
        positions.put(11,"name=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=2000|nrOfRules=|useCanonicalModel=false|rulesProviderId=|matchRatio=null");
        positions.put(12,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=100|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        positions.put(13,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=100|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        positions.put(14,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=500|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        positions.put(15,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=500|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        positions.put(16,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=1000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        positions.put(17,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=1000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        positions.put(18,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=3000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        positions.put(19,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=3000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        positions.put(20,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=5000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        positions.put(21,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=5000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        positions.put(22,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=10000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        positions.put(23,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=10000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");

        positions.put(24,"name=org.drools.benchmarks.turtle.runtime.StandardOperatorsExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        positions.put(25,"name=org.drools.benchmarks.turtle.runtime.AdvOperatorsExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        positions.put(26,"name=org.drools.benchmarks.turtle.runtime.AdvOperators2ExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        positions.put(27,"name=org.drools.benchmarks.turtle.runtime.AdvOperators3ExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        positions.put(28,"name=org.drools.benchmarks.turtle.runtime.AdvOperators4ExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");

        positions.put(29,"name=org.drools.benchmarks.turtle.runtime.AfterBeforeFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        positions.put(30,"name=org.drools.benchmarks.turtle.runtime.CoincidesDuringFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        positions.put(31,"name=org.drools.benchmarks.turtle.runtime.FinishesFinishedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        positions.put(32,"name=org.drools.benchmarks.turtle.runtime.MeetsMetbyFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        positions.put(33,"name=org.drools.benchmarks.turtle.runtime.OverlapsOverlappedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        positions.put(34,"name=org.drools.benchmarks.turtle.runtime.StartsStartedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");

        positions.put(35,"name=org.drools.benchmarks.turtle.runtime.InsertFactsIntoWmExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        positions.put(36,"name=org.drools.benchmarks.turtle.runtime.InsertLogicalFactsIntoWmExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        positions.put(37,"name=org.drools.benchmarks.turtle.runtime.RetractFactsFromWmExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        positions.put(38,"name=org.drools.benchmarks.turtle.runtime.UpdateFactsInWmExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        positions.put(39,"name=org.drools.benchmarks.turtle.runtime.WmManipulationExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");

        positions.put(40,"name=org.drools.benchmarks.turtle.runtime.SimpleDummyFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        positions.put(41,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=0.0");
        positions.put(42,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=0.5");
        positions.put(43,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=1.0");
        positions.put(44,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=0.0");
        positions.put(45,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=0.5");
        positions.put(46,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=1.0");
    }















































    private DroolsSheetPositions() {
    }
}