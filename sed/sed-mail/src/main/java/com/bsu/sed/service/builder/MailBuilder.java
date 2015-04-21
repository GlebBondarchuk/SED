package com.bsu.sed.service.builder;

import com.bsu.sed.dto.ContactDto;
import com.bsu.sed.model.MailMessage;
import com.bsu.sed.model.persistent.News;
import com.bsu.sed.model.persistent.User;

import java.util.List;

/**
 * Email builder.
 *
 * @author gbondarchuk
 */
public interface MailBuilder {
    /**
     * Default Encoding is ISO-8859-1.
     */
    static final String ENCODING_DEFAULT = "ISO-8859-1";

    MailMessage buildRegistrationMessage(User user);

    MailMessage buildContactMessage(ContactDto dto, String adminEmail);

    MailMessage buildNewsMessage(List<News> allNews, List<String> subscribers);

    MailMessage buildNotificationMessage(List<User> users, String message, User from);
}
