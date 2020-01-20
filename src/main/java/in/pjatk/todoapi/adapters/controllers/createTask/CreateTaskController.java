package in.pjatk.todoapi.adapters.controllers.createTask;

import static in.pjatk.todoapi.adapters.controllers.helpers.ResponseUtil.buildBadResponse;
import static in.pjatk.todoapi.adapters.controllers.helpers.ResponseUtil.buildOkResponse;
import static in.pjatk.todoapi.adapters.controllers.helpers.ResponseUtil.mapValidationErrors;

import in.pjatk.todoapi.application.createtask.CreateTaskCommand;
import in.pjatk.todoapi.application.createtask.CreateTaskUseCase;
import in.pjatk.todoapi.application.exceptions.InvalidCommandException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CreateTaskController {

    private final CreateTaskUseCase useCase;
    private final ModelMapper modelMapper;

    @PostMapping("/tasks")
    public ResponseEntity createTask(
        @Valid @RequestBody CreateTaskRequestBody requestBody, BindingResult result) {
        if (result.hasErrors()) {
            return buildBadResponse(mapValidationErrors(result.getAllErrors()));
        }

        try {
            var createTaskResponse = useCase
                .createTask(
                    CreateTaskCommand.builder().description(requestBody.getDescription()).build());

            return buildOkResponse(
                CreateTaskResponse.toResponse(modelMapper, createTaskResponse.getTask()));
        } catch (InvalidCommandException e) {
            log.warn("Unable to create task because command is invalid");
            return buildBadResponse();
        }
    }
}
