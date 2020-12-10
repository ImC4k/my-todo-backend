package com.imc4k.todolist.repository;

import com.imc4k.todolist.model.Label;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LabelRepository extends MongoRepository<Label, String> {
    List<Label> findAllByText(String text);
}
