package com.imc4k.todolist.controller;

import com.imc4k.todolist.dto.TodoRequest;
import com.imc4k.todolist.dto.TodoResponse;
import com.imc4k.todolist.dto.TodoUpdateRequest;
import com.imc4k.todolist.mapper.TodoMapper;
import com.imc4k.todolist.model.Label;
import com.imc4k.todolist.service.LabelService;
import com.imc4k.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "localhost:3000")
@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoMapper todoMapper;
    @Autowired
    private LabelService labelService;

    @GetMapping
    public List<TodoResponse> getAll() {
        return todoService.getAll().stream().map(this::getTodoResponse).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@RequestBody TodoRequest todoRequest) {
        return getTodoResponse(todoService.createTodo(todoMapper.toEntity(todoRequest)));
    }

    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable String id, @RequestBody TodoUpdateRequest todoUpdateRequest) {
        return getTodoResponse(todoService.update(todoMapper.toEntity(todoUpdateRequest)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        todoService.delete(id);
    }

    private TodoResponse getTodoResponse(com.imc4k.todolist.model.Todo todo) {
        List<Label> labels = todo.getLabelIds() != null? todo.getLabelIds().stream().map(labelService::getById).collect(Collectors.toList()) : null;
        return todoMapper.toResponse(todo, labels);
    }
}
