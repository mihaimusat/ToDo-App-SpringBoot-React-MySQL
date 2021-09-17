package com.endava.todo.controller.mapper;

import com.endava.todo.controller.dto.ToDoItemDto;
import com.endava.todo.model.ToDoItem;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ToDoConverter {

    public ToDoItemDto fromEntityToDto(ToDoItem entity) {
        ToDoItemDto dto = new ToDoItemDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setPriority(entity.getPriority());
        dto.setCompleted(entity.getCompleted());
        dto.setDueDate(entity.getDueDate());

        return dto;
    }

    public ToDoItem fromDtoToEntity(ToDoItemDto dto) {
        ToDoItem entity = new ToDoItem();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setPriority(dto.getPriority());
        entity.setCompleted(dto.getCompleted());
        entity.setDueDate(dto.getDueDate());

        return entity;
    }

    public List<ToDoItemDto> fromEntitiesToDtos(List<ToDoItem> entities) {
        if(CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }
        return entities.stream().map(this::fromEntityToDto).collect(Collectors.toList());
    }

    public List<ToDoItem> fromDtosToEntities(List<ToDoItemDto> dtos) {
        if(CollectionUtils.isEmpty(dtos)) {
            return Collections.emptyList();
        }
        return dtos.stream().map(this::fromDtoToEntity).collect(Collectors.toList());
    }
}
