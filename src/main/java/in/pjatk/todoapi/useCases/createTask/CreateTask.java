package in.pjatk.todoapi.useCases.createTask;

import in.pjatk.todoapi.domain.entities.Task;
import in.pjatk.todoapi.useCases.ports.Notifier;
import in.pjatk.todoapi.useCases.ports.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateTask implements CreateTaskUseCase {

    private final TaskRepository repository;
    private final Notifier notifier;

    @Override
    public CreateTaskResponse execute(CreateTaskCommand command) {
        var todo = Task.builder().description(command.getDescription()).build();

        repository.save(todo);

        notifier.notify(todo.getId());

        return CreateTaskResponse.builder().todo(todo).build();
    }
}
