package com.gitradar;

import com.gitradar.service.suggester.TokenSuggester;
import com.gitradar.storage.dabaseviews.DynamoDatabaseView;

public class Main {
    public static void main(String[] args) {
        DynamoDatabaseView databaseView = new DynamoDatabaseView.Builder()
                .in("eu-central-2")
                .port(4566)
                .ip("http://localhost")
                .build();
        TokenSuggester service = new TokenSuggester(databaseView);
    }
}
