package com.imc4k.todolist.service;

import com.imc4k.todolist.model.Label;
import com.imc4k.todolist.repository.LabelRepository;
import exception.InvalidColorException;
import exception.LabelAlreadyExistException;
import exception.LabelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

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

        Optional<List<Label>> labelsWithSameText = labelRepository.findAllByText(newLabel.getText());
        if (labelsWithSameText.isPresent()) {
            throw new LabelAlreadyExistException();
        }

        return labelRepository.save(newLabel);
    }

    public Label update(Label updatedLabel) throws InvalidColorException {
        getById(updatedLabel.getId());
        return createLabel(updatedLabel);
    }
}
