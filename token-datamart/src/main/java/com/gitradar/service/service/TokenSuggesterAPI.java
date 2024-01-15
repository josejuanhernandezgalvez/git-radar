package com.gitradar.service.service;

public interface TokenSuggesterAPI {
    void start();

    interface ResponseSerializer {
        String serialize(Record record);
    }
}
