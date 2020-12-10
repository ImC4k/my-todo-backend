package com.imc4k.todolist.dto;

public class TodoRequest {
    private String text;

    public TodoRequest() {}

    public TodoRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
