package com.endava.todo.controller.dto;

import com.endava.todo.model.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ToDoItemDto {

    private Long id;

    @NotNull(message = "The title cannot be empty")
    private String title;

    @NotNull(message = "The priority cannot be empty")
    private Priority priority;

    @NotNull(message = "The completion status cannot be empty")
    private Boolean completed;

    @NotNull(message = "The due date cannot be empty")
    private LocalDate dueDate;
}
