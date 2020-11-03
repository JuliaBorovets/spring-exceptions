package com.softserve.itacademy.service;

import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.model.Task;

import java.util.List;

public interface TaskService {
    Task create(Task task);
    Task readById(long id) throws EntityNotFoundException;
    Task update(Task task) throws EntityNotFoundException;
    void delete(long id) throws EntityNotFoundException;

    List<Task> getAll();
    List<Task> getByTodoId(long todoId);
}
