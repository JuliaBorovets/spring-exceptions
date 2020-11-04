package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;
import com.softserve.itacademy.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        if (!task.getName().isEmpty()) {
            return taskRepository.save(task);
        }
        throw new NullEntityReferenceException("Task can`t be 'null'!");
    }

    @Override
    public Task readById(long id) {
        Optional<Task> optional = taskRepository.findById(id);
            return optional.get();
    }

    @Override
    public Task update(Task task) {
            if (!task.getName().isEmpty()) {
            Task oldTask = readById(task.getId());
            if (oldTask != null) {
                return taskRepository.save(task);
            } else {
                throw new EntityNotFoundException("Task with such id doesn`t exist!");
            }
        }
        throw new NullEntityReferenceException("Task can`t be 'null'!");
    }

    @Override
    public void delete(long id) {
        Task task = readById(id);
            taskRepository.delete(task);
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.isEmpty() ? new ArrayList<>() : tasks;
    }

    @Override
    public List<Task> getByTodoId(long todoId) {
        List<Task> tasks = taskRepository.getByTodoId(todoId);
        return tasks.isEmpty() ? new ArrayList<>() : tasks;
    }
}
