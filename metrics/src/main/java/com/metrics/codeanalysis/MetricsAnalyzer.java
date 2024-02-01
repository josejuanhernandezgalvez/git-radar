package com.metrics.codeanalysis;

import java.util.Arrays;

public abstract class MetricsAnalyzer {
    private static final String LINE_SEPARATOR = "\n";


    public Metrics analyzeMetrics(String code) {
        return new Metrics(countLines(code),
                           calculateMaxIndentation(code),
                           countFunctions(code), countImports(code));
    }


    private int countLines(String code) {
        return code.split(LINE_SEPARATOR).length;
    }

    public int calculateMaxIndentation(String code) {
        return Arrays.stream(code.split(LINE_SEPARATOR))
                     .filter(line -> !isLineEmpty(line))
                     .mapToInt(this::getIdentationLevel)
                     .filter(this::processIndentation)
                     .max()
                     .orElse(0) / 4;
    }

    private boolean processIndentation(int indentation) {
        return indentation > 0;
    }

    private int getIdentationLevel(String line) {
        return line.length() - line.replaceAll("^\\s+", "").length();
    }

    private boolean isLineEmpty(String line) {
        return line.trim().isEmpty();
    }

    public abstract int countFunctions(String code);

    public abstract int countImports(String code);

}
