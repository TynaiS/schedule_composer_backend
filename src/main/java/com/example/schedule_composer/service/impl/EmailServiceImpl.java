package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.email.MailBody;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String emailUsername;

//    public void sendSimpleMessage(MailBody mailBody) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(emailUsername);
//        message.setTo(mailBody.to());
//        message.setSubject(mailBody.subject());
//        message.setText(mailBody.text());
//
//        System.out.println(message);
//        System.out.println("h3");
//        emailSender.send(message);
//        System.out.println("h4");
//    }

    @Override
    public void sendVerificationEmail(MailBody mailBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(mailBody.to());
        helper.setSubject(mailBody.subject());
        helper.setText(mailBody.text(), true);

        emailSender.send(message);
    }

}
