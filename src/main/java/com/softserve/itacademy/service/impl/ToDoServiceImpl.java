package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.service.ToDoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository todoRepository;

    public ToDoServiceImpl(ToDoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public ToDo create(ToDo todo) {
        if (!todo.getTitle().isEmpty()) {
            return todoRepository.save(todo);
        }
        throw new NullEntityReferenceException("ToDo can't be 'null'!");
    }

    @Override
    public ToDo readById(long id) {
        Optional<ToDo> optional = todoRepository.findById(id);
            return optional.get();
    }

    @Override
    public ToDo update(ToDo todo) {
        if (!todo.getTitle().isEmpty()) {
            if (todoRepository.findById(todo.getId()).isPresent()) {
                return todoRepository.save(todo);
            } else {
                throw new EntityNotFoundException("Such ToDo doesn't exist!");
            }
        }
        throw new NullEntityReferenceException("ToDo can't be 'null'!");
    }

    @Override
    public void delete(long id) {
        ToDo todo = readById(id);
            todoRepository.delete(todo);
    }

    @Override
    public List<ToDo> getAll() {
        List<ToDo> todos = todoRepository.findAll();
        return todos.isEmpty() ? new ArrayList<>() : todos;
    }

    @Override
    public List<ToDo> getByUserId(long userId) {
        List<ToDo> todos = todoRepository.getByUserId(userId);
        return todos.isEmpty() ? new ArrayList<>() : todos;
    }
}
