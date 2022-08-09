package com.senla.handler;

import com.senla.exception.ExceptionDetails;
import com.senla.exception.MyAccessDeniedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

/**
 * @author Aliaksei Kaspiarovich
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<Object> handleHttpClientErrorException(
            HttpClientErrorException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String message = messageSource.getMessage("message.http.client.error",
                null, request.getLocale());
        ExceptionDetails details = ExceptionDetails.builder()
                .title("Http Client Error Exception")
                .detail(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .message(message)
                .time(LocalDateTime.now()).build();
        return handleExceptionInternal(
                ex, details, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(
            AuthenticationException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String message = messageSource.getMessage("message.authentication.error",
                null, request.getLocale());
        ExceptionDetails details = ExceptionDetails.builder()
                .title("Authentication Exception")
                .detail(ex.getMessage())
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(message)
                .time(LocalDateTime.now()).build();
        return handleExceptionInternal(
                ex, details, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({LockedException.class})
    public ResponseEntity<Object> handleLockedException(LockedException ex,
                                                        WebRequest request) {
        log.error(ex.getMessage(), ex);
        String message = messageSource.getMessage("message.account.is.locked.error",
                null, request.getLocale());
        ExceptionDetails details = ExceptionDetails.builder()
                .title("Locked Exception")
                .detail(ex.getMessage())
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(message)
                .time(LocalDateTime.now()).build();
        return handleExceptionInternal(
                ex, details, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String message = messageSource.getMessage("message.validation.error",
                null, request.getLocale());
        ExceptionDetails details = ExceptionDetails.builder()
                .title("Method Argument Not Valid Exception")
                .detail(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .time(LocalDateTime.now()).build();
        return handleExceptionInternal(
                ex, details, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleException(RuntimeException ex,
                                                  WebRequest request) {
        log.error(ex.getMessage(), ex);
        String message = messageSource.getMessage("message.error", null,
                request.getLocale());
        ExceptionDetails details = ExceptionDetails.builder()
                .title("Runtime Exception")
                .detail(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(message)
                .time(LocalDateTime.now()).build();
        return handleExceptionInternal(
                ex, details, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

    @ExceptionHandler({MyAccessDeniedException.class, BadCredentialsException.class, ResourceAccessException.class})
    public ResponseEntity<Object> handleMyAccessDeniedException(
            RuntimeException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String message = messageSource.getMessage("message.access.denied.error",
                null, request.getLocale());
        ExceptionDetails details = ExceptionDetails.builder()
                .title("Access Denied Exception")
                .detail(ex.getMessage())
                .status(HttpStatus.FORBIDDEN.value())
                .message(message)
                .time(LocalDateTime.now()).build();
        return handleExceptionInternal(
                ex, details, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String message = messageSource.getMessage("message.validation.error",
                null, request.getLocale());
        ExceptionDetails details = ExceptionDetails.builder()
                .title("Constraint Violation Exception")
                .detail(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .time(LocalDateTime.now()).build();
        return handleExceptionInternal(
                ex, details, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
