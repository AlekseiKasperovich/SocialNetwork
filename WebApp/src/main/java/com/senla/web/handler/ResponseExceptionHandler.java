package com.senla.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxUploadSizeExceededException(
            RedirectAttributes redirectAttributes, MaxUploadSizeExceededException e) {
        log.error(e.getMessage(), e);
        redirectAttributes.addFlashAttribute(
                "message", "You could not upload file bigger then 50 MB");
        return "redirect:/users/profile/update?fail";
    }

    @ExceptionHandler(Exception.class)
    public String handleAnyException(Exception e) {
        log.error(e.getMessage(), e);
        return "error";
    }
}
