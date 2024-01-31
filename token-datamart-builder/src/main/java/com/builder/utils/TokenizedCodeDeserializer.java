package com.builder.utils;

import com.builder.TokenizedCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class TokenizedCodeDeserializer implements Deserializer {
    private final Gson gson = new Gson();

    @Override
    public TokenizedCode deserialize(String json) {
        try {
            return gson.fromJson(json, TokenizedCode.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
