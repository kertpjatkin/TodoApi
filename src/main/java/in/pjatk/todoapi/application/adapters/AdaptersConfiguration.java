package in.pjatk.todoapi.application.adapters;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AdaptersConfiguration {

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
}
