package com.gitradar.service;

public interface TokenManager {
    WordContextDTO get(String context);
    WordContextDTO post(String context, String nextWord) throws Exception;

}
