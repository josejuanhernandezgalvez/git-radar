package metrics.utils;

import metrics.counters.function_counter.FunctionCounterMetric;
import metrics.counters.function_counter.JavaFunctionCounterMetric;
import metrics.counters.function_counter.PythonFunctionCounterMetric;

import java.util.HashMap;
import java.util.Map;

public class LanguageMapper implements Mapper {
    private static final Map<String, Class<? extends FunctionCounterMetric>> languageMappings = new HashMap<>();

    static {
        languageMappings.put("java", JavaFunctionCounterMetric.class);
        languageMappings.put("python", PythonFunctionCounterMetric.class);
    }

    @Override
    public Class<? extends FunctionCounterMetric> getLanguageClass(String language) {
        return languageMappings.get(language);
    }
}
