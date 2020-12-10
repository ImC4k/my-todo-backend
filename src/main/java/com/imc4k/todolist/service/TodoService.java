package com.imc4k.todolist.service;

import com.imc4k.todolist.dto.TodoRequest;
import com.imc4k.todolist.model.Todo;
import com.imc4k.todolist.repository.TodosRepository;
import exception.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodosRepository todosRepository;

    public List<Todo> getAll() {
        return todosRepository.findAll();
    }

    public Todo getById(String id) {
        return todosRepository.findById(id).orElseThrow(TodoNotFoundException::new);
    }

    public Todo createTodo(Todo newTodo) {
        if (newTodo.getDone() == null) {
            newTodo.setDone(false);
        }
        if (newTodo.getLabelIds() == null) {
            newTodo.setLabelIds(Collections.emptyList());
        }
        return todosRepository.save(newTodo);
    }

    public Todo update(Todo updatedTodo) {
        this.getById(updatedTodo.getId());
        return createTodo(updatedTodo);
    }

    public void delete(String id) {
        this.getById(id);
        todosRepository.deleteById(id);
    }
}
