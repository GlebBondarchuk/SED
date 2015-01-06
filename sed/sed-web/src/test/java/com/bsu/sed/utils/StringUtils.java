package com.bsu.sed.utils;

import com.bsu.sed.model.constraint.ConstraintConstants;

/**
 * @author gbondarchuk
 */
public abstract class StringUtils {
    private static final char DEFAULT_SYMBOL = '*';

    public static String createOverflowString(int maxLength) {
        return org.apache.commons.lang.StringUtils.leftPad("", maxLength + 1, DEFAULT_SYMBOL);
    }

    public static String createValidString(int maxLength) {
        return org.apache.commons.lang.StringUtils.leftPad("", maxLength, DEFAULT_SYMBOL);
    }
}
