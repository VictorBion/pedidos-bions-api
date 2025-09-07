package com.acai.bions_api.Service;

import com.acai.bions_api.Repositories.EmailSenderGateway;
import com.acai.bions_api.dtos.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService{

    @Autowired
    EmailSenderGateway emailSenderGateway;

    public void sendEmail(String to, String subject, String body) {
        this.emailSenderGateway.sendEmail(to, subject, body);

    }


}
