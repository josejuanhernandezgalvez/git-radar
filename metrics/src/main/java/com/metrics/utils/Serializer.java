package com.metrics.utils;

import com.metrics.codeanalysis.SourceCodeMetrics;

public interface Serializer {
    String serialize(SourceCodeMetrics sourceCodeMetrics);
}
