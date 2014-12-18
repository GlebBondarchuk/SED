package com.bsu.sed.model;

/**
 * @author gbondarchuk
 */
public enum SystemAttributeKey {
    EMAIL("email"),
    EMAIL_PASSWORD("emailPassword");

    private String name;

    SystemAttributeKey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
