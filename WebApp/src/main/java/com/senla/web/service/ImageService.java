package com.senla.web.service;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void uploadImage(MultipartFile file);

    byte[] downloadImage(UUID imageName);
}
