package com.imc4k.todolist.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Todo {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String text;
    private Boolean done;
    private List<String> labelIds;

    public Todo() {
    }

    public Todo(String id, String text, Boolean done, List<String> labelIds) {
        this.id = id;
        this.text = text;
        this.done = done;
        this.labelIds = labelIds;
    }

    public Todo(String text, Boolean done, List<String> labelIds) {
        this.text = text;
        this.done = done;
        this.labelIds = labelIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Todo) {
            return this.id.equals(((Todo) obj).getId()) &&
                    this.text.equals(((Todo) obj).getText()) &&
                    this.done.equals(((Todo) obj).getDone()) &&
                    this.labelIds.equals(((Todo) obj).getLabelIds());
        }
        return super.equals(obj);
    }
}
