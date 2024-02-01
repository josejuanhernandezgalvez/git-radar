package context;

import com.tokenizer.context.tokenizer.languages.PythonTokenizer;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PythonTokenizer_ {
    @Test
    public void tokenizerTest() {
        String code = "train.Sentiment.replace(to_replace='Extremely Positive', value='Positive',inplace=True)";

        List<String> exectedTokenized = List.of("train", ".", "Sentiment", ".", "replace", "(", "to_replace", "=", "'", "Extremely", "Positive", "'", ",", "value", "=", "'", "Positive", "'", ",", "inplace", "=", "True", ")");
        PythonTokenizer pythonTokenizer = new PythonTokenizer();
        List<String> tokenized = pythonTokenizer.tokenize(code);
        Assert.assertEquals(exectedTokenized, tokenized);
    }
}
