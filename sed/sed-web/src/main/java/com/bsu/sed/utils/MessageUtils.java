package com.bsu.sed.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Utils for i18n messages properties.
 *
 * @author gbondarchuk
 */
@Component
public class MessageUtils {
    @Autowired
    private MessageSource messageSource;

    /**
     * Gets message from messages.properties.
     *
     * @param key Key of message.
     * @return Message body.
     */
    public String getMessage(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    /**
     * Get message with arguments.
     *
     * @param key  Keu of message.
     * @param args Multiple arguments.
     * @return Message body.
     */
    public String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}