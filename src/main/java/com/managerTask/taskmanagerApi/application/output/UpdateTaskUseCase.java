package com.managerTask.taskmanagerApi.application.output;

import com.managerTask.taskmanagerApi.application.input.UpdateTaskInput;
import com.managerTask.taskmanagerApi.domain.TaskId;
import com.managerTask.taskmanagerApi.domain.TaskNotFoundException;
import com.managerTask.taskmanagerApi.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskUseCase {

    private final TaskRepository repository;

    public UpdateTaskUseCase(TaskRepository repository){
        this.repository = repository;
    }

    public TaskOutput execute(TaskId id, UpdateTaskInput input){
        var task = repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        task.update(input.title(), input.descriptional(), input.status());
        var updated = repository.save(task);
        return  TaskOutput.from(updated);
    }
}
