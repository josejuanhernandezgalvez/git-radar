package com.metrics.codeanalysis.languages;

import com.metrics.codeanalysis.MetricsAnalyzer;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaMetricsAnalyzer extends MetricsAnalyzer {
    private static final Pattern pattern = Pattern.compile("\\s*(public|private|protected|default)\\s*\\w*\\s+.+\\s+\\w+\\s*\\(.*\\)\\s*\\{\n");

    @Override
    public int countFunctions(String code) {
        return (int) pattern.matcher(code).results().count();
    }

    @Override
    public int countImports(String code) {
        return (int) Arrays.stream(code.split("\n"))
                .map(String::trim)
                .filter(line -> line.startsWith("import"))
                .count();
    }
}
