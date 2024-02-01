package com.metrics.codeanalysis.languages;

import com.metrics.codeanalysis.MetricsAnalyzer;

import java.util.Arrays;

public class PythonMetricsAnalyzer extends MetricsAnalyzer {
    private static final String FUNCTION_MARKER = "def ";

    @Override
    public int countFunctions(String code) {
        return code.split(FUNCTION_MARKER).length - 1;
    }

    @Override
    public int countImports(String code) {
        return (int) Arrays.stream(code.split("\n"))
                .map(String::trim)
                .filter(line -> line.startsWith("import") || line.startsWith("from "))
                .count();
    }
}
