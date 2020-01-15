package in.pjatk.todoapi.application.adapters.db.mongo;

import in.pjatk.todoapi.domain.entities.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoTodoRepository extends MongoRepository<Task, String> {

}
