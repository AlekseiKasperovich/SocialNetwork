package com.senla.web.service.impl;

import com.senla.web.exception.ImageUploadException;
import com.senla.web.service.MinioService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;

    @Override
    public String upload(MultipartFile file, String bucketName) {
        String uniqFileName = UUID.randomUUID().toString();
        String contentType = file.getContentType();
        try {
            PutObjectArgs putObjectArgs =
                    PutObjectArgs.builder().contentType(contentType).stream(
                                    file.getInputStream(), -1, 5L * 1024L * 1024L)
                            .object(uniqFileName)
                            .bucket(bucketName)
                            .build();
            minioClient.putObject(putObjectArgs);
            return uniqFileName;
        } catch (MinioException
                | IllegalArgumentException
                | IOException
                | GeneralSecurityException e) {
            log.error(e.getMessage());
            throw new ImageUploadException(e.getMessage());
        }
    }
}
