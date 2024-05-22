package com.ufu.tcc.asyncapp.config;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableSqs
public class AwsConfiguration {

    private final String region;

    private final String awsAccessKey;

    private final String awsSecretKey;

    private final String awsEndPointUri;

    @Autowired
    public AwsConfiguration(
            @Value("${cloud.aws.region.static}") String region,
            @Value("${cloud.aws.credentials.access-key}") String awsAccessKey,
            @Value("${cloud.aws.credentials.secret-key}") String awsSecretKey,
            @Value("${cloud.aws.end-point-uri}") String awsEndPointUri
    ) {
        this.region = region;
        this.awsAccessKey = awsAccessKey;
        this.awsSecretKey = awsSecretKey;
        this.awsEndPointUri = awsEndPointUri;
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsEndPointUri, region))
                .build();
    }
}
