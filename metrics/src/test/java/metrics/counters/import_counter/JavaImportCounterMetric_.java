package metrics.counters.import_counter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaImportCounterMetric_ {
    @Test
    public void countImportsTest() {
        String code = "package tokenizer;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.Arrays;\n" +
                "import java.util.List;\n" +
                "import java.util.regex.Matcher;\n" +
                "import java.util.regex.Pattern;\n" +
                "\n" +
                "public class JavaTokenizer implements Tokenizer{\n";

        int expectedImports = 5;
        JavaImportCounterMetric javaImportCounterMetric = new JavaImportCounterMetric();
        int importCounter = javaImportCounterMetric.countImports(code);
        assertEquals(importCounter, expectedImports);
    }
}
