package in.pjatk.todoapi.useCases.ports.repositories;

import in.pjatk.todoapi.domain.entities.Todo;

public interface TodoRepository {

    Todo save(Todo todo);

}
