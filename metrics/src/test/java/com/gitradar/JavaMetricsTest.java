package com.gitradar;

import com.metrics.codeanalysis.languages.JavaMetricsAnalyzer;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaMetricsTest {

    private static String javaCode;

    static {
        String filePath = "src/main/java/com/metrics/S3Service.java";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            StringBuilder code = new StringBuilder();
            while ((linea = br.readLine()) != null) code.append(linea).append("\n");
            javaCode = code.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final int N_IMPORTS = 11;
    private static final int N_LINES = 68;
    private static final int N_FUNCTIONS = 6;
    private static final int MAX_INDENTATION = 4;

    @Test
    public void should_test_counter_import_metric() {
        int imports = new JavaMetricsAnalyzer().analyzeMetrics(javaCode).getImports();
        assertThat(imports).isEqualTo(N_IMPORTS);
    }

    @Test
    public void should_test_counter_lines_metric() {
        int lines = new JavaMetricsAnalyzer().analyzeMetrics(javaCode).getLines();
        assertThat(lines).isEqualTo(N_LINES);
    }

    @Test
    public void should_test_counter_functions_metric() {
        int functions = new JavaMetricsAnalyzer().analyzeMetrics(javaCode).getFunctions();
        assertThat(functions).isEqualTo(N_FUNCTIONS);
    }

    @Test
    public void should_test_counter_indentations_metric() {
        int maxIndentation = new JavaMetricsAnalyzer().analyzeMetrics(javaCode).getMaxIndentation();
        assertThat(maxIndentation).isEqualTo(MAX_INDENTATION);
    }
}
