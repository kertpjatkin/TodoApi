package in.pjatk.todoapi.application.createTask;

import in.pjatk.todoapi.domain.Task;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateTaskResponse {

    private final Task task;

}
