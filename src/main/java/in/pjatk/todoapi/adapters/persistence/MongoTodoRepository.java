package in.pjatk.todoapi.adapters.persistence;

import in.pjatk.todoapi.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoTodoRepository extends MongoRepository<Task, String> {

}
