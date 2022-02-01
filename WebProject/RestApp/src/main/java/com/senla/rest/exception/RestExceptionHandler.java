package com.senla.rest.exception;

import com.senla.api.exception.EntityNotFoundException;
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
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    private final MessageSource messages;

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String bodyOfResponse = messages.getMessage("message.authentication.error", null, request.getLocale());
        return handleExceptionInternal(
                ex, bodyOfResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({LockedException.class})
    public ResponseEntity<Object> handleLockedException(LockedException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String bodyOfResponse = messages.getMessage("message.account.is.locked.error", null, request.getLocale());
        return handleExceptionInternal(
                ex, bodyOfResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String bodyOfResponse = messages.getMessage("message.registration.error", null, request.getLocale());
        return handleExceptionInternal(
                ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({MailException.class})
    public ResponseEntity<Object> handleMailException(MailException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String bodyOfResponse = messages.getMessage("message.email.error", null, request.getLocale());
        return handleExceptionInternal(
                ex, bodyOfResponse, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String bodyOfResponse = messages.getMessage("message.error", null, request.getLocale());
        return handleExceptionInternal(
                ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String bodyOfResponse = messages.getMessage("message.entity.not.found.error", null, request.getLocale());
        return handleExceptionInternal(
                ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({MyAccessDeniedException.class})
    public ResponseEntity<Object> handleMyAccessDeniedException(MyAccessDeniedException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String bodyOfResponse = messages.getMessage("message.acces.denied.error", null, request.getLocale());
        return handleExceptionInternal(
                ex, bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String bodyOfResponse = messages.getMessage("message.validation.error", null, request.getLocale());
        return handleExceptionInternal(
                ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String bodyOfResponse = messages.getMessage("message.validation.error", null, request.getLocale());
        return handleExceptionInternal(
                ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
