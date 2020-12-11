package com.imc4k.todolist.service;

import com.imc4k.todolist.model.Label;
import com.imc4k.todolist.repository.LabelRepository;
import com.imc4k.todolist.exception.InvalidColorException;
import com.imc4k.todolist.exception.LabelAlreadyExistException;
import com.imc4k.todolist.exception.LabelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private TodoService todoService;

    public List<Label> getAll() {
        return labelRepository.findAll();
    }

    public Label getById(String id) {
        return labelRepository.findById(id).orElseThrow(LabelNotFoundException::new);
    }

    public Label createLabel(Label newLabel) throws InvalidColorException {
        if (!newLabel.getColor().matches("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$")) {
            throw new InvalidColorException();
        }

        List<Label> labelsWithSameText = labelRepository.findAllByText(newLabel.getText());
        if (!labelsWithSameText.isEmpty() && !labelsWithSameText.get(0).getId().equals(newLabel.getId())) {
            throw new LabelAlreadyExistException();
        }

        return labelRepository.save(newLabel);
    }

    public Label update(Label updatedLabel) throws InvalidColorException {
        getById(updatedLabel.getId());
        return createLabel(updatedLabel);
    }

    public void delete(String id) {
        getById(id);
        labelRepository.deleteById(id);
        todoService.removeLabelId(id);
    }
}
