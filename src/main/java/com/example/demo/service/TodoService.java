package com.example.demo.service;

import com.example.demo.dto.TodoDTO;
import com.example.demo.mapper.TodoMapper;
import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoService {


    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    @Autowired
    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public List<TodoDTO> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(todoMapper::todoToTodoDTO)
                .collect(Collectors.toList());
    }

    public Optional<TodoDTO> getTodoById(Long id) {
        return todoRepository.findById(id)
                .map(todoMapper::todoToTodoDTO);
    }

    public TodoDTO createTodo(TodoDTO todoDTO) {
        log.info("Creating new Todo: {}", todoDTO.getTitle());
        Todo todo = todoMapper.todoDTOToTodo(todoDTO);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.todoToTodoDTO(savedTodo);
    }

    public TodoDTO updateTodo(Long id, TodoDTO todoDTO) {
        if (todoRepository.existsById(id)) {
            Todo todo = todoMapper.todoDTOToTodo(todoDTO);
            todo.setId(id);
            Todo updatedTodo = todoRepository.save(todo);
            return todoMapper.todoToTodoDTO(updatedTodo);
        }
        return null;
    }

    public boolean deleteTodoById(Long id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
