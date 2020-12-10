package com.imc4k.todolist.dto;

import com.imc4k.todolist.model.Label;

import java.util.List;

public class TodoResponse {
    private String id;
    private String text;
    private Boolean done;
    private List<Label> labels;

    public TodoResponse() {
    }

    public TodoResponse(String id, String text, Boolean done, List<Label> labels) {
        this.id = id;
        this.text = text;
        this.done = done;
        this.labels = labels;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Boolean getDone() {
        return done;
    }

    public List<Label> getLabels() {
        return labels;
    }
}
