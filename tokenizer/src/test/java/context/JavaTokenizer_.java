package context;

import com.tokenizer.context.tokenizer.languages.JavaTokenizer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JavaTokenizer_ {

    @Test
    public void tokenizer_test() {
        String code = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(sequence);\n" +
                "    }\n" +
                "}";

        JavaTokenizer javaTokenizer = new JavaTokenizer();

        List<String> expectedTokens =  List.of(
                "public", "class", "Main", "{",
                "public", "static", "void", "main", "(", "String", "[", "]", "args", ")", "{",
                "System", ".", "out", ".", "println", "(", "sequence", ")", ";",
                "}", "}"
        );

        List<String> tokenized = javaTokenizer.tokenize(code);
        assertEquals(expectedTokens, tokenized);
    }

}
