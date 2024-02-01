package com.tokenizer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.tokenizer.context.SourceCode;
import com.tokenizer.context.TokenizedCode;
import com.tokenizer.context.tokenizer.TokenizerInitializer;
import com.tokenizer.utils.SourceCodeDeserializer;
import com.tokenizer.utils.TokenizedCodeSerializer;

import java.util.List;
import java.util.Map;

public class LambdaFunctionHandler implements RequestHandler<Map<String, Object>, String> {
    private static final SourceCodeDeserializer deserializer = new SourceCodeDeserializer();
    private static final TokenizedCodeSerializer serializer = new TokenizedCodeSerializer();
    private static final S3Service S3_SERVICE = new S3Service();
    public static String datalake = "gitradar-datalake";

    @Override
    public String handleRequest(Map<String, Object> event, Context context) {
        S3_SERVICE.putObjectTo(datalake,
                               sourceObject(event).get("key"),
                               serializedTokenizedCodeDTO(event));
        return "Done";
    }

    private static String serializedTokenizedCodeDTO(Map<String, Object> event) {
        return serializer.serialize(tokenizedCodeDTO(sourceBucketName(event).get("name"), sourceObject(event).get("key")));
    }

    private static TokenizedCode tokenizedCodeDTO(String bucketName, String objectName) {
        return new TokenizedCode(tokenizedCode(sourceCodeFrom(bucketName, objectName)), System.currentTimeMillis());
    }

    private static SourceCode sourceCodeFrom(String bucketName, String objectName) {
        return deserializer.deserialize(S3_SERVICE.getObjectFrom(bucketName, objectName));
    }

    private static List<String> tokenizedCode(SourceCode sourceCode) {
        return TokenizerInitializer.initialize(sourceCode.getName())
                .tokenize(sourceCode.getCode());
    }

    private static Map<String, String> sourceBucketName(Map<String, Object> event) {
        return (Map<String, String>) ((Map<String, Object>) firstRecord(event).get("s3")).get("bucket");
    }

    private static Map<String, String> sourceObject(Map<String, Object> event) {
        return (Map<String, String>) ((Map<String, Object>) firstRecord(event).get("s3")).get("object");
    }

    private static Map<String, Object> firstRecord(Map<String, Object> event) {
        return ((List<Map<String, Object>>) event.get("Records")).get(0);
    }

}

