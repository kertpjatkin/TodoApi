package in.pjatk.todoapi.application.createTask;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import in.pjatk.todoapi.application.exceptions.InvalidCommandException;
import in.pjatk.todoapi.application.ports.Notifier;
import in.pjatk.todoapi.application.ports.TaskRepository;
import in.pjatk.todoapi.domain.Task;
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
class CreateTaskServiceTest {

    private static final String SAVED_TASK_ID = "savedTaskId";

    private CreateTaskService createTaskService;

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
        createTaskService = new CreateTaskService(taskRepository, notifier,
            validator);
        when(taskRepository.save(any())).thenReturn(task);
        when(task.getId()).thenReturn(SAVED_TASK_ID);
    }

    @Test
    void withCorrectCommand_createsTask() {
        var description = "taskDescription";
        var command = CreateTaskCommand.builder().description(description).build();
        createTaskService.createTask(command);

        var order = inOrder(taskRepository, notifier);
        order.verify(taskRepository, times(1)).save(any());
        order.verify(notifier, times(1)).notify(eq(SAVED_TASK_ID));
    }

    @Test
    void withNoDescription_throwsInvalidCommandException() {
        var command = CreateTaskCommand.builder().build();
        assertThrows(InvalidCommandException.class,
            () -> createTaskService.createTask(command));
    }

    @Test
    void withEmptyDescription_throwsInvalidCommandException() {
        var command = CreateTaskCommand.builder().description("").build();
        assertThrows(InvalidCommandException.class,
            () -> createTaskService.createTask(command));
    }
}
