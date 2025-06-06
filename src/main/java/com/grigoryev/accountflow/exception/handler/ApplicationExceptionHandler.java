package com.grigoryev.accountflow.exception.handler;

import com.grigoryev.accountflow.aspect.Loggable;
import com.grigoryev.accountflow.dto.error.IncorrectData;
import com.grigoryev.accountflow.dto.error.ValidationErrorResponse;
import com.grigoryev.accountflow.dto.error.Violation;
import com.grigoryev.accountflow.exception.TransferFundsException;
import com.grigoryev.accountflow.exception.InvalidPasswordException;
import com.grigoryev.accountflow.exception.NotFoundException;
import com.grigoryev.accountflow.exception.UserIdHeaderNotValidException;
import io.jsonwebtoken.JwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Loggable
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<IncorrectData> handleNotFoundException(NotFoundException exception) {
        return getResponse(exception.getClass().getSimpleName(), exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIdHeaderNotValidException.class)
    public ResponseEntity<IncorrectData> handleUserIdHeaderNotValidException(UserIdHeaderNotValidException exception) {
        return getResponse(exception.getClass().getSimpleName(), exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<IncorrectData> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return getResponse(exception.getClass().getSimpleName(), exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<IncorrectData> handleJwtException(JwtException exception) {
        return getResponse(exception.getClass().getSimpleName(), exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<IncorrectData> handleInvalidPasswordException(InvalidPasswordException exception) {
        return getResponse(exception.getClass().getSimpleName(), exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(TransferFundsException.class)
    public ResponseEntity<IncorrectData> handleTransferFundsException(TransferFundsException exception) {
        return getResponse(exception.getClass().getSimpleName(), exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<Violation> violations = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new Violation(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ValidationErrorResponse(HttpStatus.CONFLICT.toString(), violations));
    }

    private static ResponseEntity<IncorrectData> getResponse(String name, String message, HttpStatus status) {
        IncorrectData incorrectData = new IncorrectData(name, message, status.toString());
        return ResponseEntity.status(status).body(incorrectData);
    }

}
