package com.ufu.tcc.commonsdomain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

import java.net.URI;

@Component
public class SESService {

    private final SesClient sesClient;

    @Autowired
    public SESService(@Value("${aws.ses.uri}") String endpoint) {
        this.sesClient = SesClient.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(URI.create(endpoint))
                .build();
    }

    public void sendEmail(String from, String to, String subject, String body) {
        SendEmailRequest request = SendEmailRequest.builder()
                .destination(Destination.builder().toAddresses(to).build())
                .message(Message.builder()
                        .body(Body.builder()
                                .text(Content.builder().data(body).charset("UTF-8").build())
                                .build())
                        .subject(Content.builder().data(subject).charset("UTF-8").build())
                        .build())
                .source(from)
                .build();

        try {
            SendEmailResponse response = sesClient.sendEmail(request);
            System.out.println("Email sent! Message ID: " + response.messageId());
        } catch (SesException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}