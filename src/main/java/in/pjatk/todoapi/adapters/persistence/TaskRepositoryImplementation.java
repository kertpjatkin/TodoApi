package in.pjatk.todoapi.adapters.persistence;

import static lombok.Lombok.checkNotNull;

import in.pjatk.todoapi.application.ports.TaskRepository;
import in.pjatk.todoapi.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TaskRepositoryImplementation implements TaskRepository {

    private final MongoTodoRepository repository;

    @Override
    public Task save(Task todo) {
        checkNotNull(todo, "todo cannot be null");

        return repository.save(todo);
    }
}
