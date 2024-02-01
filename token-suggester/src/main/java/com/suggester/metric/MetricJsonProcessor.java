package com.suggester.metric;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.suggester.api.JsonProcessor;

public class MetricJsonProcessor implements JsonProcessor {

    private static final String METRICS_FIELD_NAME = "metrics";
    private static final String ALL_METRICS_NAME = "all";
    private final String metric;

    public MetricJsonProcessor(String metric) {
        this.metric = metric;
    }

    @Override
    public String process(String json){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonObject metricObject = jsonObject.getAsJsonObject(METRICS_FIELD_NAME);
        if(metric.equals(ALL_METRICS_NAME)) return metricObject.toString();
        return metricObject.get(metric).getAsString();
    }
}
