package metrics.utils;

import metrics.SourceCode;

public interface Parser {
    SourceCode parseJson(String jsonString);
}
