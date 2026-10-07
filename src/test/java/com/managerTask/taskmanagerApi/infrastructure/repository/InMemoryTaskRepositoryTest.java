package com.managerTask.taskmanagerApi.infrastructure.repository;

import com.managerTask.taskmanagerApi.domain.Task;
import com.managerTask.taskmanagerApi.domain.TaskId;
import com.managerTask.taskmanagerApi.domain.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class InMemoryTaskRepositoryTest {

    private InMemoryTaskRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
    }

    @Test
    void save_shouldReturnSavedTask() {
        Task task = new Task("Estudar Java", Optional.of("Ler sobre records"));

        Task saved = repository.save(task);

        assertThat(saved).isSameAs(task);
    }

    @Test
    void save_shouldStoreTaskSoItCanBeFoundById() {
        Task task = new Task("Estudar Java", Optional.empty());

        repository.save(task);

        assertThat(repository.findById(task.getId())).containsSame(task);
    }

    @Test
    void save_withSameId_shouldOverwriteExistingTask() {
        Task task = new Task("Estudar Java", Optional.empty());
        repository.save(task);

        task.setStatus(TaskStatus.IN_PROGRESS);
        repository.save(task);

        assertThat(repository.findAll()).hasSize(1);
        assertThat(repository.findById(task.getId()))
                .get()
                .extracting(Task::getStatus)
                .isEqualTo(TaskStatus.IN_PROGRESS);
    }

    @Test
    void findAll_whenEmpty_shouldReturnEmptyList() {
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void findAll_shouldReturnAllSavedTasks() {
        Task first = new Task("Tarefa 1", Optional.empty());
        Task second = new Task("Tarefa 2", Optional.empty());
        repository.save(first);
        repository.save(second);

        assertThat(repository.findAll()).containsExactlyInAnyOrder(first, second);
    }

    @Test
    void findAll_shouldReturnCopyThatDoesNotAffectStorage() {
        repository.save(new Task("Tarefa 1", Optional.empty()));

        repository.findAll().clear();

        assertThat(repository.findAll()).hasSize(1);
    }

    @Test
    void findById_whenTaskDoesNotExist_shouldReturnEmpty() {
        assertThat(repository.findById(new TaskId())).isEmpty();
    }

    @Test
    void findById_shouldMatchByIdValueNotReference() {
        Task task = new Task("Tarefa 1", Optional.empty());
        repository.save(task);

        TaskId sameIdValue = new TaskId(task.getId().id());

        assertThat(repository.findById(sameIdValue)).containsSame(task);
    }

    @Test
    void delete_shouldRemoveTask() {
        Task task = new Task("Tarefa 1", Optional.empty());
        repository.save(task);

        repository.delete(task.getId());

        assertThat(repository.findById(task.getId())).isEmpty();
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void delete_shouldOnlyRemoveTheGivenTask() {
        Task kept = new Task("Fica", Optional.empty());
        Task removed = new Task("Sai", Optional.empty());
        repository.save(kept);
        repository.save(removed);

        repository.delete(removed.getId());

        assertThat(repository.findAll()).containsExactly(kept);
    }

    @Test
    void delete_whenTaskDoesNotExist_shouldNotThrow() {
        assertThatNoException().isThrownBy(() -> repository.delete(new TaskId()));
    }
}
