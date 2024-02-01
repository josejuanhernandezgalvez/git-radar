package com.metrics.codeanalysis;

public class Metrics {
    private final int lines;
    private final int maxIndentation;
    private final int functions;
    private final int imports;

    public Metrics(int numberOflines, int maxIndentation, int numberOffunctions, int numberOfimports) {
        this.lines = numberOflines;
        this.maxIndentation = maxIndentation;
        this.functions = numberOffunctions;
        this.imports = numberOfimports;
    }

    public int getLines() {
        return lines;
    }

    public int getMaxIndentation() {
        return maxIndentation;
    }

    public int getFunctions() {
        return functions;
    }

    public int getImports() {
        return imports;
    }
}
