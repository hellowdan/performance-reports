package org.benchmarks.definitions.operators;

import org.benchmarks.definitions.ReportSheetPositions;
import org.benchmarks.definitions.RowDataToMap;

public class OperatorsSheetPositions extends ReportSheetPositions {

    static {
        positions.put(735, new RowDataToMap("benchmark=org.drools.benchmarks.operators.AccumulateBenchmark.test|rulesAndFactsNumber=2","Accumulate Test With 2 Rules And Facts"));
        positions.put(736, new RowDataToMap("benchmark=org.drools.benchmarks.operators.AccumulateBenchmark.test|rulesAndFactsNumber=8","Accumulate Test With 8 Rules And Facts"));
        positions.put(737, new RowDataToMap("benchmark=org.drools.benchmarks.operators.AccumulateBenchmark.test|rulesAndFactsNumber=32","Accumulate Test With 32 Rules And Facts"));
        positions.put(738, new RowDataToMap("benchmark=org.drools.benchmarks.operators.BasicOperatorsBenchmark.test|rulesAndFactsNumber=2","Basic Operators Test With 2 Rules And Facts"));
        positions.put(739, new RowDataToMap("benchmark=org.drools.benchmarks.operators.BasicOperatorsBenchmark.test|rulesAndFactsNumber=8","Basic Operators Test With 8 Rules And Facts"));
        positions.put(740, new RowDataToMap("benchmark=org.drools.benchmarks.operators.BasicOperatorsBenchmark.test|rulesAndFactsNumber=32","Basic Operators Test With 32 Rules And Facts"));
        positions.put(741, new RowDataToMap("benchmark=org.drools.benchmarks.operators.EvalBenchmark.test|rulesAndFactsNumber=2","Eval Test With 2 Rules And Facts"));
        positions.put(742, new RowDataToMap("benchmark=org.drools.benchmarks.operators.EvalBenchmark.test|rulesAndFactsNumber=8","Eval Test With 8 Rules And Facts"));
        positions.put(743, new RowDataToMap("benchmark=org.drools.benchmarks.operators.EvalBenchmark.test|rulesAndFactsNumber=32","Eval Test With 32 Rules And Facts"));
        positions.put(744, new RowDataToMap("benchmark=org.drools.benchmarks.operators.ExistsBenchmark.test|rulesAndFactsNumber=2","Exists Test With 2 Rules And Facts"));
        positions.put(745, new RowDataToMap("benchmark=org.drools.benchmarks.operators.ExistsBenchmark.test|rulesAndFactsNumber=8","Exists Test With 8 Rules And Facts"));
        positions.put(746, new RowDataToMap("benchmark=org.drools.benchmarks.operators.ExistsBenchmark.test|rulesAndFactsNumber=32","Exists Test With 32 Rules And Facts"));
    }

    public OperatorsSheetPositions() {
    }
}
