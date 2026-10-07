package com.managerTask.taskmanagerApi.infrastructure.repository;

import com.managerTask.taskmanagerApi.domain.Task;
import com.managerTask.taskmanagerApi.domain.TaskId;
import com.managerTask.taskmanagerApi.domain.TaskRepository;

import java.util.*;

public class InMemoryTaskRepository implements TaskRepository {

    private final Map<TaskId, Task> storage = new HashMap<>();

    @Override
    public Task save(Task task) {
        storage.put(task.getId(), task);
        return task;
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Task> findById(TaskId id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(TaskId id) {
        storage.remove(id);
    }
}
