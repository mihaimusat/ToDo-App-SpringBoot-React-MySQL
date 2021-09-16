package com.endava.todo.model;

public enum Priority {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");

    private final String value;

    private Priority(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
