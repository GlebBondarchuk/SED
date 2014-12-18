package com.bsu.sed.model;

import java.util.Collection;

/**
 * Mail message object.
 *
 * @author gbondarchuk
 */
public class MailMessage {
    private String[] recipients;
    private String sender;
    private String subject;
    private String emailBody;
    private MessagePriority priority = MessagePriority.NORMAL; //normal priority as default.

    protected MailMessage() {
    }

    public static MailMessage newMessage() {
        return new MailMessage();
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(Collection<String> recipients) {
        this.recipients = recipients.toArray(new String[recipients.size()]);
    }

    public void setRecipients(String recipient) {
        this.recipients = new String[]{recipient};
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public MessagePriority getPriority() {
        return priority;
    }

    public void setPriority(MessagePriority priority) {
        this.priority = priority;
    }
}
