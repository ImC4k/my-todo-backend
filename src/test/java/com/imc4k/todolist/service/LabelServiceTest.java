package com.imc4k.todolist.service;

import com.imc4k.todolist.model.Label;
import com.imc4k.todolist.repository.LabelRepository;
import exception.InvalidColorException;
import exception.LabelAlreadyExistException;
import exception.LabelNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

    @Test
    void should_return_required_label_when_getById_given_a_valid_label_id() {
        //given
        final List<Label> allTodos = createDummyLabels();
        when(labelRepository.findById(anyString())).thenReturn(Optional.of(allTodos.get(1)));

        //when
        final Label actual = labelService.getById("2");

        //then
        assertEquals(allTodos.get(1), actual);
    }

    @Test
    void should_throw_LabelNotFoundException_when_getById_given_invalid_label_id() {
        //given
        when(labelRepository.findById(anyString())).thenReturn(Optional.empty());

        //when
        Exception exception = assertThrows(LabelNotFoundException.class, ()-> labelService.getById("invalid id"));

        //then
        assertEquals("Label not found", exception.getMessage());
    }

    @Test
    void should_return_newly_created_label_when_createLabel_given_valid_label_info() throws InvalidColorException {
        //given
        Label labelRequest = new Label("test", "#0F0F0F");
        Label expected = new Label("test", "#0F0F0F");
        when(labelRepository.save(labelRequest)).thenReturn(expected);

        //when
        labelService.createLabel(labelRequest);
        final ArgumentCaptor<Label>  labelArgumentCaptor = ArgumentCaptor.forClass(Label.class);
        verify(labelRepository, times(1)).save(labelArgumentCaptor.capture());

        //then
        final Label actual = labelArgumentCaptor.getValue();
        assertEquals(expected.getText(), actual.getText());
        assertEquals(expected.getColor(), actual.getColor());
    }

    @Test
    void should_throw_InvalidColorException_when_createLabel_given_invalid_color_hex_code() {
        //given
        Label labelRequest = new Label("test", "invalid color code");

        //when
        Exception exception = assertThrows(InvalidColorException.class, ()-> labelService.createLabel(labelRequest));

        //then
        assertEquals("Invalid color", exception.getMessage());
    }

    @Test
    void should_throw_LabelAlreadyExistException_when_createLabel_given_label_text_already_exist_in_repository() {
        //given
        Label labelRequest = new Label("test", "#000000");
        when(labelRepository.findAllByText(labelRequest.getText())).thenReturn(Optional.of(Stream.of(new Label("test", "#333333")).collect(Collectors.toList())));

        //when
        Exception exception = assertThrows(LabelAlreadyExistException.class, ()-> labelService.createLabel(labelRequest));

        //then
        assertEquals("Label already exist", exception.getMessage());
    }

    @Test
    void should_return_updated_label_when_update_given_valid_id_and_info() throws InvalidColorException {
        //given
        Label updatedLabel = new Label("1", "updated", "#000000");
        Label original = new Label("1", "original", "#000000");
        Label expected = new Label("1", "updated", "#000000");
        when(labelRepository.findById("1")).thenReturn(Optional.of(original));

        //when
        labelService.update(updatedLabel);
        final ArgumentCaptor<Label> labelArgumentCaptor = ArgumentCaptor.forClass(Label.class);
        verify(labelRepository, times(1)).save(labelArgumentCaptor.capture());

        //then
        final Label actual = labelArgumentCaptor.getValue();
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void should_throw_LabelNotFoundException_when_update_given_invalid_id() {
        //given
        Label updatedLabel = new Label("invalid id", "updated", "#000000");
        when(labelRepository.findById(anyString())).thenReturn(Optional.empty());

        //when
        Exception exception = assertThrows(LabelNotFoundException.class, ()-> labelService.update(updatedLabel));

        //then
        assertEquals("Label not found", exception.getMessage());
    }

    @Test
    void should_throw_InvalidColorException_when_update_given_valid_id_but_invalid_color_code() {
        //given
        Label updatedLabel = new Label("1", "updated", "invalid color code");
        Label original = new Label("1", "original", "#000000");
        when(labelRepository.findById("1")).thenReturn(Optional.of(original));

        //when
        Exception exception = assertThrows(InvalidColorException.class, ()-> labelService.update(updatedLabel));

        //then
        assertEquals("Invalid color", exception.getMessage());
    }

    @Test
    void should_throw_LabelAlreadyExistException_when_update_given_valid_id_but_text_already_exist() {
        //given
        Label updatedLabel = new Label("1", "updated", "#3a3a3a");
        Label original = new Label("1", "original", "#000000");
        when(labelRepository.findById(updatedLabel.getId())).thenReturn(Optional.of(original));
        when(labelRepository.findAllByText(updatedLabel.getText())).thenReturn(Optional.of(Stream.of(new Label("2", "updated", "#000000")).collect(Collectors.toList())));

        //when
        Exception exception = assertThrows(LabelAlreadyExistException.class, ()-> labelService.update(updatedLabel));

        //then
        assertEquals("Label already exist", exception.getMessage());
    }

    @Test
    void should_call_repository_deleteById_when_delete_given_valid_id() {
        //given
        Label labelInRepository = new Label("1", "text", "#000000");
        when(labelRepository.findById(labelInRepository.getId())).thenReturn(Optional.of(labelInRepository));

        //when
        labelService.delete(labelInRepository.getId());

        //then
        verify(labelRepository, times(1)).deleteById(labelInRepository.getId());
    }

    @Test
    void should_throw_LabelNotFoundException_when_delete_given_invalid_id() {
        //given
        when(labelRepository.findById(anyString())).thenReturn(Optional.empty());

        //when
        Exception exception = assertThrows(LabelNotFoundException.class, ()-> labelService.delete("invalid id"));

        //then
        assertEquals("Label not found", exception.getMessage());
    }
}
