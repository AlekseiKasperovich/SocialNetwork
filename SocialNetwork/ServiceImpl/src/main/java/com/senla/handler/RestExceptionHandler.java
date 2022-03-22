package com.senla.handler;

import com.senla.api.exception.EntityNotFoundException;
import com.senla.api.exception.ExceptionDetails;
import com.senla.api.exception.MyAccessDeniedException;
import com.senla.api.exception.UserAlreadyExistException;
import javax.validation.ConstraintViolationException;
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

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<Object> handleUserAlreadyExistException(
            UserAlreadyExistException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String message = messageSource.getMessage("message.registration.error",
                null, request.getLocale());
        ExceptionDetails details = ExceptionDetails.builder()
                .title("User Already Exist Exception")
                .detail(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .message(message).build();
        return handleExceptionInternal(
                ex, details, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({MailException.class})
    public ResponseEntity<Object> handleMailException(MailException ex,
            WebRequest request) {
        log.error(ex.getMessage(), ex);
        String message = messageSource.getMessage("message.email.error", null,
                request.getLocale());
        ExceptionDetails details = ExceptionDetails.builder()
                .title("Mail Exception")
                .detail(ex.getMessage())
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .message(message).build();
        return handleExceptionInternal(
                ex, details, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE,
                request);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String message = messageSource.getMessage("message.entity.not.found.error",
                null, request.getLocale());
        ExceptionDetails details = ExceptionDetails.builder()
                .title("Entity Not Found Exception")
                .detail(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .message(message).build();
        return handleExceptionInternal(
                ex, details, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({MyAccessDeniedException.class})
    public ResponseEntity<Object> handleMyAccessDeniedException(
            MyAccessDeniedException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String message = messageSource.getMessage("message.acces.denied.error",
                null, request.getLocale());
        ExceptionDetails details = ExceptionDetails.builder()
                .title("Access Denied Exception")
                .detail(ex.getMessage())
                .status(HttpStatus.FORBIDDEN.value())
                .message(message).build();
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
                .message(message).build();
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
                .message(message).build();
        return handleExceptionInternal(
                ex, details, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

}
