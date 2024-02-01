package com.gitradar.service;

public interface TokenSuggesterAPI {
    void start();

    interface ResponseSerializer {
        String serialize(Record record);
    }
}
