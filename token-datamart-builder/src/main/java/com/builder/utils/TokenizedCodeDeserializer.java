package com.builder.utils;

import com.builder.TokenizedCode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenizedCodeDeserializer implements Deserializer {
    @Override
    public TokenizedCode deserialize(String jsonCode) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonCode, TokenizedCode.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
