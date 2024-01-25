package com.tokenizer.context.tokenizer;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class TokenizerInitializer {

    public static Tokenizer initializeTokenizer(String fileName) {
        String fileExtension = getFileExtension(fileName);
        TokenizerMapper tokenizerRegistry = new TokenizerMapper();
        Class<? extends Tokenizer> tokenizerClass = tokenizerRegistry.getObjectClass(fileExtension);
        return (tokenizerClass != null) ? createTokenizerInstance(tokenizerClass, fileExtension) : null;
    }

    private static Tokenizer createTokenizerInstance(Class<? extends Tokenizer> tokenizerClass, String fileExtension) {
        try {
            return tokenizerClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Error creating tokenizer for extension: " + fileExtension, e);
        }
    }

    private static String getFileExtension(String fileName) {
        Optional<Integer> dotIndex = Optional.of(fileName.lastIndexOf('.'))
                .filter(index -> index != -1 && index != fileName.length() - 1);

        return dotIndex.map(index -> fileName.substring(index + 1))
                .orElseThrow(() -> new IllegalArgumentException("Invalid file name: " + fileName));
    }
}
