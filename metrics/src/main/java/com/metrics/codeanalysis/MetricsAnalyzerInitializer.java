package com.metrics.codeanalysis;

import com.metrics.utils.LanguageMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class MetricsAnalyzerInitializer {

    public static MetricsAnalyzer initializeMetricAnalyzer(String fileName) {
        String fileExtension = getFileExtension(fileName);
        LanguageMapper languageMapper = new LanguageMapper();
        Class<? extends MetricsAnalyzer> metricsAnalyzerClass = languageMapper.getLanguageClass(fileExtension);
        return (metricsAnalyzerClass != null) ? createMetricsAnalyzerInstance(metricsAnalyzerClass, fileExtension) : null;
    }

    private static MetricsAnalyzer createMetricsAnalyzerInstance(Class<? extends MetricsAnalyzer> metricsAnalyzerClass, String fileExtension) {
        try {
            return metricsAnalyzerClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error creating metricsAnalyzer for extension: " + fileExtension, e);
        }
    }

    private static String getFileExtension(String fileName) {
        Optional<Integer> dotIndex = Optional.of(fileName.lastIndexOf('.'))
                .filter(index -> index != -1 && index != fileName.length() - 1);

        return dotIndex.map(index -> fileName.substring(index + 1))
                .orElseThrow(() -> new IllegalArgumentException("Invalid file name: " + fileName));
    }
}
