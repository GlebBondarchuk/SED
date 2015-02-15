package com.bsu.sed.service;

import com.bsu.sed.dto.ContactDto;
import com.bsu.sed.model.persistent.User;

/**
 * @author gbondarchuk
 */
public interface MailService {
    void sendRegistrationMessage(User user);

    void sendContactMessage(ContactDto dto);
}
