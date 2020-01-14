package in.pjatk.todoapi.application.adapters.db;

import static lombok.Lombok.checkNotNull;

import in.pjatk.todoapi.application.adapters.db.mongo.MongoTodoRepository;
import in.pjatk.todoapi.domain.entities.Todo;
import in.pjatk.todoapi.useCases.ports.repositories.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TodoRepositoryImplementation implements TodoRepository {

    private final MongoTodoRepository repository;

    @Override
    public Todo save(Todo todo) {
        checkNotNull(todo, "todo cannot be null");

        return repository.save(todo);
    }
}
