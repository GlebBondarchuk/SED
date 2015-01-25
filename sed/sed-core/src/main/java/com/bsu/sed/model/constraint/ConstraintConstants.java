package com.bsu.sed.model.constraint;

/**
 * @author gbondarchuk
 */
public abstract class ConstraintConstants {
    private ConstraintConstants() {
    }

    /**
     * Email Template.
     */
    public static final String EMAIL_TEMPLATE = "(.+)@(.+)(\\.)(.+)";

    /**
     * User Constraints.
     */
    public static final int USER_LOGIN_MAX_LENGTH = 50;
    public static final int USER_NAME_MAX_LENGTH = 50;
    public static final int USER_PASSWORD_MAX_LENGTH = 50;

    /**
     * User Details Constraints
     */
    public static final int USER_DETAILS_PHONE_MAX_LENGTH = 50;
    public static final int USER_DETAILS_ADDRESS_MAX_LENGTH = 100;


    /**
     * System Attribute Constraints.
     */
    public static final int SYSTEM_VALUE_MAX_LENGTH = 50;
    public static final int SYSTEM_DESCRIPTION_MAX_LENGTH = 200;
}
