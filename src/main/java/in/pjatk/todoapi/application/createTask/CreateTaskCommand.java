package in.pjatk.todoapi.application.createTask;

import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CreateTaskCommand {

    @NotEmpty(message = "description cannot be empty")
    private final String description;

}
