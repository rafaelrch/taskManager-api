package com.managerTask.taskmanagerApi.application.input;

import com.managerTask.taskmanagerApi.domain.TaskStatus;

import java.util.Optional;

public record UpdateTaskInput(Optional<String> title,
                              Optional<String> descriptional,
                              Optional<TaskStatus> status) {

}
