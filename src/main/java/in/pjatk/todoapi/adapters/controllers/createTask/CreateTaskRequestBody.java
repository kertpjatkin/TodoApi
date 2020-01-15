package in.pjatk.todoapi.adapters.controllers.createTask;

import in.pjatk.todoapi.adapters.controllers.helpers.validators.NoHtml;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class CreateTaskRequestBody {

    @NotEmpty(message = "description cannot be empty")
    @NoHtml(message = "description cannot contain HTML tags")
    private String description;

}
