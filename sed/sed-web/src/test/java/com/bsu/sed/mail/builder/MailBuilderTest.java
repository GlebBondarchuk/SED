package com.bsu.sed.mail.builder;

import com.bsu.sed.common.AbstractTransactionalIntegrationTest;
import com.bsu.sed.model.MailMessage;
import com.bsu.sed.model.MessagePriority;
import com.bsu.sed.model.persistent.User;
import com.bsu.sed.service.UserService;
import com.bsu.sed.service.builder.MailBuilder;
import com.bsu.sed.service.sender.MailSender;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author gbondarchuk
 */
public class MailBuilderTest extends AbstractTransactionalIntegrationTest {

    private static final String WRONG_SYMBOL = "$";

    @Autowired
    private UserService service;

    @Autowired
    private MailBuilder mailBuilder;

    @Autowired
    private MailSender mailSender;

    @Test
    public void buildMessageTest() {
        buildRegistrationMessage();
    }

    @Test
    public void sendRegistrationMessageTest() {
        MailMessage message = buildRegistrationMessage();
        mailSender.send(message);
    }

    private MailMessage buildRegistrationMessage() {
        User user = service.getByLogin(UserService.SYSTEM_USER);
        user.setLogin("sed.bsu@gmail.com");
        Assert.assertNotNull(user);

        MailMessage message = mailBuilder.buildRegistrationMessage(user);
        Assert.assertEquals(message.getPriority(), MessagePriority.NORMAL);
        Assert.assertEquals(message.getRecipients()[0], user.getLogin());
        Assert.assertEquals(message.getSubject(), "User Registration");
        Assert.assertNotNull(message.getEmailBody());

        if(message.getEmailBody().contains(WRONG_SYMBOL)) {
            Assert.fail("Message body contains unexpected symbols.");
        }
        return message;
    }
}
