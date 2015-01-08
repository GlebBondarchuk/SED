package com.bsu.sed.utils;

import com.bsu.sed.model.constraint.ConstraintConstants;

import javax.validation.ConstraintViolation;
import java.util.Set;

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

    public static void printMessages(Set<ConstraintViolation> constraintViolations) {
        for(ConstraintViolation constraintViolation : constraintViolations) {
            System.err.println(constraintViolation.getMessage());
        }
    }
}
