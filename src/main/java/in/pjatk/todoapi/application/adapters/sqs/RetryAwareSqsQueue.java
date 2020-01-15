package in.pjatk.todoapi.application.adapters.sqs;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RetryAwareSqsQueue implements SqsQueue {

    private final SqsQueue sqsQueue;
    private final int retryCount;

    @Override
    public SendMessageResult sendMessage(SendMessageRequest request) {
        return sendMessage(request, 0);
    }

    private SendMessageResult sendMessage(SendMessageRequest request, int numberOfTimesRetried) {
        if (numberOfTimesRetried == retryCount) {
            throw new SqsException(
                String.format("Unable to notify user, retried %s times", numberOfTimesRetried));
        }

        var response = sqsQueue.sendMessage(request);

        if (didMessageSendingFail(response)) {
            sendMessage(request, numberOfTimesRetried + 1);
        }

        return response;
    }

    private boolean didMessageSendingFail(SendMessageResult response) {
        return isEmpty(response.getMessageId());
    }
}
