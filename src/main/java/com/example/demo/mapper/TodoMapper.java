package com.example.demo.mapper;

import com.example.demo.model.Todo;
import com.example.demo.dto.TodoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    TodoDTO todoToTodoDTO(Todo todo);
    Todo todoDTOToTodo(TodoDTO todoDTO);
}
