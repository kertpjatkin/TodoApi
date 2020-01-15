package in.pjatk.todoapi.useCases.createTask;

import static in.pjatk.todoapi.useCases.helpers.ValidatorErrorsMapper.mapValidationErrors;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import in.pjatk.todoapi.domain.entities.Task;
import in.pjatk.todoapi.useCases.exceptions.InvalidCommandException;
import in.pjatk.todoapi.useCases.ports.Notifier;
import in.pjatk.todoapi.useCases.ports.repositories.TaskRepository;
import javax.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class CreateTask implements CreateTaskUseCase {

    private final TaskRepository repository;
    private final Notifier notifier;
    private final Validator validator;

    @Override
    public CreateTaskResponse execute(CreateTaskCommand command) {
        var errors = validator.validate(command);

        if (!isEmpty(errors)) {
            log.info("CreateTaskCommand validation did not pass. Command: {}", command);
            throw new InvalidCommandException(mapValidationErrors(errors));
        }

        log.debug("Creating task with description {}", command.getDescription());
        var task = Task.builder().description(command.getDescription()).build();

        task = repository.save(task);
        log.info("Created task {}", task.getId());
        notifier.notify(task.getId());

        return CreateTaskResponse.builder().task(task).build();
    }
}
