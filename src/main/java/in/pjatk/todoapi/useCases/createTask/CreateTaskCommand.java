package in.pjatk.todoapi.useCases.createTask;

import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateTaskCommand {

    @NotEmpty(message = "description cannot be empty")
    private final String description;

    @Override
    public String toString() {
        return "CreateTaskCommand{" +
            "description='" + description + '\'' +
            '}';
    }
}
