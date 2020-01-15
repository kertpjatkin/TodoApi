package in.pjatk.todoapi.application.controllers.createTask;

import static in.pjatk.todoapi.application.controllers.helpers.ResponseUtil.buildBadResponse;
import static in.pjatk.todoapi.application.controllers.helpers.ResponseUtil.buildOkResponse;
import static in.pjatk.todoapi.application.controllers.helpers.ResponseUtil.mapValidationErrors;

import in.pjatk.todoapi.useCases.createTask.CreateTaskCommand;
import in.pjatk.todoapi.useCases.createTask.CreateTaskUseCase;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateTaskController {

    private final CreateTaskUseCase useCase;
    private final ModelMapper modelMapper;

    @PostMapping("/tasks")
    public ResponseEntity post(
        @Valid @RequestBody CreateTaskRequestBody requestBody, BindingResult result) {
        if (result.hasErrors()) {
            return buildBadResponse(mapValidationErrors(result.getAllErrors()));
        }

        var createTodoResponse = useCase
            .execute(CreateTaskCommand.builder().description(requestBody.getDescription()).build());

        return buildOkResponse(
            CreateTaskResponse.toResponse(modelMapper, createTodoResponse.getTodo()));
    }
}
