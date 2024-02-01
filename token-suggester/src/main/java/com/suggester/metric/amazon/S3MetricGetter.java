package com.suggester.metric.amazon;

import com.suggester.metric.MetricGetter;
import com.suggester.metric.MetricJsonProcessor;

public class S3MetricGetter implements MetricGetter {

    private static final String BUCKET_NAME = "gitradar-metrics";
    private static final String FILE_EXTENSION = ".json";
    private final MetricJsonProcessor processor;
    private final String file;
    private final String metric;
    private final S3 getter;

    private S3MetricGetter(String file, String metric) {
        this.processor = new MetricJsonProcessor(metric);
        this.getter = new S3();
        this.file = file;
        this.metric = metric;
    }

    public static S3MetricGetter with(String file, String metric){
        return new S3MetricGetter(file, metric);
    }

    public String get() {
        return processor.process(getter.getMetricWith(BUCKET_NAME, buildKey()));
    }

    private String buildKey() {
        return file + FILE_EXTENSION;
    }
}
