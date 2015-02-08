package com.bsu.sed.model;

/**
 * User authenticated roles.
 *
 * @author gbondarchuk
 */
public enum Role {
    ADMIN, /*Admin Role*/
    TEACHER, /*Teacher Role*/
    STUDENT;  /*Student Role*/

    public boolean in(Role... roles) {
        for (Role role : roles) {
            if (role.name().equals(this.name())) {
                return true;
            }
        }
        return false;
    }
}
