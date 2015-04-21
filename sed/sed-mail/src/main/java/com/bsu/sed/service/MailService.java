package com.bsu.sed.service;

import com.bsu.sed.dto.ContactDto;
import com.bsu.sed.model.persistent.News;
import com.bsu.sed.model.persistent.User;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface MailService {
    void sendRegistrationMessage(User user);

    void sendContactMessage(ContactDto dto);

    void sendNewsToSubscribers(List<News> allNews, List<String> subscribers);

    void sendNotifications(List<Long> userIds, String message, Long fromId);
}
