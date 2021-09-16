package com.endava.todo.controller;

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

    @GetMapping(value = "")
    public List<ToDoItem> getAllItems() {
        return toDoService.getAllItems();
    }

    @GetMapping(value = "/{id}")
    public ToDoItem getItem(@PathVariable Long id) {
        return toDoService.getItem(id);
    }

    @PostMapping(value = "")
    public ToDoItem saveItem(@Valid @RequestBody ToDoItem item) {
        return toDoService.saveItem(item);
    }

    @PutMapping(value = "/{id}")
    public ToDoItem updateItem(@PathVariable Long id, @Valid @RequestBody ToDoItem todoItem) {
        return toDoService.updateItem(id, todoItem);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteItem(@PathVariable Long id) {
        toDoService.deleteItem(id);
    }
}
