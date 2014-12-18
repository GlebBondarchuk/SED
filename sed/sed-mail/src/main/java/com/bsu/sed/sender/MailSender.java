package com.bsu.sed.sender;

import com.bsu.sed.model.AttachMailMessage;
import com.bsu.sed.model.MailMessage;

/**
 * @author gbondarchuk
 */
public interface MailSender {
    void send(MailMessage message);

    void send(AttachMailMessage message);
}
