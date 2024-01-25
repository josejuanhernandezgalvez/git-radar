package metrics.counters.function_counter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaFunctionCounterMetric implements FunctionCounterMetric{

    private static final Pattern pattern = Pattern.compile("\\s*(public|private|protected|default)\\s*\\w*\\s+.+\\s+\\w+\\s*\\(.*\\)\\s*\\{\n");


    @Override
    public int countFunctions(String code) {
        Matcher matcher = pattern.matcher(code);
        return (int) matcher.results().count();
    }
}
