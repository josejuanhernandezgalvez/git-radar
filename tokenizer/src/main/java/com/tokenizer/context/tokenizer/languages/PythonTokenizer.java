package com.tokenizer.context.tokenizer.languages;

import com.tokenizer.context.tokenizer.Tokenizer;

public class PythonTokenizer extends Tokenizer {

    @Override
    public String removeComments(String code) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        int n = code.length();
        boolean insideComment = false;
        boolean insideDocstring = false;

        while (i < n) {
            char currentChar = code.charAt(i);
            insideComment |= !insideDocstring && currentChar == '#';
            insideDocstring |= !insideComment && currentChar == '"' && i + 2 < n && code.substring(i + 1, i + 3).equals("\"\"");
            result.append(!insideComment && !insideDocstring ? currentChar : "");
            insideDocstring &= !(insideDocstring && currentChar == '"' && i + 2 < n && code.substring(i + 1, i + 3).equals("\"\""));
            insideComment &= !(insideComment && currentChar == '\n');
            i++;
        }
        return result.toString();
    }
}
