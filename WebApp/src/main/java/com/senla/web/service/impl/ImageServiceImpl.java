package com.senla.web.service.impl;

import com.senla.web.dto.profile.ImageDto;
import com.senla.web.dto.user.DtoUser;
import com.senla.web.service.ImageService;
import com.senla.web.service.MinioService;
import com.senla.web.service.ProfileService;
import com.senla.web.service.UserService;
import java.util.UUID;
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
    private final UserService userService;
    private final ProfileService profileService;

    @Override
    public void uploadImage(MultipartFile file) {
        DtoUser user = userService.getUser();
        if (user.getImage() != null) {
            minioService.removeObject(user.getImage().toString(), bucketName);
        }
        String uniqFileName = minioService.upload(file, bucketName);
        ImageDto image = new ImageDto(uniqFileName);
        profileService.updateImage(image);
    }

    @Override
    public byte[] downloadImage(UUID imageName) {
        return minioService.getMinioObjectAsBytes(imageName.toString(), bucketName);
    }
}
