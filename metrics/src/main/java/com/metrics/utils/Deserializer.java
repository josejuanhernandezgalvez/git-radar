package com.metrics.utils;

import com.metrics.SourceCode;

public interface Deserializer {
    SourceCode deserialize(String jsonString);
}
