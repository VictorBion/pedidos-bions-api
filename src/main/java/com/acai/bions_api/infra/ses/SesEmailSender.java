package com.acai.bions_api.infra.ses;

import com.acai.bions_api.Repositories.EmailSenderGateway;
import com.acai.bions_api.exceptions.EmailServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesEmailSender implements EmailSenderGateway {

    @Autowired
    AmazonSimpleEmailService amazonSimpleEmailService;


    @Override
    public void sendEmail(String to, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest()
                .withSource("vitorbion16@gmail.com")
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(body)))
                );

        try {
            amazonSimpleEmailService.sendEmail(request);
        } catch (AmazonSimpleEmailServiceException exception) {
            throw new EmailServiceException("Email não encontrado!",exception);
        }
    }

}
