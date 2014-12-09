package com.bsu.sed.service;

import com.bsu.sed.model.User;

/**
 * @author gbondarchuk
 */
public interface UserService {
    User getByLogin(String login);
}
