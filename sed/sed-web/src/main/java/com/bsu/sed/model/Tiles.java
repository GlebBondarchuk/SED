package com.bsu.sed.model;

/**
 * @author gbondarchuk
 */
public enum Tiles {
    LOGIN_PAGE("sed-login"),
    MAIN_PAGE("sed-main"),
    SYSTEM_PAGE("sed-system"),
    SIGN_UP_PAGE("sed-sign-up"),
    NOT_FOUND_PAGE("error.404"),
    EXCEPTION_PAGE("error.exception");

    private String tileName;

    Tiles(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
