package com.grigoryev.accountflow.exception.handler;

import com.grigoryev.accountflow.dto.IncorrectData;
import com.grigoryev.accountflow.exception.NotFoundException;
import com.grigoryev.accountflow.exception.UserIdHeaderNotValidException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    private static ResponseEntity<IncorrectData> getResponse(String name, String message, HttpStatus status) {
        IncorrectData incorrectData = new IncorrectData(name, message, status.toString());
        return ResponseEntity.status(status).body(incorrectData);
    }

}
