package com.acai.bions_api.Repositories;

public interface EmailSenderGateway {

    void sendEmail(String to, String subject, String body);
}
