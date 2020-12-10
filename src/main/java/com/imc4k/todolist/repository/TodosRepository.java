package com.imc4k.todolist.repository;

import com.imc4k.todolist.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodosRepository extends MongoRepository<Todo, String> {
}
