package com.bsu.sed.model;

/**
 * @author gbondarchuk
 */
public enum Tiles {
    LOGIN_PAGE("sed-login"),
    MAIN_PAGE("sed-main"),
    SIGN_UP_PAGE("sed-sign-up"),
    USER_PAGE("sed-user"),
    EXCEPTION_PAGE("error.exception"),
    CONTACT_PAGE("sed-contact"),
    PEOPLES_PAGE("sed-peoples"),

    //admin pages
    USERS_PAGE("sed-users"),
    SYSTEM_PAGE("sed-system"),
    ADD_NEW_TAB("sed-add-new-tab"),
    EDIT_TAB("sed-edit-tab");

    private String tileName;

    Tiles(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
