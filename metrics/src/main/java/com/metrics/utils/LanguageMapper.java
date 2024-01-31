package com.metrics.utils;

import com.metrics.codeanalysis.MetricsAnalyzer;
import com.metrics.codeanalysis.languages.JavaMetricsAnalyzer;
import com.metrics.codeanalysis.languages.PythonMetricsAnalyzer;


import java.util.HashMap;
import java.util.Map;

public class LanguageMapper implements Mapper {
    private static final Map<String, Class<? extends MetricsAnalyzer>> map = createMap();

    private static HashMap<String, Class<? extends MetricsAnalyzer>> createMap() {
        HashMap<String, Class<? extends MetricsAnalyzer>> map = new HashMap<>();
        map.put("java", JavaMetricsAnalyzer.class);
        map.put("py", PythonMetricsAnalyzer.class);
        return map;
    }

    @Override
    public Class<? extends MetricsAnalyzer> getLanguageClass(String language) {
        return map.get(language);
    }
}
