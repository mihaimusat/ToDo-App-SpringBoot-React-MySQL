package com.endava.todo.controller.handler;

import com.endava.todo.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleBadRequestValidation(HttpServletRequest request, Exception exception) {
        MethodArgumentNotValidException notValidException = (MethodArgumentNotValidException) exception;
        List<ObjectError> allErrors = notValidException.getBindingResult().getAllErrors();
        StringBuilder message = new StringBuilder();
        for (ObjectError objectError : allErrors) {
            message.append(objectError.getDefaultMessage());
            message.append(".");
        }
        return new ResponseEntity<>(message.toString(), HttpStatus.BAD_REQUEST);
    }
}
