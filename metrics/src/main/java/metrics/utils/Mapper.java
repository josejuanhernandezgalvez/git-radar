package metrics.utils;

import metrics.counters.function_counter.FunctionCounterMetric;

public interface Mapper {
    Class<? extends FunctionCounterMetric> getLanguageClass(String language);
}
