package org.benchmarks.definitions.operators;

import org.benchmarks.definitions.ReportSheetPositions;
import org.benchmarks.definitions.RowDataToMap;
import org.benchmarks.definitions.cep.CEPMultithreadedSheetPositions;
import org.benchmarks.definitions.oopath.OopathSheetPositions;
import org.benchmarks.definitions.turtle.runtime.RuntimeMultithreadedSheetPositions;

public final class OperatorsSheetPositions extends ReportSheetPositions {

    static {
        positions.put(getFirstKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.operators.AccumulateBenchmark.test|rulesAndFactsNumber=2","Accumulate Test With 2 Rules And Facts"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.operators.AccumulateBenchmark.test|rulesAndFactsNumber=8","Accumulate Test With 8 Rules And Facts"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.operators.AccumulateBenchmark.test|rulesAndFactsNumber=32","Accumulate Test With 32 Rules And Facts"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.operators.BasicOperatorsBenchmark.test|rulesAndFactsNumber=2","Basic Operators Test With 2 Rules And Facts"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.operators.BasicOperatorsBenchmark.test|rulesAndFactsNumber=8","Basic Operators Test With 8 Rules And Facts"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.operators.BasicOperatorsBenchmark.test|rulesAndFactsNumber=32","Basic Operators Test With 32 Rules And Facts"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.operators.EvalBenchmark.test|rulesAndFactsNumber=2","Eval Test With 2 Rules And Facts"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.operators.EvalBenchmark.test|rulesAndFactsNumber=8","Eval Test With 8 Rules And Facts"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.operators.EvalBenchmark.test|rulesAndFactsNumber=32","Eval Test With 32 Rules And Facts"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.operators.ExistsBenchmark.test|rulesAndFactsNumber=2","Exists Test With 2 Rules And Facts"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.operators.ExistsBenchmark.test|rulesAndFactsNumber=8","Exists Test With 8 Rules And Facts"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.operators.ExistsBenchmark.test|rulesAndFactsNumber=32","Exists Test With 32 Rules And Facts"));
    }

    private static int getFirstKeyValue()
    {
        OopathSheetPositions oopathSheetPositions = new OopathSheetPositions();
        return getNextKeyValue(oopathSheetPositions.positions);
    }

    public OperatorsSheetPositions() {
    }
}
