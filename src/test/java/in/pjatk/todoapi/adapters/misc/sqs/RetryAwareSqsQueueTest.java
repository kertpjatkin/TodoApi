package in.pjatk.todoapi.adapters.misc.sqs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RetryAwareSqsQueueTest {

    private static final int MAX_NUMBER_OF_RETRIES = 3;

    @Mock
    private SqsQueue sqsQueue;

    private RetryAwareSqsQueue queue;

    @Mock
    private SendMessageRequest sendMessageRequest;

    @Mock
    private SendMessageResult sendMessageResult;

    @BeforeEach
    void setUp() {
        queue = new RetryAwareSqsQueue(sqsQueue, MAX_NUMBER_OF_RETRIES);
        when(sqsQueue.sendMessage(eq(sendMessageRequest))).thenReturn(sendMessageResult);
        when(sendMessageResult.getMessageId()).thenReturn(null);
    }

    @Test
    void whenSendRequestFails_retriesMultipleTimes() {
        assertThrows(SqsException.class, () -> queue.sendMessage(sendMessageRequest));
        verify(sqsQueue, times(MAX_NUMBER_OF_RETRIES)).sendMessage(eq(sendMessageRequest));
    }

    @Test
    void whenSendSucceeds_doesNotRetry() {
        var messageId = "messageId";
        when(sendMessageResult.getMessageId()).thenReturn(messageId);
        var result = queue.sendMessage(sendMessageRequest);

        assertEquals(result.getMessageId(), messageId);
        verify(sqsQueue, times(1)).sendMessage(eq(sendMessageRequest));
    }
}
