package com.senla.web.service.impl;

import com.senla.web.exception.ImageDownloadException;
import com.senla.web.exception.ImageRemoveException;
import com.senla.web.exception.ImageUploadException;
import com.senla.web.service.MinioService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.cache.annotation.CacheEvict;
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
            throw new ImageUploadException("Something went wrong.");
        }
    }

    @Override
    public byte[] getMinioObjectAsBytes(String imageName, String bucketName) {
        try (InputStream object =
                minioClient.getObject(
                        GetObjectArgs.builder().bucket(bucketName).object(imageName).build())) {
            System.out.println("minio");
            return IOUtils.toByteArray(object);
        } catch (MinioException | GeneralSecurityException | IOException e) {
            throw new ImageDownloadException("Something went wrong.");
        }
    }

    @CacheEvict(value = "images", key = "#imageName")
    @Override
    public void removeObject(String imageName, String bucketName) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucketName).object(imageName).build());
        } catch (MinioException | GeneralSecurityException | IOException e) {
            throw new ImageRemoveException("Something went wrong.");
        }
    }
}
