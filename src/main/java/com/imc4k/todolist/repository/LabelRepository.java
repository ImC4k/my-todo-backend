package com.imc4k.todolist.repository;

import com.imc4k.todolist.model.Label;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LabelRepository extends MongoRepository<Label, String> {
    Optional<List<Label>> findAllByText(String text);
}
