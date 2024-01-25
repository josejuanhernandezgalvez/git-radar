package metrics.counters.function_counter;

public class PythonFunctionCounterMetric implements FunctionCounterMetric{
    private static final String FUNCTION_MARKER = "def ";

    @Override
    public int countFunctions(String code) {
        return code.split(FUNCTION_MARKER).length - 1;
    }
}
