package com.bsu.sed.service.sender;

import com.bsu.sed.dao.SystemAttributeDao;
import com.bsu.sed.model.InlineResource;
import com.bsu.sed.model.MailMessage;
import com.bsu.sed.model.SystemAttributeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * Mail sender.
 *
 * @author gbondarchuk
 */
@Component
public class MailSenderImpl implements MailSender {

    @Autowired
    private JavaMailSenderImpl sender;

    @Autowired
    private SystemAttributeDao systemAttributeDao;

    /**
     * Init mail sender from system attributes.
     *
     * @return JavaMailSender object.
     */
    private JavaMailSender getSender() {
        sender.setProtocol(systemAttributeDao.get(SystemAttributeKey.EMAIL_PROTOCOL));
        sender.setPort(systemAttributeDao.getInt(SystemAttributeKey.EMAIL_PORT));
        sender.setHost(systemAttributeDao.get(SystemAttributeKey.EMAIL_HOST));
        sender.setUsername(systemAttributeDao.get(SystemAttributeKey.EMAIL));
        sender.setPassword(systemAttributeDao.get(SystemAttributeKey.EMAIL_PASSWORD));
        return sender;
    }

    @Override
    public void send(final MailMessage message) {
        boolean multipart = (message.getInlineResource() != null);
        send(message, multipart);
    }

    /**
     * Send configured mail message.
     *
     * @param message   MailMessage object.
     * @param multipart Multipart flag.
     */
    private void send(final MailMessage message, final boolean multipart) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, multipart);
                helper.setTo(message.getRecipients());
                helper.setSubject(message.getSubject());
                helper.setText(message.getEmailBody(), true);
                helper.setPriority(message.getPriority().getPriority());

                if (multipart) {
                    InlineResource inlineResource = message.getInlineResource();
                    ClassPathResource resource = new ClassPathResource(inlineResource.getUrl());
                    helper.addInline(inlineResource.getIdentifier(), resource);
                }
            }
        };

        getSender().send(preparator);
    }
}
