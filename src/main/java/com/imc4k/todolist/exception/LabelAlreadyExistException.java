package com.imc4k.todolist.exception;

public class LabelAlreadyExistException extends RuntimeException {
    public LabelAlreadyExistException() {
        super("Label already exist");
    }
}
