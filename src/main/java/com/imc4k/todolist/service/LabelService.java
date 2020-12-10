package com.imc4k.todolist.service;

import com.imc4k.todolist.model.Label;
import com.imc4k.todolist.repository.LabelRepository;
import exception.LabelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    public List<Label> getAll() {
        return labelRepository.findAll();
    }

    public Label getById(String id) {
        return labelRepository.findById(id).orElseThrow(LabelNotFoundException::new);
    }
}
