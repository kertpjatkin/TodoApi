package in.pjatk.todoapi.domain.entities;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
public class Todo {

    @Id
    private String id;

    private String description;

    @Setter
    private Instant completedAt;

    @Builder
    private Todo(String description) {
        this.description = description;
    }
}
