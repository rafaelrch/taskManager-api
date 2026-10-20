package com.managerTask.taskmanagerApi.infrastructure.http.request;
import com.managerTask.taskmanagerApi.application.input.UpdateTaskInput;
import com.managerTask.taskmanagerApi.domain.TaskStatus;

import java.util.Optional;

public record UpdateTaskRequest(
        Optional<String> title,
        Optional<String> description,
        Optional<String> status
) {
    public UpdateTaskInput toInput(){
        return new UpdateTaskInput(title, description, status.map(TaskStatus::valueOf));
    }
}
