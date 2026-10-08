package com.managerTask.taskmanagerApi.application;

import com.managerTask.taskmanagerApi.application.input.CreateTaskInput;
import com.managerTask.taskmanagerApi.application.output.TaskOutput;
import com.managerTask.taskmanagerApi.domain.Task;
import com.managerTask.taskmanagerApi.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskUseCase {

    private final TaskRepository repository;

    public CreateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskOutput execute(CreateTaskInput input){
        var task = new Task(input.title(), input.description());
        var saved = repository.save(task);
        return TaskOutput.from(saved);
    }
}
