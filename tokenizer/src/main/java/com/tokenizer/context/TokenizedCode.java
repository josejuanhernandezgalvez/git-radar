package com.tokenizer.context;

import java.util.List;

public class TokenizedCode {
    public final long ts;
    public final List<String> tokenizedCode;

    public TokenizedCode(List<String> tokenized, long currentTimeMillis) {
        this.tokenizedCode = tokenized;
        this.ts = currentTimeMillis;
    }

    public List<String> getTokenizedCode() {
        return tokenizedCode;
    }

    public long getTimeStamp() {
        return ts;
    }
}
