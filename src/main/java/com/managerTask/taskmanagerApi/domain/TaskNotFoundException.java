package com.managerTask.taskmanagerApi.domain;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(TaskId id) {
        super("Task with id " + id + "not found");
    }
}
