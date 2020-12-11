package com.imc4k.todolist.exception;

public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException() {
        super("Todo not found");
    }
}
