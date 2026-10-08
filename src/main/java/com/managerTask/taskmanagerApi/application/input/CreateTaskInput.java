package com.managerTask.taskmanagerApi.application.input;

import java.util.Optional;

public record CreateTaskInput(String title, Optional<String> description) {
}
