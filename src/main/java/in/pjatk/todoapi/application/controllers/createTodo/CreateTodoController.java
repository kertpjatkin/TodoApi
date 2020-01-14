package in.pjatk.todoapi.application.controllers.createTodo;

import static in.pjatk.todoapi.application.controllers.helpers.ResponseUtil.buildBadResponse;
import static in.pjatk.todoapi.application.controllers.helpers.ResponseUtil.buildOkResponse;
import static in.pjatk.todoapi.application.controllers.helpers.ResponseUtil.mapValidationErrors;

import in.pjatk.todoapi.useCases.createTodo.CreateTodoCommand;
import in.pjatk.todoapi.useCases.createTodo.CreateTodoUseCase;
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
public class CreateTodoController {

    private final CreateTodoUseCase useCase;
    private final ModelMapper modelMapper;

    @PostMapping("/todos")
    public ResponseEntity post(
        @Valid @RequestBody CreateTodoRequestBody requestBody, BindingResult result) {
        if (result.hasErrors()) {
            return buildBadResponse(mapValidationErrors(result.getAllErrors()));
        }

        var createTodoResponse = useCase
            .execute(CreateTodoCommand.builder().description(requestBody.getDescription()).build());

        return buildOkResponse(
            CreateTodoResponse.toResponse(modelMapper, createTodoResponse.getTodo()));
    }
}
