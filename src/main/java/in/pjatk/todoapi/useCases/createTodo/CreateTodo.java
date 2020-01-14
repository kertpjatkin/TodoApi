package in.pjatk.todoapi.useCases.createTodo;

import in.pjatk.todoapi.domain.entities.Todo;
import in.pjatk.todoapi.useCases.ports.Notifier;
import in.pjatk.todoapi.useCases.ports.repositories.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateTodo implements CreateTodoUseCase {

    private final TodoRepository repository;
    private final Notifier notifier;

    @Override
    public CreateTodoResponse execute(CreateTodoCommand command) {
        var todo = Todo.builder().description(command.getDescription()).build();

        repository.save(todo);

        notifier.notify(todo.getId());

        return CreateTodoResponse.builder().todo(todo).build();
    }
}
