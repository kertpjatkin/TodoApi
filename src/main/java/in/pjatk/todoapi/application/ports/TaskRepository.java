package in.pjatk.todoapi.application.ports;

import in.pjatk.todoapi.domain.Task;

public interface TaskRepository {

    Task save(Task todo);

}
