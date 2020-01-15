package in.pjatk.todoapi.useCases.createTask;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import in.pjatk.todoapi.domain.entities.Task;
import in.pjatk.todoapi.useCases.exceptions.InvalidCommandException;
import in.pjatk.todoapi.useCases.ports.Notifier;
import in.pjatk.todoapi.useCases.ports.repositories.TaskRepository;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CreateTaskTest {

    private static final String SAVED_TASK_ID = "savedTaskId";

    private CreateTask createTask;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private Notifier notifier;

    @Mock
    private Task task;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        var validator = factory.getValidator();
        createTask = new CreateTask(taskRepository, notifier, validator);
        when(taskRepository.save(any())).thenReturn(task);
        when(task.getId()).thenReturn(SAVED_TASK_ID);
    }

    @Test
    void withCorrectCommand_createsTask() {
        var description = "taskDescription";
        var command = CreateTaskCommand.builder().description(description).build();
        createTask.execute(command);

        var order = inOrder(taskRepository, notifier);
        order.verify(taskRepository, times(1)).save(any());
        order.verify(notifier, times(1)).notify(eq(SAVED_TASK_ID));
    }

    @Test
    void withNoDescription_throwsInvalidCommandException() {
        var command = CreateTaskCommand.builder().build();
        assertThrows(InvalidCommandException.class, () -> createTask.execute(command));
    }

    @Test
    void withEmptyDescription_throwsInvalidCommandException() {
        var command = CreateTaskCommand.builder().description("").build();
        assertThrows(InvalidCommandException.class, () -> createTask.execute(command));
    }
}
