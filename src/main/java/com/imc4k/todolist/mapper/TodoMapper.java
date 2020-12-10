package com.imc4k.todolist.mapper;

import com.imc4k.todolist.dto.TodoRequest;
import com.imc4k.todolist.dto.TodoResponse;
import com.imc4k.todolist.dto.TodoUpdateRequest;
import com.imc4k.todolist.model.Label;
import com.imc4k.todolist.model.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class TodoMapper {
    public TodoResponse toResponse(Todo todo, List<Label> labels) {
        return new TodoResponse(todo.getId(), todo.getText(), todo.getDone(), labels);
    }

    public Todo toEntity(TodoRequest todoRequest) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoRequest, todo);
        todo.setDone(false);
        todo.setLabelIds(Collections.emptyList());
        return todo;
    }

    public Todo toEntity(String id, TodoUpdateRequest todoRequest) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoRequest, todo);
        todo.setId(id);
        return todo;
    }
}
