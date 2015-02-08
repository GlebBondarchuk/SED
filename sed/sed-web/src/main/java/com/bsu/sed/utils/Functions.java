package com.bsu.sed.utils;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Functions for using on JSP pages.
 *
 * @author gbondarchuk
 */
public class Functions {

    /**
     * Get locale country.
     *
     * @return Locale country. Use [RU] for Russian and [EN] for English.
     * @see /WEB-INF/spring/servlet-context.xml
     */
    public static String getLangUpperCase() {
        return getLang().toUpperCase();
    }

    public static String getLangLowerCase() {
        return getLang().toLowerCase();
    }

    private static String getLang() {
        return LocaleContextHolder.getLocale().getLanguage();
    }
}
