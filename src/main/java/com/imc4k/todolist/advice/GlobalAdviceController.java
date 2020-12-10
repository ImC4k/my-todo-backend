package com.imc4k.todolist.advice;

import exception.InvalidColorException;
import exception.LabelAlreadyExistException;
import exception.LabelNotFoundException;
import exception.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAdviceController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {IllegalArgumentException.class, InvalidColorException.class, LabelAlreadyExistException.class})
    public ErrorResponse handleIllegalArgument(Exception exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.name());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {TodoNotFoundException.class, LabelNotFoundException.class})
    public ErrorResponse handleResourceNotFound(Exception exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.name());
    }
}
