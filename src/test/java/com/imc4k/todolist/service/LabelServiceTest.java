package com.imc4k.todolist.service;

import com.imc4k.todolist.repository.LabelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LabelServiceTest {
    @InjectMocks
    private LabelService labelService;

    @Mock
    private LabelRepository labelRepository;
    
    @Test
    void should__when__given_() {
        //given
        
                
        //when
        
        
        //then
    }
}
