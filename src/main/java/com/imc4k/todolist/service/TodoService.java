package com.imc4k.todolist.service;

import com.imc4k.todolist.model.Todo;
import com.imc4k.todolist.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodosRepository todosRepository;

    public List<Todo> getAll() {
        return todosRepository.findAll();
    }
}
