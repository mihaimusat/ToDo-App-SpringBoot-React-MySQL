package com.endava.todo.service;

import com.endava.todo.exceptions.NotFoundException;
import com.endava.todo.model.ToDoItem;
import com.endava.todo.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public List<ToDoItem> getAllItems() {
        return toDoRepository.findAll();
    }

    public ToDoItem getItem(Long id) {
        Optional<ToDoItem> itemOptional = toDoRepository.findById(id);
        return itemOptional.orElseThrow(() ->
                new NotFoundException("The requested item was not found", "item.not.found"));
    }

    public ToDoItem saveItem(ToDoItem item) {
        return toDoRepository.save(item);
    }

    public ToDoItem updateItem(Long id, ToDoItem todoItem) {
        Optional<ToDoItem> itemOptional = toDoRepository.findById(id);
        return itemOptional.map(
                (item) -> {
                    item.setTitle(todoItem.getTitle());
                    item.setPriority(todoItem.getPriority());
                    item.setCompleted(todoItem.getCompleted());
                    item.setDueDate(todoItem.getDueDate());
                    return toDoRepository.save(item);
                }).orElseThrow(() ->
                        new NotFoundException("The requested item was not found", "item.not.found")
        );
    }

    public void deleteItem(Long id) {
        Optional<ToDoItem> itemOptional = toDoRepository.findById(id);
        itemOptional.ifPresentOrElse(
                (item) -> toDoRepository.deleteById(id),
                () -> {
                    throw new NotFoundException("The requested item was not found", "item.not.found");
                });
    }
}
