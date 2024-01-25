package com.tokenizer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tokenizer.context.TokenizedCode;

public class TokenizedCodeSerializer implements Serializer {

    @Override
    public String serialize(TokenizedCode tokenizedCode) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(tokenizedCode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
