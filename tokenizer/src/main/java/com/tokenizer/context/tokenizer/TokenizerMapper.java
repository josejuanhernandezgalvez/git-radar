package com.tokenizer.context.tokenizer;

import com.tokenizer.context.tokenizer.languages.JavaTokenizer;
import com.tokenizer.context.tokenizer.languages.PythonTokenizer;

import java.util.HashMap;
import java.util.Map;

public class TokenizerMapper {

    private static final Map<String, Class<? extends Tokenizer>> TOKENIZER_MAP;

    static {
        TOKENIZER_MAP = new HashMap<>();
        initializeTokenizerMap();
    }

    private static void initializeTokenizerMap() {
        TOKENIZER_MAP.put("java", JavaTokenizer.class);
        TOKENIZER_MAP.put("py", PythonTokenizer.class);
    }

    public Class<? extends Tokenizer> getObjectClass(String fileExtension) {
        return TOKENIZER_MAP.get(fileExtension.toLowerCase());
    }
}
