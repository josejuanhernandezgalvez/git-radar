package com.gitradar;

import com.gitradar.service.api.TokenSuggesterWebService;
import com.gitradar.service.manager.DatabaseTokenManager;
import com.gitradar.storage.dabaseviews.DynamoDatabaseView;

public class Main {
    public static void main(String[] args) {
        DynamoDatabaseView databaseView = new DynamoDatabaseView.Builder()
                .in("eu-central-2")
                .port(4566)
                .ip("http://localhost")
                .build();
        DatabaseTokenManager suggester = new DatabaseTokenManager(databaseView);
        new TokenSuggesterWebService(suggester)
                .at(8080)
                .start();
    }
}
