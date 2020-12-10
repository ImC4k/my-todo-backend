package com.ImC4k.todolist.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TodosServiceTest {
    @InjectMocks
    private TodosService todosService;

    @Test
    void should_return_all_todos_when_getAll_given_todos_not_empty() {
        //given


        //when


        //then
    }
}
