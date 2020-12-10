package com.imc4k.todolist.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Label {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String text;
    private String color;

    public Label() {}

    public Label(String id, String text, String color) {
        this.id = id;
        this.text = text;
        this.color = color;
    }

    public Label(String text, String color) {
        this.text = text;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Label) {
            return this.id.equals(((Label) obj).getId()) &&
                    this.text.equals(((Label) obj).getText()) &&
                    this.color.equals(((Label) obj).getColor());
        }
        return super.equals(obj);
    }
}
