package com.bsu.sed.service;

import com.bsu.sed.model.persistent.User;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface UserService {
    /**
     * System user.
     */
    static final String SYSTEM_USER = "sed.bsu@gmail.com";

    User getByLogin(String login);

    List<User> getAll();
}
