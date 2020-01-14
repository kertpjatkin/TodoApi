package in.pjatk.todoapi.useCases.createTodo;

import in.pjatk.todoapi.domain.entities.Todo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateTodoResponse {

    private final Todo todo;

}
