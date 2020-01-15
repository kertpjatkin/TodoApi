package in.pjatk.todoapi.adapters.misc.sqs;

import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

public interface SqsQueue {

    SendMessageResult sendMessage(SendMessageRequest request);

}
