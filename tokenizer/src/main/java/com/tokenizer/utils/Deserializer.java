package com.tokenizer.utils;

import com.tokenizer.context.SourceCode;

public interface Deserializer {
    SourceCode deserialize(String jsonString);
}
