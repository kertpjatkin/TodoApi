package in.pjatk.todoapi.application.adapters;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import in.pjatk.todoapi.useCases.ports.Notifier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class SqsNotifier implements Notifier {

    private final AmazonSQS sqs;
    private final String sqsUrl;
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public SqsNotifier(AmazonSQS sqs, @Value("${sqs.queue_url}") String sqsUrl) {
        this.sqs = sqs;
        this.sqsUrl = sqsUrl;
    }

    @Override
    public void notify(@NotEmpty String todoId) {
        executor.submit(() -> {
            SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(sqsUrl)
                .withMessageBody(todoId);

            SendMessageResult result = sqs.sendMessage(sendMessageRequest);

            log.info("Sent message with id {}", result);
        });
    }
}
