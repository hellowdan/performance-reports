package org.benchmarks.definitions;

import java.util.HashMap;

public class SheetPositionsTest {

    public static HashMap<Integer, String> droolsSheetPositions = new HashMap<>();

    public static HashMap<Integer, String> droolsBuildtimeSheetPositions = new HashMap<>();

    public static HashMap<Integer, String> droolsRuntimeSheetPositions = new HashMap<>();

    static {
        droolsSheetPositions.put(2, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDTablesBenchmark.timeKBaseCreationFromOneBigAndOneSmallDTable|numberOfRules=|nrOfRules=|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        droolsSheetPositions.put(3, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDrlBenchmark.timeKBaseCreationFromDrl|numberOfRules=|nrOfRules=1k|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        droolsSheetPositions.put(4, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDrlBenchmark.timeKBaseCreationFromDrl|numberOfRules=|nrOfRules=5k|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        droolsSheetPositions.put(5, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDrlBenchmark.timeKBaseCreationFromDrl|numberOfRules=|nrOfRules=10k|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        droolsSheetPositions.put(6, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDslAndDslrBenchmark.timeBaseCreationFromSimpleDslrAndDsl|numberOfRules=|nrOfRules=|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        droolsSheetPositions.put(7, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromMultipleResourcesBenchmark.timeKBaseCreationFromMultipleResources|numberOfRules=|nrOfRules=|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        droolsSheetPositions.put(8, "name=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=1000|nrOfRules=|useCanonicalModel=true|rulesProviderId=|matchRatio=null");
        droolsSheetPositions.put(9, "name=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=1000|nrOfRules=|useCanonicalModel=false|rulesProviderId=|matchRatio=null");
        droolsSheetPositions.put(10,"name=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=2000|nrOfRules=|useCanonicalModel=true|rulesProviderId=|matchRatio=null");
        droolsSheetPositions.put(11,"name=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=2000|nrOfRules=|useCanonicalModel=false|rulesProviderId=|matchRatio=null");
        droolsSheetPositions.put(12,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=100|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsSheetPositions.put(13,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=100|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsSheetPositions.put(14,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=500|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsSheetPositions.put(15,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=500|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsSheetPositions.put(16,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=1000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsSheetPositions.put(17,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=1000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsSheetPositions.put(18,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=3000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsSheetPositions.put(19,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=3000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsSheetPositions.put(20,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=5000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsSheetPositions.put(21,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=5000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsSheetPositions.put(22,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=10000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsSheetPositions.put(23,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=10000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");

        droolsSheetPositions.put(24,"name=org.drools.benchmarks.turtle.runtime.StandardOperatorsExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsSheetPositions.put(25,"name=org.drools.benchmarks.turtle.runtime.AdvOperatorsExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsSheetPositions.put(26,"name=org.drools.benchmarks.turtle.runtime.AdvOperators2ExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsSheetPositions.put(27,"name=org.drools.benchmarks.turtle.runtime.AdvOperators3ExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsSheetPositions.put(28,"name=org.drools.benchmarks.turtle.runtime.AdvOperators4ExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");

        droolsSheetPositions.put(29,"name=org.drools.benchmarks.turtle.runtime.AfterBeforeFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsSheetPositions.put(30,"name=org.drools.benchmarks.turtle.runtime.CoincidesDuringFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsSheetPositions.put(31,"name=org.drools.benchmarks.turtle.runtime.FinishesFinishedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsSheetPositions.put(32,"name=org.drools.benchmarks.turtle.runtime.MeetsMetbyFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsSheetPositions.put(33,"name=org.drools.benchmarks.turtle.runtime.OverlapsOverlappedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsSheetPositions.put(34,"name=org.drools.benchmarks.turtle.runtime.StartsStartedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");

        droolsSheetPositions.put(35,"name=org.drools.benchmarks.turtle.runtime.InsertFactsIntoWmExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsSheetPositions.put(36,"name=org.drools.benchmarks.turtle.runtime.InsertLogicalFactsIntoWmExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsSheetPositions.put(37,"name=org.drools.benchmarks.turtle.runtime.RetractFactsFromWmExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsSheetPositions.put(38,"name=org.drools.benchmarks.turtle.runtime.UpdateFactsInWmExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsSheetPositions.put(39,"name=org.drools.benchmarks.turtle.runtime.WmManipulationExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");

        droolsSheetPositions.put(40,"name=org.drools.benchmarks.turtle.runtime.SimpleDummyFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsSheetPositions.put(41,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=0.0");
        droolsSheetPositions.put(42,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=0.5");
        droolsSheetPositions.put(43,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=1.0");
        droolsSheetPositions.put(44,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=0.0");
        droolsSheetPositions.put(45,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=0.5");
        droolsSheetPositions.put(46,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=1.0");
    }

    static {
        droolsBuildtimeSheetPositions.put(2, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDTablesBenchmark.timeKBaseCreationFromOneBigAndOneSmallDTable|numberOfRules=|nrOfRules=|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        droolsBuildtimeSheetPositions.put(3, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDrlBenchmark.timeKBaseCreationFromDrl|numberOfRules=|nrOfRules=1k|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        droolsBuildtimeSheetPositions.put(4, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDrlBenchmark.timeKBaseCreationFromDrl|numberOfRules=|nrOfRules=5k|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        droolsBuildtimeSheetPositions.put(5, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDrlBenchmark.timeKBaseCreationFromDrl|numberOfRules=|nrOfRules=10k|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        droolsBuildtimeSheetPositions.put(6, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDslAndDslrBenchmark.timeBaseCreationFromSimpleDslrAndDsl|numberOfRules=|nrOfRules=|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        droolsBuildtimeSheetPositions.put(7, "name=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromMultipleResourcesBenchmark.timeKBaseCreationFromMultipleResources|numberOfRules=|nrOfRules=|useCanonicalModel=|rulesProviderId=|matchRatio=null");
        droolsBuildtimeSheetPositions.put(8, "name=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=1000|nrOfRules=|useCanonicalModel=true|rulesProviderId=|matchRatio=null");
        droolsBuildtimeSheetPositions.put(9, "name=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=1000|nrOfRules=|useCanonicalModel=false|rulesProviderId=|matchRatio=null");
        droolsBuildtimeSheetPositions.put(10,"name=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=2000|nrOfRules=|useCanonicalModel=true|rulesProviderId=|matchRatio=null");
        droolsBuildtimeSheetPositions.put(11,"name=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=2000|nrOfRules=|useCanonicalModel=false|rulesProviderId=|matchRatio=null");
        droolsBuildtimeSheetPositions.put(12,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=100|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsBuildtimeSheetPositions.put(13,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=100|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsBuildtimeSheetPositions.put(14,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=500|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsBuildtimeSheetPositions.put(15,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=500|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsBuildtimeSheetPositions.put(16,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=1000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsBuildtimeSheetPositions.put(17,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=1000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsBuildtimeSheetPositions.put(18,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=3000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsBuildtimeSheetPositions.put(19,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=3000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsBuildtimeSheetPositions.put(20,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=5000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsBuildtimeSheetPositions.put(21,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=5000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsBuildtimeSheetPositions.put(22,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=10000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");
        droolsBuildtimeSheetPositions.put(23,"name=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=10000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider|matchRatio=null");

    }

    static {
        droolsRuntimeSheetPositions.put(24,"name=org.drools.benchmarks.turtle.runtime.StandardOperatorsExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsRuntimeSheetPositions.put(25,"name=org.drools.benchmarks.turtle.runtime.AdvOperatorsExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsRuntimeSheetPositions.put(26,"name=org.drools.benchmarks.turtle.runtime.AdvOperators2ExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsRuntimeSheetPositions.put(27,"name=org.drools.benchmarks.turtle.runtime.AdvOperators3ExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsRuntimeSheetPositions.put(28,"name=org.drools.benchmarks.turtle.runtime.AdvOperators4ExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");

        droolsRuntimeSheetPositions.put(29,"name=org.drools.benchmarks.turtle.runtime.AfterBeforeFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsRuntimeSheetPositions.put(30,"name=org.drools.benchmarks.turtle.runtime.CoincidesDuringFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsRuntimeSheetPositions.put(31,"name=org.drools.benchmarks.turtle.runtime.FinishesFinishedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsRuntimeSheetPositions.put(32,"name=org.drools.benchmarks.turtle.runtime.MeetsMetbyFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsRuntimeSheetPositions.put(33,"name=org.drools.benchmarks.turtle.runtime.OverlapsOverlappedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsRuntimeSheetPositions.put(34,"name=org.drools.benchmarks.turtle.runtime.StartsStartedbyFusionBenchmark.timeEventsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");

        droolsRuntimeSheetPositions.put(35,"name=org.drools.benchmarks.turtle.runtime.InsertFactsIntoWmExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsRuntimeSheetPositions.put(36,"name=org.drools.benchmarks.turtle.runtime.InsertLogicalFactsIntoWmExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsRuntimeSheetPositions.put(37,"name=org.drools.benchmarks.turtle.runtime.RetractFactsFromWmExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsRuntimeSheetPositions.put(38,"name=org.drools.benchmarks.turtle.runtime.UpdateFactsInWmExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsRuntimeSheetPositions.put(39,"name=org.drools.benchmarks.turtle.runtime.WmManipulationExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");

        droolsRuntimeSheetPositions.put(40,"name=org.drools.benchmarks.turtle.runtime.SimpleDummyFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiring|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=");
        droolsRuntimeSheetPositions.put(41,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=0.0");
        droolsRuntimeSheetPositions.put(42,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=0.5");
        droolsRuntimeSheetPositions.put(43,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateful|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=1.0");
        droolsRuntimeSheetPositions.put(44,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=0.0");
        droolsRuntimeSheetPositions.put(45,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=0.5");
        droolsRuntimeSheetPositions.put(46,"name=org.drools.benchmarks.turtle.runtime.SimpleRealFactsMatchRatioExpertBenchmark.timeFactsInsertionAndRulesFiringStateless|numberOfRules=null|nrOfRules=null|useCanonicalModel=null|rulesProviderId=null|matchRatio=1.0");
    }
}