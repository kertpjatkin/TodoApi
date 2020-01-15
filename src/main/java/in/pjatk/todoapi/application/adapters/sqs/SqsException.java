package in.pjatk.todoapi.application.adapters.sqs;

public class SqsException extends RuntimeException {

    public SqsException(String message) {
        super(message);
    }

}
