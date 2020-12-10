package com.imc4k.todolist.mapper;

import com.imc4k.todolist.dto.TodoResponse;
import com.imc4k.todolist.model.Label;
import com.imc4k.todolist.model.Todo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoMapper {
    public TodoResponse toResponse(Todo todo, List<Label> labels) {
        return new TodoResponse(todo.getId(), todo.getText(), todo.getDone(), labels);
    }
}
