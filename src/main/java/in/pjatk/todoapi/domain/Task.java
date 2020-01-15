package in.pjatk.todoapi.domain;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
public class Task {

    @Id
    private String id;

    private String description;

    @Setter
    private Instant completedAt;

    @Builder
    private Task(String description) {
        this.description = description;
    }
}
