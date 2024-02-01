package com.metrics;


import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.metrics.codeanalysis.Metrics;
import com.metrics.codeanalysis.MetricsAnalyzer;
import com.metrics.codeanalysis.MetricsAnalyzerInitializer;
import com.metrics.codeanalysis.SourceCodeMetrics;
import com.metrics.utils.SourceCodeDeserializer;
import com.amazonaws.services.lambda.runtime.Context;
import com.metrics.utils.SourceCodeMetricsSerializer;

import java.util.List;
import java.util.Map;

public class LambdaFunctionHandler implements RequestHandler<Map<String, Object>, String> {
    private static final SourceCodeDeserializer deserializer = new SourceCodeDeserializer();
    private static final SourceCodeMetricsSerializer serializer = new SourceCodeMetricsSerializer();
    private static final S3Service S3_SERVICE = new S3Service();
    public static String MetricsBucket = "gitradar-metrics";

    @Override
    public String handleRequest(Map<String, Object> event, Context context) {
        S3_SERVICE.putObjectTo(MetricsBucket,
                               sourceCodeFrom(sourceBucketName(event).get("name"), sourceObject(event).get("key")).getName() + ".json",
                               serializedCode(event));
        return "Done";
    }

    private String serializedCode(Map<String, Object> event) {
        return serializer.serialize(new SourceCodeMetrics(
                                    analyzerFor(event).analyzeMetrics(sourceCodeFrom(
                                                    sourceBucketName(event).get("name"),
                                                    sourceObject(event).get("key")).getCode()),
                                                    System.currentTimeMillis()));
    }

    private static MetricsAnalyzer analyzerFor(Map<String, Object> event) {
        return MetricsAnalyzerInitializer.initializeMetricAnalyzer(sourceCodeFrom(sourceBucketName(event).get("name"), sourceObject(event).get("key")).getName());
    }


    private static SourceCode sourceCodeFrom(String bucketName, String objectName) {
        return deserializer.deserialize(S3_SERVICE.getObjectFrom(bucketName, objectName));
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

