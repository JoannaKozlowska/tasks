package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);
    @Autowired
    private JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        LOGGER.info("Starting e-mail preparation...");
        try {
                SimpleMailMessage mailMessage = createMessage(mail);
                javaMailSender.send(mailMessage);
                LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending", e.getMessage(),e);
        }
    }
    private SimpleMailMessage createMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
            if (mail.getMailCC() != null && mail.getMailCC() != "") {
                mailMessage.setTo(mail.getMailTo());
                mailMessage.setCc(mail.getMailCC());
                mailMessage.setSubject(mail.getSubject());
                mailMessage.setText(mail.getMessage());
            } else {
                LOGGER.warn("Warning: Empty CC is not allowed. E-mail won't be sent");
               return null;
            }
        return mailMessage;
    }
}
