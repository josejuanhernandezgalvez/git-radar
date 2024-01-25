package metrics.counters;

import java.util.Arrays;

public class IdentationMetric {

    private int maxIdentation;
    private boolean processNextLine;

    public IdentationMetric() {
        this.maxIdentation = 0;
        this.processNextLine = true;
    }

    public int calculateMaxIdentation(String code) {
        Arrays.stream(code.split("\n"))
                .forEach(this::checkLine);
        return maxIdentation / 4;
    }

    private void checkLine(String line) {
        if (processNextLine)
            processLine(line);
    }

    private void processLine(String line) {
        if (isLineEmpty(line)) {
            maxIdentation = Math.max(maxIdentation, getIdentationLevel(line));
            processNextLine = !isLastCharacterSpecial(line.charAt(line.length() - 1));
        }
    }

    private boolean isLastCharacterSpecial(char lastCharacter) {
        return (lastCharacter == '+' || lastCharacter == ',' || lastCharacter == '\\');
    }

    private int getIdentationLevel(String line) {
        return line.length() - line.replaceAll("^\\s+", "").length();
    }

    private boolean isLineEmpty(String line) {
        return !line.trim().isEmpty();
    }
}
