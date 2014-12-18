package com.bsu.sed.sender;

import com.bsu.sed.dao.SystemAttributeDao;
import com.bsu.sed.model.AttachMailMessage;
import com.bsu.sed.model.MailMessage;
import com.bsu.sed.model.SystemAttributeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.*;

/**
 * @author gbondarchuk
 */
@Component
public class MailSenderImpl implements MailSender {

    @Autowired
    private JavaMailSenderImpl sender;

    @Autowired
    private SystemAttributeDao systemAttributeDao;

    private JavaMailSender getSender() {
        sender.setUsername(systemAttributeDao.get(SystemAttributeKey.EMAIL));
        sender.setPassword(systemAttributeDao.get(SystemAttributeKey.EMAIL_PASSWORD));
        return sender;

    }

    @Override
    public void send(final MailMessage message) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setTo(message.getRecipients());
                helper.setSubject(message.getSubject());
                helper.setText(message.getEmailBody(), true);
                helper.setPriority(message.getPriority().getPriority());
            }
        };

        getSender().send(preparator);
    }
}
