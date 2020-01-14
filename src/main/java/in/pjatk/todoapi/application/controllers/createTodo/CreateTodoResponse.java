package in.pjatk.todoapi.application.controllers.createTodo;

import in.pjatk.todoapi.application.controllers.dtos.TodoDTO;
import in.pjatk.todoapi.domain.entities.Todo;
import lombok.Builder;
import lombok.Getter;
import org.modelmapper.ModelMapper;

@Getter
@Builder
class CreateTodoResponse {

    private final TodoDTO todo;

    public static CreateTodoResponse toResponse(ModelMapper modelMapper, Todo todo) {
        return CreateTodoResponse.builder().todo(modelMapper.map(todo, TodoDTO.class)).build();
    }
}
