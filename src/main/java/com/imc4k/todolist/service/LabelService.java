package com.imc4k.todolist.service;

import com.imc4k.todolist.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;
}
