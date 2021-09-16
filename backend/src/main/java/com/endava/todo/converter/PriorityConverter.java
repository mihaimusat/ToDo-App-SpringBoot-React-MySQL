package com.endava.todo.converter;

import com.endava.todo.model.Priority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PriorityConverter implements AttributeConverter<Priority, String> {

    @Override
    public String convertToDatabaseColumn(Priority priority) {
        if(priority == null) {
            return null;
        }

        return priority.getValue();
    }

    @Override
    public Priority convertToEntityAttribute(String value) {
        if(value == null) {
            return null;
        }

        return Stream.of(Priority.values())
                .filter(p -> p.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
