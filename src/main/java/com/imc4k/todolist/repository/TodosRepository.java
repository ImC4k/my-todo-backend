package com.imc4k.todolist.repository;

import com.imc4k.todolist.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodosRepository extends MongoRepository<Todo, String> {
    List<Todo> findAllByLabelIdsIn(List<String> ids);
}
