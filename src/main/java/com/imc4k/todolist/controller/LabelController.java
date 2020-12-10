package com.imc4k.todolist.controller;

import com.imc4k.todolist.dto.LabelRequest;
import com.imc4k.todolist.mapper.LabelMapper;
import com.imc4k.todolist.model.Label;
import com.imc4k.todolist.service.LabelService;
import exception.InvalidColorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/labels")
public class LabelController {
    @Autowired
    private LabelService labelService;
    @Autowired
    private LabelMapper labelMapper;

    @GetMapping
    public List<Label> getAll() {
        return labelService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Label create(@RequestBody LabelRequest labelRequest) throws InvalidColorException {
        return labelService.createLabel(labelMapper.toEntity(labelRequest));
    }

    @PutMapping("/{id}")
    public Label update(@PathVariable String id, @RequestBody LabelRequest labelRequest) throws InvalidColorException {
        return labelService.update(labelMapper.toEntity(id, labelRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        labelService.delete(id);
    }
}
