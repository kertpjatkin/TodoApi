package in.pjatk.todoapi.useCases.createTodo;

import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class CreateTodoCommand {

    @NotEmpty(message = "description cannot be empty")
    private final String description;

}
