package in.pjatk.todoapi.application.createtask;

public interface CreateTaskUseCase {

    CreateTaskResponse createTask(CreateTaskCommand command);

}
