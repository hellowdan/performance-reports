package org.benchmarks.definitions.turtle.buildtime;

import org.benchmarks.definitions.ReportSheetPositions;
import org.benchmarks.definitions.RowDataToMap;

public class BuildtimeSheetPositions extends ReportSheetPositions {

    static {
        positions.put(2, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDTablesBenchmark.timeKBaseCreationFromOneBigAndOneSmallDTable|numberOfRules=|nrOfRules=|useCanonicalModel=|rulesProviderId=",
                                          "Kbase creation from decision tables"));
        positions.put(3, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDrlBenchmark.timeKBaseCreationFromDrl|numberOfRules=|nrOfRules=1k|useCanonicalModel=|rulesProviderId=",
                                          "Kbase creation from DRL (1k)"));
        positions.put(4, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDrlBenchmark.timeKBaseCreationFromDrl|numberOfRules=|nrOfRules=5k|useCanonicalModel=|rulesProviderId=",
                                          "Kbase creation from DRL (5k)"));
        positions.put(5, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDrlBenchmark.timeKBaseCreationFromDrl|numberOfRules=|nrOfRules=10k|useCanonicalModel=|rulesProviderId=",
                                          "Kbase creation from DRL (10k)"));
        positions.put(6, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromDslAndDslrBenchmark.timeBaseCreationFromSimpleDslrAndDsl|numberOfRules=|nrOfRules=|useCanonicalModel=|rulesProviderId=",
                                          "Kbase creation from DSL/DSLR"));
        positions.put(7, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.KBaseCreationFromMultipleResourcesBenchmark.timeKBaseCreationFromMultipleResources|numberOfRules=|nrOfRules=|useCanonicalModel=|rulesProviderId=",
                                          "Kbase creation from Multiple resources"));
        positions.put(8, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=1000|nrOfRules=|useCanonicalModel=true|rulesProviderId=",
                                          "Build KJar from DRL resource – 1000 rules, with model"));
        positions.put(9, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=1000|nrOfRules=|useCanonicalModel=false|rulesProviderId=",
                                          "Build KJar from DRL resource – 1000 rules, without model"));
        positions.put(10, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=2000|nrOfRules=|useCanonicalModel=true|rulesProviderId=",
                                           "Build KJar from DRL resource – 2000 rules, with model"));
        positions.put(11, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKJarFromResourceBenchmark.createKJarFromResource|numberOfRules=2000|nrOfRules=|useCanonicalModel=false|rulesProviderId=",
                                           "Build KJar from DRL resource – 2000 rules, without model"));
        positions.put(12, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=100|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider",
                                           "Create KieContainer and KieBase from KJar – 100 rules, with model"));
        positions.put(13, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=100|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider",
                                           "Create KieContainer and KieBase from KJar – 100 rules, without model"));
        positions.put(14, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=500|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider",
                                           "Create KieContainer and KieBase from KJar – 500 rules, with model"));
        positions.put(15, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=500|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider",
                                           "Create KieContainer and KieBase from KJar – 500 rules, without model"));
        positions.put(16, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=1000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider",
                                           "Create KieContainer and KieBase from KJar – 1000 rules, with model"));
        positions.put(17, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=1000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider",
                                           "Create KieContainer and KieBase from KJar – 1000 rules, without model"));
        positions.put(18, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=3000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider",
                                           "Create KieContainer and KieBase from KJar – 3000 rules, with model"));
        positions.put(19, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=3000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider",
                                           "Create KieContainer and KieBase from KJar – 3000 rules, without model"));
        positions.put(20, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=5000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider",
                                           "Create KieContainer and KieBase from KJar – 5000 rules, with model"));
        positions.put(21, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=5000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider",
                                           "Create KieContainer and KieBase from KJar – 5000 rules, without model"));
        positions.put(22, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=10000|nrOfRules=|useCanonicalModel=true|rulesProviderId=RulesWithJoinsProvider",
                                           "Create KieContainer and KieBase from KJar – 10000 rules, with model"));
        positions.put(23, new RowDataToMap("benchmark=org.drools.benchmarks.turtle.buildtime.BuildKieBaseFromContainerBenchmark.getKieBaseFromContainer|numberOfRules=10000|nrOfRules=|useCanonicalModel=false|rulesProviderId=RulesWithJoinsProvider",
                                           "Create KieContainer and KieBase from KJar – 10000 rules, without model"));
    }

    public BuildtimeSheetPositions() {
    }

}
