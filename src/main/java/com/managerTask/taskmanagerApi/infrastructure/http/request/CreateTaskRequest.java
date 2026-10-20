package com.managerTask.taskmanagerApi.infrastructure.http.request;

import com.managerTask.taskmanagerApi.application.input.CreateTaskInput;

import java.util.Optional;

public record CreateTaskRequest(String title, Optional<String> description) {

    public CreateTaskInput toInput(){
        return new CreateTaskInput(title, description);
    }

}
