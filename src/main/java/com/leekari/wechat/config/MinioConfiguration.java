package com.leekari.wechat.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author litao
 * @date 2020/7/29 12:55
 * @description
 */
@Component
public class MinioConfiguration {

    private static String endpoint;

    private static String accessKey;

    private static String accessSecret;

    @Value("${MinioClient.endpoint}")
    public void setEndpoint(String endpoint) {
        MinioConfiguration.endpoint = endpoint;
    }
    @Value("${MinioClient.accessKey}")
    public void setAccessKey(String accessKey) {
        MinioConfiguration.accessKey = accessKey;
    }
    @Value("${MinioClient.accessSecret}")
    public void setAccessSecret(String accessSecret) {
        MinioConfiguration.accessSecret = accessSecret;
    }

    public static MinioClient minioClient(){
        return MinioClient
                .builder()
                .endpoint(endpoint)
                .credentials(accessKey, accessSecret)
                .build();
    }

}
