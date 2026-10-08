package com.managerTask.taskmanagerApi.application;

import com.managerTask.taskmanagerApi.application.input.CreateTaskInput;
import com.managerTask.taskmanagerApi.application.output.TaskOutput;
import com.managerTask.taskmanagerApi.domain.Task;
import com.managerTask.taskmanagerApi.domain.TaskRepository;
import com.managerTask.taskmanagerApi.infrastructure.repository.InMemoryTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateTaskUseCaseTest {

    CreateTaskUseCase useCase;

    @BeforeEach
    void setUp() {
        this.useCase = new CreateTaskUseCase(new InMemoryTaskRepository());
    }

    @Test
    void should_create_task_successfully(){
        // given

        var input = new CreateTaskInput("Estudar Java", Optional.of("Finalizar o módulo de Records"));

        // when
        TaskOutput output = useCase.execute(input);

        // then
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals("Estudar Java", output.title());
        assertEquals(Optional.of("Finalizar o módulo de Records"), output.description());
    }

}