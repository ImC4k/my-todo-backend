package com.imc4k.todolist.controller;

import com.imc4k.todolist.dto.TodoResponse;
import com.imc4k.todolist.mapper.TodoMapper;
import com.imc4k.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoMapper todoMapper;

    @GetMapping
    public List<TodoResponse> getAll() {
        return null;
    }
}
