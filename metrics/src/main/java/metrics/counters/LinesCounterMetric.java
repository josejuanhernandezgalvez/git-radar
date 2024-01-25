package metrics.counters;

public class LinesCounterMetric {

    private static final String LINE_SEPARATOR = "\n";

    public int countLines(String code) {
        return code.split(LINE_SEPARATOR).length;
    }
}