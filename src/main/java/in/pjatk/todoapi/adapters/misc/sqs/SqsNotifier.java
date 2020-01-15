package in.pjatk.todoapi.adapters.misc.sqs;

import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import in.pjatk.todoapi.application.ports.Notifier;
import io.medaid.errorhandler.ErrorHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class SqsNotifier implements Notifier {

    private final SqsQueue sqs;
    private final String sqsUrl;
    private final ErrorHandler errorHandler;
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public SqsNotifier(@Qualifier("retryAwareSqsQueue") SqsQueue sqs,
        @Value("${sqs.queue_url}") String sqsUrl, ErrorHandler errorHandler) {
        this.sqs = sqs;
        this.sqsUrl = sqsUrl;
        this.errorHandler = errorHandler;
    }

    @Override
    public void notify(@NotEmpty String taskId) {
        executor.submit(() -> {
            try {
                SendMessageRequest sendMessageRequest = new SendMessageRequest()
                    .withQueueUrl(sqsUrl)
                    .withMessageBody(taskId);

                SendMessageResult result = sqs.sendMessage(sendMessageRequest);

                log.info("Sent message with id {}", result);
            } catch (SqsException e) {
                errorHandler.logError(e);
            }
        });
    }
}
