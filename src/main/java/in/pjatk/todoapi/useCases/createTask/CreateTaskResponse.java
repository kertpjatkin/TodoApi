package in.pjatk.todoapi.useCases.createTask;

import in.pjatk.todoapi.domain.entities.Task;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateTaskResponse {

    private final Task task;

}
