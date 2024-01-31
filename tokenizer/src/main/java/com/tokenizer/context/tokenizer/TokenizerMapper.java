package com.tokenizer.context.tokenizer;

import com.tokenizer.context.tokenizer.languages.JavaTokenizer;
import com.tokenizer.context.tokenizer.languages.PythonTokenizer;

import java.util.HashMap;
import java.util.Map;

public class TokenizerMapper {
    private static final Map<String, Class<? extends Tokenizer>> Map = createMap();

    private static java.util.Map<String, Class<? extends Tokenizer>> createMap() {
        HashMap<String, Class<? extends Tokenizer>> result = new HashMap<>();
        result.put("java", JavaTokenizer.class);
        result.put("py", PythonTokenizer.class);
        return result;
    }


    public Class<? extends Tokenizer> getObjectClass(String fileExtension) {
        return Map.get(fileExtension.toLowerCase());
    }
}
