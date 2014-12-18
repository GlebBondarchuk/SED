package com.bsu.sed.sender;

import com.bsu.sed.model.AttachMailMessage;
import com.bsu.sed.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * @author gbondarchuk
 */
public interface MailSender {
    void send(MailMessage message);
}
