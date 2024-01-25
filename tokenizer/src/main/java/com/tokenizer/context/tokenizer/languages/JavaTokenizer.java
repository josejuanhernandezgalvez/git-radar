package com.tokenizer.context.tokenizer.languages;

import com.tokenizer.context.tokenizer.Tokenizer;

public class JavaTokenizer extends Tokenizer {

    @Override
    public String removeComments(String code) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        int n = code.length();
        boolean insideLineComment = false;
        boolean insideBlockComment = false;

        while (i < n) {
            char currentChar = code.charAt(i);

            insideLineComment |= !insideBlockComment && currentChar == '/' && i + 1 < n && code.charAt(i + 1) == '/';
            insideBlockComment |= !insideLineComment && currentChar == '/' && i + 1 < n && code.charAt(i + 1) == '*';

            result.append(!insideLineComment && !insideBlockComment ? currentChar : "");

            insideLineComment &= !(insideLineComment && currentChar == '\n');
            insideBlockComment &= !(insideBlockComment && currentChar == '*' && i + 1 < n && code.charAt(i + 1) == '/');

            i++;
        }
        return result.toString();
    }

}




