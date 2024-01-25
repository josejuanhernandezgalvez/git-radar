package com.tokenizer.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tokenizer.context.SourceCode;

public class SourceCodeDeserializer implements Deserializer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public SourceCode deserialize(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, SourceCode.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
