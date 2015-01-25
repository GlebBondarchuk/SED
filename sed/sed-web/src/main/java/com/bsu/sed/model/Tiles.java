package com.bsu.sed.model;

/**
 * @author gbondarchuk
 */
public enum Tiles {
    LOGIN_PAGE("sed-login"),
    MAIN_PAGE("sed-main"),
    SIGN_UP_PAGE("sed-sign-up"),
    USER_PAGE("sed-user"),
    NOT_FOUND_PAGE("error.404"),
    EXCEPTION_PAGE("error.exception"),
    CONTACT_PAGE("sed-contact"),

    //admin pages
    USERS_PAGE("sed-users"),
    SYSTEM_PAGE("sed-system");

    private String tileName;

    Tiles(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
