package com.metrics;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.*;

public class S3Service {
    static final String ACCESS_KEY = "test";
    static final String SECRET_KEY = "test";
    static BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
    private static String serviceEndpoint = "s3.localhost.localstack.cloud:4566";
    private static Regions region = Regions.US_EAST_1;
    private static final AmazonS3 S3_CLIENT;


    private static AwsClientBuilder.EndpointConfiguration endpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(
                serviceEndpoint,
                region.getName());
    }

    private static AWSStaticCredentialsProvider credentialsProvider() {
        return new AWSStaticCredentialsProvider(credentials);
    }

    static {
        S3_CLIENT = AmazonS3ClientBuilder.standard()
                .withCredentials(credentialsProvider())
                .withEndpointConfiguration(endpointConfiguration())
                .build();
    }

    public String getObjectFrom(String bucketName, String keyName) {
        return extractContentFrom(S3_CLIENT.getObject(objectRequest(bucketName, keyName)));
    }

    private static GetObjectRequest objectRequest(String bucketName, String keyName) {
        return new GetObjectRequest(bucketName, keyName);
    }

    private String extractContentFrom(S3Object s3Object) {
        StringBuilder builder = new StringBuilder();
        try (InputStream inputStream = s3Object.getObjectContent();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) builder.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void putObjectTo(String bucketName, String keyName, String content) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes());
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(content.getBytes().length);
        S3_CLIENT.putObject(new PutObjectRequest(bucketName, keyName, inputStream, metadata));
    }
}