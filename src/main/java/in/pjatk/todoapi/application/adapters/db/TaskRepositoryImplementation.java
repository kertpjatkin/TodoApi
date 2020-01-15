package in.pjatk.todoapi.application.adapters.db;

import static lombok.Lombok.checkNotNull;

import in.pjatk.todoapi.application.adapters.db.mongo.MongoTodoRepository;
import in.pjatk.todoapi.domain.entities.Task;
import in.pjatk.todoapi.useCases.ports.repositories.TaskRepository;
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
