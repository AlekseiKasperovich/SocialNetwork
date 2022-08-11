package com.senla.handler;

import com.senla.exception.EntityNotFoundException;
import com.senla.exception.ExceptionDetails;
import com.senla.exception.MyAccessDeniedException;
import com.senla.exception.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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

    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex, WebRequest request) {
        ExceptionDetails details = exceptionDetailsBuilder(
                ex, "message.registration.error", HttpStatus.CONFLICT, request);
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({MailException.class})
    public ResponseEntity<Object> handleMailException(MailException ex, WebRequest request) {
        ExceptionDetails details = exceptionDetailsBuilder(
                ex, "message.email.error", HttpStatus.SERVICE_UNAVAILABLE, request);
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ExceptionDetails details = exceptionDetailsBuilder(
                ex, "message.entity.not.found.error", HttpStatus.NOT_FOUND, request);
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({MyAccessDeniedException.class})
    public ResponseEntity<Object> handleMyAccessDeniedException(MyAccessDeniedException ex, WebRequest request) {
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

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        ExceptionDetails details = exceptionDetailsBuilder(
                ex, "message.error", HttpStatus.INTERNAL_SERVER_ERROR, request);
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
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
