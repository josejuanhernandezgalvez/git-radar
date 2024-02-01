package com.suggester.api;

import com.suggester.token.CodeTokenGetter;
import com.suggester.metric.amazon.S3MetricGetter;
import spark.Request;

import java.util.Arrays;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.port;


public class TokenSuggesterApi implements Api {

    private static final String WORDS_PARAMETERS_NAME = ":words";
    private static final String FILE_PARAMETERS_NAME = ":file";
    private static final String METRIC_PARAMETERS_NAME = ":metric";
    private static final String WORDS_DELIMITER = ",";
    private static final int PORT = 9090;

    public void start() {
        port(PORT);
        get("/gitradar/token-suggester/next/" + WORDS_PARAMETERS_NAME, (request, response) -> getNextWord(getWords(request)));
        get("/gitradar/code-metric/" + FILE_PARAMETERS_NAME + "/" + METRIC_PARAMETERS_NAME, (request, response) -> getMetricResponse(request));
    }

    private String getMetricResponse(Request request) {
        return S3MetricGetter.with(getFile(request), getMetric(request)).get();
    }

    private String getMetric(Request request) {
        return request.params(METRIC_PARAMETERS_NAME);
    }

    private String getFile(Request request) {
        return request.params(FILE_PARAMETERS_NAME);
    }

    private String getNextWord(List<String> words) {
        return CodeTokenGetter.with(words).getNextToken();
    }

    private static List<String> getWords(Request request) {
        return Arrays.asList(request.params(WORDS_PARAMETERS_NAME).split(WORDS_DELIMITER));
    }
}
