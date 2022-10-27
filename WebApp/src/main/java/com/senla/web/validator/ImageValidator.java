package com.senla.web.validator;

import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface ImageValidator {

    void imageSizeCheck(MultipartFile file);

    void imageTypeCheck(Optional<String> mimeType);
}
