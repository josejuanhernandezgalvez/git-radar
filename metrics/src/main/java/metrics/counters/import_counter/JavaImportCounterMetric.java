package metrics.counters.import_counter;

import java.util.Arrays;

public class JavaImportCounterMetric implements ImportCounterMetric{
    @Override
    public int countImports(String code) {
        return (int) Arrays.stream(code.split("\n"))
                .map(String::trim)
                .filter(line -> line.startsWith("import"))
                .count();
    }
}
