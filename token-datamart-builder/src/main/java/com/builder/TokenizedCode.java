package com.builder;

import java.util.List;

public class TokenizedCode {
    private long timestamp;
    private List<String> tokenizedCode;

    public TokenizedCode(List<String> tokenized, long currentTimeMillis) {
        this.tokenizedCode = tokenized;
        this.timestamp = currentTimeMillis;
    }

    public List<String> getTokenizedCode() { return tokenizedCode; }

    public void setName(String[] tokenizedCode) { this.tokenizedCode = List.of(tokenizedCode); }

    public long getTimeStamp() { return timestamp; }

    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
