package com.metrics.codeanalysis;

public class SourceCodeMetrics {
    private Metrics metrics;
    private long ts;

    public SourceCodeMetrics(Metrics metrics, long ts) {
        this.metrics = metrics;
        this.ts = ts;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public long getTimestamp() {
        return ts;
    }
}
