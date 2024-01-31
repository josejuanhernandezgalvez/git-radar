package com.metrics.utils;

import com.google.gson.Gson;
import com.metrics.SourceCode;

public class SourceCodeDeserializer implements Deserializer {
    private final Gson gson = new Gson();

    @Override
    public SourceCode deserialize(String jsonString) {
        try {
            return gson.fromJson(jsonString, SourceCode.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
