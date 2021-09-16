package com.endava.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The title cannot be empty")
    @Column(name = "title")
    private String title;

    @NotNull(message = "The priority cannot be empty")
    @Column(name = "priority")
    private Priority priority;

    @NotNull(message = "The completion status cannot be empty")
    @Column(name = "completed")
    private Boolean completed;

    @NotNull(message = "The due date cannot be empty")
    @Column(name = "due_date")
    private LocalDate dueDate;
}
