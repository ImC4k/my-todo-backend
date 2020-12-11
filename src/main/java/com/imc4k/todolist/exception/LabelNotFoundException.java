package com.imc4k.todolist.exception;

public class LabelNotFoundException extends RuntimeException {
    public LabelNotFoundException() {
        super("Label not found");
    }
}
