package in.pjatk.todoapi.adapters.controllers.createTask;

import in.pjatk.todoapi.adapters.controllers.dtos.TaskDTO;
import in.pjatk.todoapi.domain.Task;
import lombok.Builder;
import lombok.Getter;
import org.modelmapper.ModelMapper;

@Getter
@Builder
class CreateTaskResponse {

    private final TaskDTO task;

    static CreateTaskResponse toResponse(ModelMapper modelMapper, Task task) {
        return CreateTaskResponse.builder().task(modelMapper.map(task, TaskDTO.class)).build();
    }
}
