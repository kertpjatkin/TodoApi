package in.pjatk.todoapi.application.adapters.db.mongo;

import in.pjatk.todoapi.domain.entities.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoTodoRepository extends MongoRepository<Todo, String> {

}
