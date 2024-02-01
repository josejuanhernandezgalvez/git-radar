package com.suggester.token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class CodeTokenGetter implements TokenGetter {


    private static final String HOSTNAME = "http://token-datamart:8080/token/next/";
    private static final String WORDS_DELIMITER = ",";
    private final NextTokenJsonProcessor jsonProcessor;
    List<String> words;

    private CodeTokenGetter(List<String> words) {
        this.jsonProcessor = new NextTokenJsonProcessor();
        this.words = words;
    }

    public static CodeTokenGetter with(List<String> words){
        return new CodeTokenGetter(words);
    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) response.append(line);
            return response.toString();
        }
    }

    @Override
    public String getNextToken(){
        try {
            HttpURLConnection connection = this.establishConnection(createURL());
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) return null;
            return jsonProcessor.process(getResponse(connection));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URL createURL() throws MalformedURLException {
        return new URL(HOSTNAME + joinWords());
    }

    private String joinWords() {
        return String.join(WORDS_DELIMITER, this.words);
    }

    private HttpURLConnection establishConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;

    }
}
