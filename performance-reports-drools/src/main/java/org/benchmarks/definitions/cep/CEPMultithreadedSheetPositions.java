package org.benchmarks.definitions.cep;

import org.benchmarks.definitions.ReportSheetPositions;
import org.benchmarks.definitions.RowDataToMap;
import org.benchmarks.definitions.dmn.DMNSheetPositions;

public final class CEPMultithreadedSheetPositions extends ReportSheetPositions {

    static {
        positions.put(getFirstKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.AfterBenchmark.testAfterOperator|rulesAndEventsNumber=8","Test After Operator With 8 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.AfterBenchmark.testAfterOperator|rulesAndEventsNumber=16","Test After Operator With 16 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.AfterBenchmark.testAfterOperator|rulesAndEventsNumber=32","Test After Operator With 32 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.BeforeBenchmark.testBeforeOperator|rulesAndEventsNumber=8","Test Before Operator With 8 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.BeforeBenchmark.testBeforeOperator|rulesAndEventsNumber=16","Test Before Operator With 16 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.BeforeBenchmark.testBeforeOperator|rulesAndEventsNumber=32","Test Before Operator With 32 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.CoincidesBenchmark.testCoincidesOperator|rulesAndEventsNumber=8","Test Coincides Operator With 8 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.CoincidesBenchmark.testCoincidesOperator|rulesAndEventsNumber=16","Test Coincides Operator With 16 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.CoincidesBenchmark.testCoincidesOperator|rulesAndEventsNumber=32","Test Coincides Operator With 32 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.DuringBenchmark.testDuringOperator|rulesAndEventsNumber=8","Test During Operator With 8 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.DuringBenchmark.testDuringOperator|rulesAndEventsNumber=16","Test During Operator With 16 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.DuringBenchmark.testDuringOperator|rulesAndEventsNumber=32","Test During Operator With 32 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.FinishedByBenchmark.testFinishedByOperator|rulesAndEventsNumber=8","Test Finished By Operator With 8 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.FinishedByBenchmark.testFinishedByOperator|rulesAndEventsNumber=16","Test Finished By Operator With 16 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.FinishedByBenchmark.testFinishedByOperator|rulesAndEventsNumber=32","Test Finished By Operator With 32 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.FinishesBenchmark.testFinishesOperator|rulesAndEventsNumber=8","Test Finishes Operator With 8 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.FinishesBenchmark.testFinishesOperator|rulesAndEventsNumber=16","Test Finishes Operator With 16 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.FinishesBenchmark.testFinishesOperator|rulesAndEventsNumber=32","Test Finishes Operator With 32 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.IncludesBenchmark.testIncludesOperator|rulesAndEventsNumber=8","Test Includes Operator With 8 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.IncludesBenchmark.testIncludesOperator|rulesAndEventsNumber=16","Test Includes Operator With 16 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.IncludesBenchmark.testIncludesOperator|rulesAndEventsNumber=32","Test Includes Operator With 32 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.MeetsBenchmark.testMeetsOperator|rulesAndEventsNumber=8","Test Meets Operator With 8 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.MeetsBenchmark.testMeetsOperator|rulesAndEventsNumber=16","Test Meets Operator With 16 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.MeetsBenchmark.testMeetsOperator|rulesAndEventsNumber=32","Test Meets Operator With 32 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.MetByBenchmark.testMetByOperator|rulesAndEventsNumber=8","Test Met By Operator With 8 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.MetByBenchmark.testMetByOperator|rulesAndEventsNumber=16","Test Met By Operator With 16 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.MetByBenchmark.testMetByOperator|rulesAndEventsNumber=32","Test Met By Operator With 32 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.OverlappedByBenchmark.testOverlappedByOperator|rulesAndEventsNumber=8","Test Overlapped By Operator With 8 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.OverlappedByBenchmark.testOverlappedByOperator|rulesAndEventsNumber=16","Test Overlapped By Operator With 16 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.OverlappedByBenchmark.testOverlappedByOperator|rulesAndEventsNumber=32","Test Overlapped By Operator With 32 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.OverlapsBenchmark.testOverlapsOperator|rulesAndEventsNumber=8","Test Overlaps Operator With 8 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.OverlapsBenchmark.testOverlapsOperator|rulesAndEventsNumber=16","Test Overlaps Operator With 16 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.OverlapsBenchmark.testOverlapsOperator|rulesAndEventsNumber=32","Test Overlaps Operator With 32 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.StartedByBenchmark.testStartedByOperator|rulesAndEventsNumber=8","Test Started By Operator With 8 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.StartedByBenchmark.testStartedByOperator|rulesAndEventsNumber=16","Test Started By Operator With 16 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.StartedByBenchmark.testStartedByOperator|rulesAndEventsNumber=32","Test Started By Operator With 32 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.StartsBenchmark.testStartsOperator|rulesAndEventsNumber=8","Test Starts Operator With 8 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.StartsBenchmark.testStartsOperator|rulesAndEventsNumber=16","Test Starts Operator With 16 Events"));
        positions.put(getNextKeyValue(), new RowDataToMap("benchmark=org.drools.benchmarks.cep.StartsBenchmark.testStartsOperator|rulesAndEventsNumber=32","Test Starts Operator With 32 Events"));
    }

    private static int getFirstKeyValue(){
        CEPSheetPositions cepSheetPositions = new CEPSheetPositions();
        return getNextKeyValue(cepSheetPositions.positions);
    }

    public CEPMultithreadedSheetPositions() {
    }
}
