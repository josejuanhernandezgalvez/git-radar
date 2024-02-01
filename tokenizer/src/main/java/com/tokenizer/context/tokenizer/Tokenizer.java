package com.tokenizer.context.tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class Tokenizer {

    private static final Pattern pattern = Pattern.compile("[.,;:=+!?()\"<>'\\\\/\\[\\]{}]");

    public List<String> tokenize(String code) {
        List<String> tokens = new ArrayList<>();
        Arrays.stream(clean(code))
                .forEach(word -> tokens.addAll(separatePunctuation(word)));
        return tokens;
    }

    private String[] clean(String code) {
        return removeComments(removeSpecialSpaces(code)).split(" ");
    }

    private boolean hasPunctuation(String word) {
        return pattern.matcher(word).find();
    }

    private List<String> separatePunctuation(String word) {
        List<String> separatedTokens = new ArrayList<>();
        while (hasPunctuation(word)) word = findPunctuation(word, separatedTokens);
        if (isWordEmpty(word)) separatedTokens.add(word);
        return separatedTokens;
    }

    private static String findPunctuation(String word, List<String> separatedTokens) {
        Matcher matcher = pattern.matcher(word);
        if (matcher.find()) word = punctuationFound(word, separatedTokens, matcher);
        return word;
    }

    private static String punctuationFound(String word, List<String> separatedTokens, Matcher matcher) {
        String wordPreviousPunctuation = word.substring(0, matcher.start());
        if(!wordPreviousPunctuation.equals("")) separatedTokens.add(wordPreviousPunctuation);
        separatedTokens.add(matcher.group());
        return word.replaceFirst(Pattern.quote(wordPreviousPunctuation + matcher.group()), "");
    }

    private boolean isWordEmpty(String word){
        return !word.isEmpty();
    }

    public String removeSpecialSpaces(String code) {
        return code.replaceAll("[\\t\\n\\r\\f\\v&&[^ ]]", "");
    }

    public abstract String removeComments(String code);
}
