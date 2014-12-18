package com.bsu.sed.model;

/**
 * @author gbondarchuk
 */
public class AttachMailMessage extends MailMessage {
    private byte[] attachment;
    private String attachmentName;

    public static AttachMailMessage newMessage() {
        return new AttachMailMessage();
    }

    private AttachMailMessage() {
        super();
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}
