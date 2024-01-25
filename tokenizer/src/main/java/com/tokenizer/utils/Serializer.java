package com.tokenizer.utils;

import com.tokenizer.context.TokenizedCode;

public interface Serializer {
    String serialize(TokenizedCode tokenizedCode);
}
