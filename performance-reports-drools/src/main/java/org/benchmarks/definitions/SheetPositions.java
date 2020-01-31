package org.benchmarks.definitions;

import java.util.HashMap;
import java.util.Map;

public class SheetPositions {

    public static Map<Integer, String> getPositions() {
        return positions;
    }

    public static Map<Integer, String> getFancyNames() {
        return fancyNames;
    }

    private static final Map<Integer, String> positions = new HashMap<>();

    private static final Map<Integer, String> fancyNames = new HashMap<>();

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

    static {
        fancyNames.put(2, "Kbase creation from decision tables");
        fancyNames.put(3, "Kbase creation from DRL (1k)");
        fancyNames.put(4, "Kbase creation from DRL (5k)");
        fancyNames.put(5, "Kbase creation from DRL (10k)");
        fancyNames.put(6, "Kbase creation from DSL/DSLR");
        fancyNames.put(7, "Kbase creation from Multiple resources");
        fancyNames.put(8, "Build KJar from DRL resource – 1000 rules, with model");
        fancyNames.put(9, "Build KJar from DRL resource – 1000 rules, without model");
        fancyNames.put(10,"Build KJar from DRL resource – 2000 rules, with model");
        fancyNames.put(11,"Build KJar from DRL resource – 2000 rules, without model");
        fancyNames.put(12,"Create KieContainer and KieBase from KJar – 100 rules, with model");
        fancyNames.put(13,"Create KieContainer and KieBase from KJar – 100 rules, without model");
        fancyNames.put(14,"Create KieContainer and KieBase from KJar – 500 rules, with model");
        fancyNames.put(15,"Create KieContainer and KieBase from KJar – 500 rules, without model");
        fancyNames.put(16,"Create KieContainer and KieBase from KJar – 1000 rules, with model");
        fancyNames.put(17,"Create KieContainer and KieBase from KJar – 1000 rules, without model");
        fancyNames.put(18,"Create KieContainer and KieBase from KJar – 3000 rules, with model");
        fancyNames.put(19,"Create KieContainer and KieBase from KJar – 3000 rules, without model");
        fancyNames.put(20,"Create KieContainer and KieBase from KJar – 5000 rules, with model");
        fancyNames.put(21,"Create KieContainer and KieBase from KJar – 5000 rules, without model");
        fancyNames.put(22,"Create KieContainer and KieBase from KJar – 10000 rules, with model");
        fancyNames.put(23,"Create KieContainer and KieBase from KJar – 10000 rules, without model");

        fancyNames.put(24,"Standard Expert operators");
        fancyNames.put(25,"Advanced Expert operators");
        fancyNames.put(26,"Advanced Expert operators 2");
        fancyNames.put(27,"Advanced Expert operators 3");
        fancyNames.put(28,"Advanced Expert operators 4");

        fancyNames.put(29,"Fusion operators 'after' and 'before'");
        fancyNames.put(30,"Fusion operators 'coincides' and 'during'");
        fancyNames.put(31,"Fusion operators 'finishes' and 'finishedby'");
        fancyNames.put(32,"Fusion operators 'meets' and 'metby'");
        fancyNames.put(33,"Fusion operators 'overlaps' and 'overlappedby'");
        fancyNames.put(34,"Fusion operators 'starts' and 'startedby'");

        fancyNames.put(35,"Basic insert facts into working memory");
        fancyNames.put(36,"Basic insert logical facts into working memory");
        fancyNames.put(37,"Basic retract facts from working memory");
        fancyNames.put(38,"Basic update facts in working memory");
        fancyNames.put(39,"Basic working memory manipulation");

        fancyNames.put(40,"Basic facts match ratio 0 % - dummy facts");
        fancyNames.put(41,"Basic facts match ratio 0 % - real facts");
        fancyNames.put(42,"Basic facts match ratio 50 % - real facts");
        fancyNames.put(43,"Basic facts match ratio 100 % - real facts");
        fancyNames.put(44,"Basic facts match ratio 0 % - real facts – stateless");
        fancyNames.put(45,"Basic facts match ratio 50 % - real facts – stateless");
        fancyNames.put(46,"Basic facts match ratio 100 % - real facts – stateless");
    }
























































































    private SheetPositions() {
    }
}