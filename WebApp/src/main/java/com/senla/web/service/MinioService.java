package com.senla.web.service;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {

    String upload(MultipartFile file, String bucketName);

    byte[] getMinioObjectAsBytes(String imageName, String bucketName);

    void removeObject(String imageName, String bucketName);
}
