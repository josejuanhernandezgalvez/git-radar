package com.tokenizer.utils;

import com.google.gson.Gson;
import com.tokenizer.context.TokenizedCode;

public class TokenizedCodeSerializer implements Serializer{
    private final Gson gson = new Gson();

    @Override
    public String serialize(TokenizedCode tokenizedCode) {
        try {
            return gson.toJson(tokenizedCode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
