package com.senla.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(SizeLimitExceededException.class)
    public ModelAndView handleUserNotFoundException(HttpServletRequest request, SizeLimitExceededException e) {
        log.error(">>>>>>>>>> User Not Found Exception");
        log.error(e.getMessage(), e);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", e.getMessage());
        modelAndView.setViewName("service/user-not-found");

        return modelAndView;
    }

    @ExceptionHandler(SocialNetworkException.class)
    public ModelAndView handleSocialNetworkException(HttpServletRequest request, SocialNetworkException e) {
        log.error(">>>>>>>>>> Social Network Exception");
        log.error(e.getMessage(), e);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("service/something-went-wrong");

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAnyException(HttpServletRequest request, Exception e) {
        log.error(">>>>>>>>>> Exception");
        log.error(e.getMessage(), e);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("service/something-went-wrong");

        return modelAndView;
    }

}
