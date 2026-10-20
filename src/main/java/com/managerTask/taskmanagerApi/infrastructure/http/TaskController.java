package com.managerTask.taskmanagerApi.infrastructure.http;

import com.managerTask.taskmanagerApi.application.CreateTaskUseCase;
import com.managerTask.taskmanagerApi.application.input.CreateTaskInput;
import com.managerTask.taskmanagerApi.infrastructure.http.request.CreateTaskRequest;
import com.managerTask.taskmanagerApi.infrastructure.http.response.TaskResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
    }

    @PostMapping
    TaskResponse create(@RequestBody CreateTaskRequest request){
        var input = request.toInput();
        var output = createTaskUseCase.execute(input);
        return TaskResponse.from(output);
    }
}
