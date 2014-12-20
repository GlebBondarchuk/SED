package com.bsu.sed.service.builder;

import com.bsu.sed.model.MailMessage;
import com.bsu.sed.model.persistent.User;

/**
 * Email builder.
 *
 * @author gbondarchuk
 */
public interface MailBuilder {
    /**
     * Default Encoding is ISO-8859-1.
     */
    static final String ENCODING_DEFAULT = "ISO-8859-1";

    MailMessage buildRegistrationMessage(User user);
}
