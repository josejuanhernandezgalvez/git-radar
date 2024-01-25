package metrics.counters.import_counter;

import java.util.Arrays;

public class PythonImportCounterMetric {
    public int countPythonImports(String code) {
        return (int) Arrays.stream(code.split("\n"))
                .map(String::trim)
                .filter(line -> line.startsWith("import") || line.startsWith("from "))
                .count();
    }
}
