package com.senla.web.service.impl;

import com.senla.web.service.ImageService;
import com.senla.web.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${spring.minio.bucket.image}")
    private String bucketName;

    private final MinioService minioService;

    @Override
    public String uploadImage(MultipartFile file) {
        return minioService.upload(file, bucketName);
    }
}
