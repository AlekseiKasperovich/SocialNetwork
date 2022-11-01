package com.senla.web.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void uploadImage(MultipartFile file);
}
