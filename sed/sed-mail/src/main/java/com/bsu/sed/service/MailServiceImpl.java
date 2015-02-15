package com.bsu.sed.service;

import com.bsu.sed.dao.SystemAttributeDao;
import com.bsu.sed.dto.ContactDto;
import com.bsu.sed.model.MailMessage;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.persistent.User;
import com.bsu.sed.service.builder.MailBuilder;
import com.bsu.sed.service.sender.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Attention! This service have to be used inside transactional components only.
 *
 * @author gbondarchuk
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private MailBuilder mailBuilder;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private SystemAttributeDao systemAttributeDao;

    @Override
    public void sendRegistrationMessage(User user) {
        MailMessage message = mailBuilder.buildRegistrationMessage(user);
        mailSender.send(message);
    }

    @Override
    public void sendContactMessage(ContactDto dto) {
        String adminEmail = systemAttributeDao.get(SystemAttributeKey.EMAIL);
        MailMessage message = mailBuilder.buildContactMessage(dto, adminEmail);
        mailSender.send(message);
    }
}
