package com.builder;

import java.util.List;

public class TokenizedCode {
    private long ts;
    private List<String> tokenizedCode;

    public TokenizedCode(List<String> tokenized, long currentTimeMillis) {
        this.tokenizedCode = tokenized;
        this.ts = currentTimeMillis;
    }

    public List<String> getTokenizedCode() {
        return tokenizedCode;
    }

    public void setName(String[] tokenizedCode) {
        this.tokenizedCode = List.of(tokenizedCode);
    }

    public long getTimeStamp() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }
}
