package com.suggester.metric.amazon;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

public class S3 {

    private static final String ENDPOINT = "http://localstack:4566";
    private static final S3Client S3_CLIENT;

    static {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create("test", "test");
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsCredentials);
        S3_CLIENT = S3Client.builder()
                .credentialsProvider(credentialsProvider)
                .endpointOverride(URI.create(ENDPOINT))
                .region(Region.US_EAST_1)
                .build();
    }

    public String getMetricWith(String bucketName, String keyName){
        ResponseInputStream<GetObjectResponse> s3Object = getObjectFrom(bucketName, keyName);
        return extractContentFrom(s3Object);
    }

    private String extractContentFrom(ResponseInputStream<GetObjectResponse> s3Object) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(s3Object));
            String line;
            while ((line = reader.readLine()) != null) builder.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private ResponseInputStream<GetObjectResponse> getObjectFrom(String bucketName, String keyName) {

        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        ListObjectsV2Response listObjectsResponse = S3_CLIENT.listObjectsV2(listObjectsRequest);
        List<S3Object> objects = listObjectsResponse.contents();

        for (S3Object object : objects) {
            System.out.println("Object: " + object.key());
        }


        GetObjectRequest build = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();
        return S3_CLIENT.getObject(build);
    }
}
