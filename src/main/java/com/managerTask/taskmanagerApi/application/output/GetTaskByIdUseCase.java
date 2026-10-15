package com.managerTask.taskmanagerApi.application.output;

import com.managerTask.taskmanagerApi.domain.TaskId;
import com.managerTask.taskmanagerApi.domain.TaskNotFoundException;
import com.managerTask.taskmanagerApi.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class GetTaskByIdUseCase {
    private final TaskRepository repository;

    public GetTaskByIdUseCase(TaskRepository repository){
        this.repository = repository;
    }

    public TaskOutput execute(TaskId id){
        return repository.findById(id).map(TaskOutput::from).orElseThrow(() -> new TaskNotFoundException(id));
    }
}
