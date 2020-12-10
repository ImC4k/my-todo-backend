package com.imc4k.todolist.service;

import com.imc4k.todolist.model.Todo;
import com.imc4k.todolist.repository.TodosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    @InjectMocks
    private TodoService todoService;
    @Mock
    private TodosRepository todosRepository;

    private List<Todo> createDummyTodos() {
        List<Todo> allTodos = new ArrayList<>();
        allTodos.add(new Todo("1", "text1", true, Collections.emptyList()));
        allTodos.add(new Todo("2", "text2", false, Collections.emptyList()));
        allTodos.add(new Todo("3", "text3", true, Collections.emptyList()));
        allTodos.add(new Todo("4", "text4", false, Collections.emptyList()));
        allTodos.add(new Todo("5", "text5", true, Collections.emptyList()));
        return allTodos;
    }

    @Test
    void should_return_all_todos_when_getAll_given_todos_not_empty() {
        //given
        final List<Todo> expected = createDummyTodos();
        when(todosRepository.findAll()).thenReturn(expected);

        //when
        final List<Todo> actual = todoService.getAll();

        //then
        assertEquals(createDummyTodos().size(), actual.size());
    }

    @Test
    void should_return_required_todo_when_getById_given_a_valid_todo_id() {
        //given
        final List<Todo> expected = createDummyTodos();
        when(todosRepository.findById(anyString())).thenReturn(Optional.of(expected.get(1)));

        //when
        final Todo actual = todoService.getById("2");

        //then
        assertEquals(expected.get(1), actual);
    }
}
