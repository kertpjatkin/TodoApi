package in.pjatk.todoapi.application.adapters.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AwsSqsQueue implements SqsQueue {

    private final AmazonSQS sqs;

    @Override
    public SendMessageResult sendMessage(SendMessageRequest request) {
        return sqs.sendMessage(request);
    }
}
