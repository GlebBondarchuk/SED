package com.bsu.sed.service;

import com.bsu.sed.model.User;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface UserService {
    User getByLogin(String login);

    List<User> getAll();
}
