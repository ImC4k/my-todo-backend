package com.imc4k.todolist.service;

import com.imc4k.todolist.model.Label;
import com.imc4k.todolist.repository.LabelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LabelServiceTest {
    @InjectMocks
    private LabelService labelService;

    @Mock
    private LabelRepository labelRepository;

    private List<Label> createDummyLabels() {
        List<Label> allLabels = new ArrayList<>();
        allLabels.add(new Label("1", "label1", "#FFFFFF"));
        allLabels.add(new Label("2", "label2", "#FFFFFF"));
        allLabels.add(new Label("3", "label3", "#FFFFFF"));
        allLabels.add(new Label("4", "label4", "#FFFFFF"));
        allLabels.add(new Label("5", "label5", "#FFFFFF"));
        return allLabels;
    }

    @Test
    void should_return_all_labels_when_getAll_given_labels_not_empty() {
        //given
        final List<Label> expected = createDummyLabels();
        when(labelRepository.findAll()).thenReturn(expected);
                
        //when
        final List<Label> actual = labelService.getAll();
        
        //then
        assertEquals(createDummyLabels().size(), actual.size());
    }
}
