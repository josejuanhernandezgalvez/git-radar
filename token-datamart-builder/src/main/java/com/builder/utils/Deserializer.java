package com.builder.utils;

import com.builder.TokenizedCode;

public interface Deserializer {

    TokenizedCode deserialize(String jsonCode);
}
