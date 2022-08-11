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
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex, WebRequest request) {
        ExceptionDetails details = exceptionDetailsBuilder(
                ex, "message.http.client.error", HttpStatus.CONFLICT, request);
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        ExceptionDetails details = exceptionDetailsBuilder(
                ex, "message.authentication.error", HttpStatus.UNAUTHORIZED, request);
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({LockedException.class})
    public ResponseEntity<Object> handleLockedException(LockedException ex, WebRequest request) {
        ExceptionDetails details = exceptionDetailsBuilder(
                ex, "message.account.is.locked.error", HttpStatus.UNAUTHORIZED, request);
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ExceptionDetails details = exceptionDetailsBuilder(
                ex, "message.validation.error", HttpStatus.BAD_REQUEST, request);
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        ExceptionDetails details = exceptionDetailsBuilder(
                ex, "message.error", HttpStatus.INTERNAL_SERVER_ERROR, request);
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({MyAccessDeniedException.class, BadCredentialsException.class, ResourceAccessException.class})
    public ResponseEntity<Object> handleMyAccessDeniedException(RuntimeException ex, WebRequest request) {
        ExceptionDetails details = exceptionDetailsBuilder(
                ex, "message.access.denied.error", HttpStatus.FORBIDDEN, request);
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        ExceptionDetails details = exceptionDetailsBuilder(
                ex, "message.validation.error", HttpStatus.BAD_REQUEST, request);
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private ExceptionDetails exceptionDetailsBuilder(Exception ex, String code, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String message = messageSource.getMessage(code, null, request.getLocale());
        return ExceptionDetails.builder()
                .title(ex.getCause().toString())
                .detail(ex.getMessage())
                .status(status.value())
                .message(message)
                .time(LocalDateTime.now()).build();
    }
}
