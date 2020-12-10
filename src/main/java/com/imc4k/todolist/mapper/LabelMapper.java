package com.imc4k.todolist.mapper;

import com.imc4k.todolist.dto.LabelRequest;
import com.imc4k.todolist.model.Label;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class LabelMapper {
    public Label toEntity(LabelRequest labelRequest) {
        Label label = new Label();
        BeanUtils.copyProperties(labelRequest, label);
        return label;
    }

    public Label toEntity(String id, LabelRequest labelRequest) {
        Label label = toEntity(labelRequest);
        label.setId(id);
        return label;
    }
}
