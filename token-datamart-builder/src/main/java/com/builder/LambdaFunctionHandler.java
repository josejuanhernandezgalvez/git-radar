package com.builder;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.builder.requestHandler.WordContextDTO;
import com.builder.requestHandler.WordContextDTOProcessor;
import com.builder.utils.TokenizedCodeDeserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LambdaFunctionHandler implements RequestHandler<Map<String, Object>, String> {
    private static final int MaxWindowSize = 25;
    private static final S3Service S3_SERVICE = new S3Service();
    TokenWebService tokenWebService = new TokenWebService();
    private static final TokenizedCodeDeserializer tokenizedCodeDeserializer = new TokenizedCodeDeserializer();

    @Override
    public String handleRequest(Map<String, Object> event, Context context) {
        ArrayList<String> tokenizedCode = tokenizedCodeFor(event);
        for (int windowSize = 1; windowSize <MaxWindowSize; windowSize++)
            processFor(windowSize, tokenizedCode);
        return "Done";
    }

    private void processFor(int windowSize, ArrayList<String> tokenizedCode) {
        for (int j = 0; j < tokenizedCode.size() - windowSize + 1; j++) {
            WordContextDTO wordContext = WordContextDTOProcessor.processWordContext(tokenizedCode, windowSize, j);
            if (wordContext == null) continue;
            tokenWebService.post(wordContext.context().replace(" ", ","), wordContext.nextWord());
        }
    }

    private static ArrayList<String> tokenizedCodeFor(Map<String, Object> event) {
        return (ArrayList<String>) tokenizedCodeDeserializer.deserialize(jsonFor(event))
                .getTokenizedCode();
    }

    private static String jsonFor(Map<String, Object> event) {
        return S3_SERVICE.getObjectFrom(bucket(event).get("name"), object(event).get("key"));
    }

    private static Map<String, String> object(Map<String, Object> event) {
        return (Map<String, String>) ((Map<String, Object>) firstRecord(event).get("s3")).get("object");
    }

    private static Map<String, String> bucket(Map<String, Object> event) {
        return (Map<String, String>) ((Map<String, Object>) firstRecord(event).get("s3")).get("bucket");
    }

    private static Map<String, Object> firstRecord(Map<String, Object> event) {
        return ((List<Map<String, Object>>) event.get("Records")).get(0);
    }

}

