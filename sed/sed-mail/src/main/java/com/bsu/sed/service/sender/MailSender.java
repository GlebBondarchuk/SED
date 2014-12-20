package com.bsu.sed.service.sender;

import com.bsu.sed.model.MailMessage;

/**
 *  Mail Sender.
 *
 * @author gbondarchuk
 */
public interface MailSender {

    /**
     *  Send MailMessage object.
     *
     * @param message MailMessage object.
     */
    void send(MailMessage message);
}
