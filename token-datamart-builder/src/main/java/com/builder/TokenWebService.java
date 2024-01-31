package com.builder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TokenWebService {

    public final static String API_PATH = "http://token-datamart:8080/token/post/";

    public String post(String context, String nextWord) {
        try {
            return this.establishConnection(createURL(context, nextWord)).getResponseMessage();
        } catch (IOException e) { throw new RuntimeException(e); }
    }

    private URL createURL(String context, String nextWord) throws MalformedURLException {
        return new URL(API_PATH + context + "/" + nextWord);
    }

    private HttpURLConnection establishConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }
}