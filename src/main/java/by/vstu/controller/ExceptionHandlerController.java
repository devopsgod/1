package by.vstu.controller;

import by.vstu.dto.NamedExceptionDOO;
import by.vstu.exception.NamedException;
import by.vstu.service.email.EmailExceptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {

    private final EmailExceptionService emailExceptionService;

    @ExceptionHandler(NamedException.class)
    public ResponseEntity<NamedExceptionDOO> namedExceptiondHadler(NamedException e) {
        return new ResponseEntity<>(new NamedExceptionDOO(e.getName(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<NamedExceptionDOO> servletExceptiondHadler(ServletException e) {
        return getResponseEntity("request.exception", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<NamedExceptionDOO> accessDeniedExceptionHandler(AccessDeniedException e) {
        return new ResponseEntity<>(new NamedExceptionDOO("access.denied", e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<NamedExceptionDOO>> handleValidationException(MethodArgumentNotValidException ex) {
        List<NamedExceptionDOO> exceptions = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            exceptions.add(new NamedExceptionDOO(error.getObjectName() + "." + error.getField() + ".invalid", error.getDefaultMessage()));
        }
        return new ResponseEntity<>(exceptions, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<NamedExceptionDOO> handleInvalidFormatException(Exception ex) {
        emailExceptionService.sendExceptionEmail(ex);
        log.error(ex.getMessage(), ex);
        return getResponseEntity("I'm teapot", "Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            NumberFormatException.class
    })
    public ResponseEntity<NamedExceptionDOO> handleHttpMessageNotReadableException(Exception ex, HttpServletRequest request) {
        emailExceptionService.sendInvalidFormatExceptionEmail(ex, request);
        return getResponseEntity("json.parse.exception", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<NamedExceptionDOO> getResponseEntity(String code, String message, HttpStatus status) {
        return new ResponseEntity<>(new NamedExceptionDOO(code, message), status);
    }
}
