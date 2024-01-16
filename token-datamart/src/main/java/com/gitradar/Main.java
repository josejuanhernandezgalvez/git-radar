package com.gitradar;

import com.gitradar.service.apis.TokenSuggesterWebService;
import com.gitradar.service.managers.DatabaseTokenManager;
import com.gitradar.storage.databaseviews.DynamoDatabaseView;

public class Main {
    public static void main(String[] args) {
        DynamoDatabaseView databaseView = new DynamoDatabaseView.Builder()
                .in("eu-central-2")
                .port(4566)
                .ip("http://host.docker.internal")
                .build();
        DatabaseTokenManager suggester = new DatabaseTokenManager(databaseView);
        new TokenSuggesterWebService(suggester)
                .at(8080)
                .start();
    }
}
