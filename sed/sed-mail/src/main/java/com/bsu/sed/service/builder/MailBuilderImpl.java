package com.bsu.sed.service.builder;

import com.bsu.sed.dto.ContactDto;
import com.bsu.sed.model.InlineResource;
import com.bsu.sed.model.MailMessage;
import com.bsu.sed.model.MessagePriority;
import com.bsu.sed.model.persistent.News;
import com.bsu.sed.model.persistent.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.EscapeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mail Builder.
 *
 * @author gbondarchuk
 */
@Component
public class MailBuilderImpl implements MailBuilder {

    @Value("#{mailProperties.serverUrl}")
    private String serverUrl;

    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private Md5PasswordEncoder passwordEncoder;

    @Override
    public MailMessage buildRegistrationMessage(User user) {
        MailMessage message = MailMessage.newMessage();
        Map<String, Object> model = getModel();

        message.setSubject("User Registration");

        String encodedLogin = passwordEncoder.encodePassword(user.getLogin(), null);
        model.put("login", encodedLogin);
        model.put("user", user);

        InlineResource image = new InlineResource("bsu", "/image/bsu.png");
        message.addInlineResource(image);

        String emailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                "/template/registration_notification.vm", ENCODING_DEFAULT, model);


        message.setEmailBody(emailBody);
        message.setRecipients(user.getEmail());
        message.setPriority(MessagePriority.NORMAL);

        return message;
    }

    @Override
    public MailMessage buildContactMessage(ContactDto dto, String adminMail) {
        MailMessage message = MailMessage.newMessage();
        Map<String, Object> model = getModel();

        message.setSubject(dto.getSubject());
        model.put("message", dto.getMessage());

        InlineResource image = new InlineResource("bsu", "/image/bsu.png");
        message.addInlineResource(image);

        String emailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                "/template/contact.vm", ENCODING_DEFAULT, model);

        message.setEmailBody(emailBody);
        message.setRecipients(adminMail);
        message.setPriority(MessagePriority.NORMAL);
        return message;
    }

    @Override
    public MailMessage buildNewsMessage(List<News> allNews, List<String> subscribers) {
        MailMessage message = MailMessage.newMessage();
        Map<String, Object> model = getModel();
        model.put("news", allNews);

        InlineResource image = new InlineResource("bsu", "/image/bsu.png");
        message.addInlineResource(image);

        String emailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                "/template/news.vm", ENCODING_DEFAULT, model);

        message.setSubject("Department News");
        message.setEmailBody(emailBody);
        message.setRecipients(subscribers);
        message.setPriority(MessagePriority.NORMAL);
        return message;
    }

    @Override
    public MailMessage buildNotificationMessage(List<User> users, String message, User from) {
        MailMessage mailMessage = MailMessage.newMessage();
        Map<String, Object> model = getModel();
        model.put("message", message);
        model.put("from", from);

        InlineResource image = new InlineResource("bsu", "/image/bsu.png");
        mailMessage.addInlineResource(image);

        String emailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                "/template/notification.vm", ENCODING_DEFAULT, model);

        mailMessage.setSubject("Notification");
        mailMessage.setEmailBody(emailBody);
        mailMessage.setRecipients(getEmails(users));
        mailMessage.setPriority(MessagePriority.NORMAL);
        return mailMessage;
    }

    private List<String> getEmails(List<User> users) {
        List<String> emails = new ArrayList<>();
        for (User user : users) {
            emails.add(user.getEmail());
        }
        return emails;
    }

    /**
     * Create default model for all messages.
     *
     * @return Model map.
     */
    private Map<String, Object> getModel() {
        Map<String, Object> model = new HashMap<>();
        model.put(EscapeTool.DEFAULT_KEY, new EscapeTool());
        model.put("serverUrl", serverUrl);
        return model;
    }
}
