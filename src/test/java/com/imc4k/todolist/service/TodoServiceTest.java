package com.imc4k.todolist.service;

import com.imc4k.todolist.model.Todo;
import com.imc4k.todolist.repository.TodosRepository;
import exception.TodoNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void should_throw_TodoNotFoundException_when_getById_given_an_invalid_todo_id() {
        //given
        when(todosRepository.findById(anyString())).thenReturn(Optional.empty());

        //when
        Exception exception = assertThrows(TodoNotFoundException.class, ()-> todoService.getById("something"));

        //then
        assertEquals("Todo not found", exception.getMessage());
    }

    @Test
    void should_return_newly_created_todo_when_createTodo_given_valid_todo_info() {
        //given
        Todo todoRequest = new Todo("test", false, Collections.emptyList());
        Todo expected = new Todo("test", false, Collections.emptyList());
        when(todosRepository.save(todoRequest)).thenReturn(expected);

        //when
        todoService.createTodo(todoRequest);
        final ArgumentCaptor<Todo> todoArgumentCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todosRepository, times(1)).save(todoArgumentCaptor.capture());

        //then
        final Todo actual = todoArgumentCaptor.getValue();
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getDone(), actual.getDone());
    }

    @Test
    void should_return_updated_todo__when_update_given_valid_id() {
        //given
        Todo updatedTodo = new Todo("1", "original", true, Collections.emptyList());
        Todo original = new Todo("1", "original", false, Collections.emptyList());
        Todo expected = new Todo("1", "original", true, Collections.emptyList());
        when(todosRepository.findById("1")).thenReturn(Optional.of(original));

        //when
        todoService.update(updatedTodo);
        final ArgumentCaptor<Todo> todoArgumentCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todosRepository, times(1)).save(todoArgumentCaptor.capture());

        //then
        final Todo actual = todoArgumentCaptor.getValue();
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void should_throw_TodoNotFoundException_when_update_given_invalid_id() {
        //given
        Todo updatedTodo = new Todo("1", "original", true, Collections.emptyList());
        when(todosRepository.findById(updatedTodo.getId())).thenReturn(Optional.empty());

        //when
        Exception exception = assertThrows(TodoNotFoundException.class, ()-> todoService.update(updatedTodo));

        //then
        assertEquals("Todo not found", exception.getMessage());
    }

    @Test
    void should_call_repository_deleteById_when_delete_given_valid_id() {
        //given
        Todo todoInRepository = new Todo("1", "test", true, Collections.emptyList());
        when(todosRepository.findById(todoInRepository.getId())).thenReturn(Optional.of(todoInRepository));

        //when
        todoService.delete(todoInRepository.getId());

        //then
        verify(todosRepository, times(1)).deleteById(todoInRepository.getId());
    }

    @Test
    void should_throw_TodoNotFoundException_when_delete_given_invalid_id() {
        //given
        when(todosRepository.findById(anyString())).thenReturn(Optional.empty());

        //when
        Exception exception = assertThrows(TodoNotFoundException.class, ()-> todoService.delete("invalid id"));

        //then
        assertEquals("Todo not found", exception.getMessage());
    }
}
