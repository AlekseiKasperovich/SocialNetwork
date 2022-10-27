package com.senla.web.validator.impl;

import com.senla.web.exception.ImageUploadException;
import com.senla.web.validator.ImageValidator;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageValidatorImpl implements ImageValidator {

    //    @Value("${app.image.preferences.file-size}")
    private Integer fileSize;

    @Override
    public void imageSizeCheck(MultipartFile file) {
        long originSizeMB = 0;
        if (file != null) {
            originSizeMB = file.getSize() / (1024 * 1024);
        }
        if (originSizeMB > fileSize) {
            throw new ImageUploadException(
                    String.format("Image size must be less then %s megabytes", fileSize));
        }
    }

    @Override
    public void imageTypeCheck(Optional<String> mimeType) {
        if (mimeType.isEmpty() || mimeType.filter(s -> s.contains("image")).isEmpty()) {
            throw new ImageUploadException("Invalid type, provide file with image extension!");
        }
    }
}
