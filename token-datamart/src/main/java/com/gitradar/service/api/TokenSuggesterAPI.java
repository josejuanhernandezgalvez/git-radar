package com.gitradar.service.api;

public interface TokenSuggesterAPI {
    void start();

    interface ResponseSerializer {
        String serialize(Record record);
    }
}
