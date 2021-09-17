package com.endava.todo.controller;

import com.endava.todo.controller.dto.ToDoItemDto;
import com.endava.todo.controller.mapper.ToDoConverter;
import com.endava.todo.model.ToDoItem;
import com.endava.todo.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/todo")
@AllArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;
    private final ToDoConverter toDoConverter;

    @GetMapping(value = "")
    public List<ToDoItemDto> getAllItems() {
        List<ToDoItem> toDoList = toDoService.getAllItems();
        return toDoConverter.fromEntitiesToDtos(toDoList);
    }

    @GetMapping(value = "/{id}")
    public ToDoItemDto getItem(@PathVariable Long id) {
        ToDoItem todo = toDoService.getItem(id);
        return toDoConverter.fromEntityToDto(todo);
    }

    @PostMapping(value = "")
    public ToDoItemDto saveItem(@Valid @RequestBody ToDoItemDto todoDto) {
        ToDoItem todo = toDoConverter.fromDtoToEntity(todoDto);
        ToDoItem savedTodo = toDoService.saveItem(todo);
        return toDoConverter.fromEntityToDto(savedTodo);
    }

    @PutMapping(value = "/{id}")
    public ToDoItemDto updateItem(@PathVariable Long id, @Valid @RequestBody ToDoItemDto todoDto) {
        ToDoItem todo = toDoConverter.fromDtoToEntity(todoDto);
        ToDoItem updatedTodo = toDoService.updateItem(id, todo);
        return toDoConverter.fromEntityToDto(updatedTodo);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteItem(@PathVariable Long id) {
        toDoService.deleteItem(id);
    }
}
