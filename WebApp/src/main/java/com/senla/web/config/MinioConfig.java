package com.senla.web.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Value("${spring.minio.access-key}")
    private String accessKey;

    @Value("${spring.minio.secret-key}")
    private String accessSecret;

    @Value("${spring.minio.url}")
    private String minioUrl;

    @Bean
    public MinioClient generateMinioClient() {
        return MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(accessKey, accessSecret)
                .build();
    }
}
