package com.bsu.sed.model.metadata;

import java.io.Serializable;

/**
 * @author gbondarchuk
 */
public class UserDtoMetadata implements Serializable {
    private int nameLength;
    private int passwordLength;
    private int loginLength;

    public int getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    public int getLoginLength() {
        return loginLength;
    }

    public void setLoginLength(int loginLength) {
        this.loginLength = loginLength;
    }

    public int getNameLength() {
        return nameLength;
    }

    public void setNameLength(int nameLength) {
        this.nameLength = nameLength;
    }
}
