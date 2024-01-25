package metrics;

import metrics.counters.LinesCounterMetric;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinesCounterMetric_ {
    @Test
    public void countLinesTest() {
        String code = "package metrics;\n" +
                "\n" +
                "public class LinesCounterMetrics {\n" +
                "\n" +
                "    private static final String LINE_SEPARATOR = \"\\n\";\n" +
                "\n" +
                "    public int countLines(String code) {\n" +
                "        return code.split(LINE_SEPARATOR).length;\n" +
                "    }\n" +
                "}";
        int expectedLines = 10;
        LinesCounterMetric linesCounterMetrics = new LinesCounterMetric();
        int lines = linesCounterMetrics.countLines(code);
        assertEquals(expectedLines, lines);
    }
}
