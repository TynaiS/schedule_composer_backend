package com.example.schedule_composer.service;

import com.example.schedule_composer.email.MailBody;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendVerificationEmail(MailBody mailBody) throws MessagingException;

}
