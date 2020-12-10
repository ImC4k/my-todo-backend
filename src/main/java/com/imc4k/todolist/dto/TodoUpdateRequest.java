package com.imc4k.todolist.dto;

import java.util.List;

public class TodoUpdateRequest {
    private String text;
    private Boolean done;
    private List<String> labelIds;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public List<String> getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(List<String> labelIds) {
        this.labelIds = labelIds;
    }
}
