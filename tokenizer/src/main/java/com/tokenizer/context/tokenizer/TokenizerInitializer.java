package com.tokenizer.context.tokenizer;

import java.util.Optional;

public class TokenizerInitializer {

    public static Tokenizer initialize(String fileName) {
        String fileExtension = getFileExtension(fileName);
        Class<? extends Tokenizer> tokenizerClass = new TokenizerMapper().getObjectClass(fileExtension);
        return (tokenizerClass != null) ? createTokenizerInstance(tokenizerClass, fileExtension) : null;
    }

    private static Tokenizer createTokenizerInstance(Class<? extends Tokenizer> tokenizerClass, String fileExtension) {
        try {
            return tokenizerClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error creating tokenizer for extension: " + fileExtension, e);
        }
    }

    private static String getFileExtension(String fileName) {
        return Optional.of(fileName.lastIndexOf('.'))
                       .filter(index -> isAnAllowedFormat(fileName, index))
                       .map(index -> fileName.substring(index + 1))
                       .orElseThrow(() -> new IllegalArgumentException("Invalid file name: " + fileName));
    }

    private static boolean isAnAllowedFormat(String fileName, Integer index) {
        return index != -1 && index != fileName.length() - 1;
    }
}
