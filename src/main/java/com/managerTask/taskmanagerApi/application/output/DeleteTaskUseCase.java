package com.managerTask.taskmanagerApi.application.output;

import com.managerTask.taskmanagerApi.domain.TaskId;
import com.managerTask.taskmanagerApi.domain.TaskNotFoundException;
import com.managerTask.taskmanagerApi.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskUseCase {

    private final TaskRepository repository;

    public DeleteTaskUseCase(TaskRepository repository){
        this.repository = repository;
    }

    public void execute(TaskId taskId){
        if (repository.findById(taskId).isEmpty()){
            throw  new TaskNotFoundException(taskId);
        }

        repository.delete(taskId);
    }
}


