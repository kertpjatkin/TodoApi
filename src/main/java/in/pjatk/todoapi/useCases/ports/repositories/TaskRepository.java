package in.pjatk.todoapi.useCases.ports.repositories;

import in.pjatk.todoapi.domain.entities.Task;

public interface TaskRepository {

    Task save(Task todo);

}
