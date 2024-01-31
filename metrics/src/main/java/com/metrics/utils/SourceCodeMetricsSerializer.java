package com.metrics.utils;

import com.google.gson.Gson;
import com.metrics.codeanalysis.SourceCodeMetrics;

public class SourceCodeMetricsSerializer implements Serializer {
    private final Gson gson = new Gson();

    @Override
    public String serialize(SourceCodeMetrics sourceCodeMetrics) {
        try {
            return gson.toJson(sourceCodeMetrics);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
