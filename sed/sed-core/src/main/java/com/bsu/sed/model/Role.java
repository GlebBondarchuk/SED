package com.bsu.sed.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User authenticated roles.
 *
 * @author gbondarchuk
 */
public enum Role {
    ADMIN(3), /*Admin Role*/
    TEACHER(2), /*Teacher Role*/
    STUDENT(1),  /*Student Role*/
    ROLE_ANONYMOUS(0);

    private int rank;


    Role(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public static List<Role> getRolesWithEmpty() {
        return Arrays.asList(ADMIN, TEACHER, STUDENT, null);
    }

    public static List<Role> getRoles() {
        return Arrays.asList(ADMIN, TEACHER, STUDENT);
    }

    public boolean in(Role... roles) {
        for (Role role : roles) {
            if (role.name().equals(this.name())) {
                return true;
            }
        }
        return false;
    }
}
