package com.managerTask.taskmanagerApi.infrastructure.http;

import com.managerTask.taskmanagerApi.application.output.GetTaskByIdUseCase;
import com.managerTask.taskmanagerApi.domain.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTaskNotFoundException(TaskNotFoundException ex){
        return ex.getMessage();
    }
}
