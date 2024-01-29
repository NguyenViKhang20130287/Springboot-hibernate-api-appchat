package com.spring.hibernate.api_appchat.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailConfig {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailConfig(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String subject, String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("OTP for " + subject);
        message.setText("Your OTP is: " + otp);
        javaMailSender.send(message);
    }

}
