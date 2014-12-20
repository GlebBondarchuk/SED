package com.bsu.sed.mail.sender;

import com.bsu.sed.common.AbstractTransactionalIntegrationTest;
import com.bsu.sed.model.MailMessage;
import com.bsu.sed.model.MessagePriority;
import com.bsu.sed.service.sender.MailSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tests for sending email messages.
 *
 * @author gbondarchuk
 */
public class MailSenderTest extends AbstractTransactionalIntegrationTest {

    @Autowired
    private MailSender mailSender;

    @Test
    public void simpleSendTest() {
        MailMessage message = createMessage();
        mailSender.send(message);
    }

    private MailMessage createMessage() {
        MailMessage message = MailMessage.newMessage();
        message.setEmailBody("body");
        message.setPriority(MessagePriority.HIGH);
        message.setSubject("subject");
        message.setRecipients("sed.bsu@gmail.com");
        return message;
    }
}
