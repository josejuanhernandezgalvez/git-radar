package com.suggester;

import com.suggester.api.Api;
import com.suggester.api.TokenSuggesterApi;

public class Main {
    public static void main(String[] args) {
        Api api = new TokenSuggesterApi();
        api.start();
    }
}

