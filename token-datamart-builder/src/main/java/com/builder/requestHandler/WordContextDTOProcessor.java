package com.builder.requestHandler;

import java.util.List;

public class WordContextDTOProcessor {

    public static WordContextDTO processWordContext(List<String> tokens, int windowSize, int index) {
        if (shouldSkipProcessing(tokens, windowSize, index)) {
            return null;
        }

        return new WordContextDTO(
                createContext(tokens, index, windowSize),
                createNextWord(tokens, index, windowSize));
    }

    private static boolean shouldSkipProcessing(List<String> tokens, int windowSize, int index) {
        return index + windowSize > tokens.size();
    }

    private static String createContext(List<String> tokens, int startIndex, int windowSize) {
        return String.join(" ", tokens.subList(startIndex, Math.min(startIndex + windowSize, tokens.size())));
    }

    private static String createNextWord(List<String> tokens, int startIndex, int windowSize) {
        if (isEndOfSequence(tokens, startIndex, windowSize)) return "EOS";
        return tokens.get(startIndex + windowSize);

    }

    private static boolean isEndOfSequence(List<String> tokens, int startIndex, int windowSize) {
        return startIndex + windowSize >= tokens.size();
    }
}

