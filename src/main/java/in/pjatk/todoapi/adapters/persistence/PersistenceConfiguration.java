package in.pjatk.todoapi.adapters.persistence;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import in.pjatk.todoapi.adapters.misc.sqs.AwsSqsQueue;
import in.pjatk.todoapi.adapters.misc.sqs.RetryAwareSqsQueue;
import in.pjatk.todoapi.adapters.misc.sqs.SqsQueue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class PersistenceConfiguration {

    @Bean
    @Scope("singleton")
    public BasicAWSCredentials getAwsCredentials(
        @Value("${aws.access_key_id}") String awsAccessKeyId,
        @Value("${aws.secret_access_key}") String secretAccessKey) {
        return new BasicAWSCredentials(awsAccessKeyId, secretAccessKey);
    }

    @Bean
    @Scope("singleton")
    public AmazonSQS getSqs(BasicAWSCredentials awsCredentials) {
        return AmazonSQSClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .withRegion(Regions.EU_CENTRAL_1)
            .build();
    }

    @Bean
    @Qualifier("sqsQueueWithoutRetry")
    public AwsSqsQueue getSqsQueueWithoutRetry(AmazonSQS sqs) {
        return new AwsSqsQueue(sqs);
    }

    @Bean
    @Qualifier("retryAwareSqsQueue")
    public SqsQueue getSqsQueue(@Qualifier("sqsQueueWithoutRetry") AwsSqsQueue sqsQueue,
        @Value("${sqs.number_of_retries}") int maxNumberOfRetries) {
        return new RetryAwareSqsQueue(sqsQueue, maxNumberOfRetries);
    }
}
