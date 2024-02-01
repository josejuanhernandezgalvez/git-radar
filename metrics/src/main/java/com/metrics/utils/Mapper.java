package com.metrics.utils;

import com.metrics.codeanalysis.MetricsAnalyzer;

public interface Mapper {
    Class<? extends MetricsAnalyzer> getLanguageClass(String language);
}
