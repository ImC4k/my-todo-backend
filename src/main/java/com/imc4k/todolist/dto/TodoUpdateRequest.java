package com.imc4k.todolist.dto;

import java.util.List;

public class TodoUpdateRequest {
    private String text;
    private Boolean done;
    private List<String> labelIds;

    public String getText() {
        return text;
    }

    public Boolean getDone() {
        return done;
    }

    public List<String> getLabelIds() {
        return labelIds;
    }
}
