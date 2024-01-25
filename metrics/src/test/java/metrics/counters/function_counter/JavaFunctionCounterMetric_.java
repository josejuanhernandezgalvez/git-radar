package metrics.counters.function_counter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaFunctionCounterMetric_ {
    @Test
    public void countJavaFunctions() {
        String code = "public class JavaTokenizer implements Tokenizer{\n" +
                "\n" +
                "    private static final Pattern pattern = Pattern.compile(\"[.,;:!?()\\\"<>'\\\\\\\\/\\\\[\\\\]{}]\");\n" +
                "\n" +
                "    @Override\n" +
                "    public List<String> tokenizer(String code) {\n" +
                "        String codeWithoutSpecialSpaces = removeSpecialSpaces(code);\n" +
                "        List<String> tokens = new ArrayList<>();\n" +
                "        Arrays.stream(codeWithoutSpecialSpaces.split(\" \"))\n" +
                "                .forEach(word -> tokens.addAll(separatePunctuation(word)));\n" +
                "        return tokens;\n" +
                "    }\n" +
                "\n" +
                "    private boolean hasPunctuation(String word) {\n" +
                "        Matcher matcher = pattern.matcher(word);\n" +
                "        return matcher.find();\n" +
                "    }\n" +
                "\n" +
                "    private List<String> separatePunctuation(String word) {\n" +
                "        List<String> separatedTokens = new ArrayList<>();\n" +
                "        while (hasPunctuation(word)) word = findPunctuation(word, separatedTokens);\n" +
                "        if (isWordEmpty(word)) separatedTokens.add(word);\n" +
                "        return separatedTokens;\n" +
                "    }\n" +
                "\n" +
                "    private static String findPunctuation(String word, List<String> separatedTokens) {\n" +
                "        Matcher matcher = pattern.matcher(word);\n" +
                "        if (matcher.find()) word = punctuationFound(word, separatedTokens, matcher);\n" +
                "        return word;\n" +
                "    }\n" +
                "\n" +
                "    private static String punctuationFound(String word, List<String> separatedTokens, Matcher matcher) {\n" +
                "        separatedTokens.add(matcher.group());\n" +
                "        return word.replaceFirst(Pattern.quote(matcher.group()), \"\");\n" +
                "    }\n" +
                "\n" +
                "    private boolean isWordEmpty(String word){\n" +
                "        return !word.isEmpty();\n" +
                "    }\n" +
                "\n" +
                "    public String removeSpecialSpaces(String code) {\n" +
                "        return code.replaceAll(\"[\\\\t\\\\n\\\\r\\\\f\\\\v&&[^ ]]\", \"\");\n" +
                "    }\n" +
                "}";

        int expectedFunctions = 7;
        JavaFunctionCounterMetric javaFunctionCounterMetric = new JavaFunctionCounterMetric();
        int functions = javaFunctionCounterMetric.countFunctions(code);
        assertEquals(expectedFunctions, functions);
    }
}
