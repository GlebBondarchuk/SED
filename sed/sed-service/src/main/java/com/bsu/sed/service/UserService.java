package com.bsu.sed.service;

import com.bsu.sed.model.SortOrder;
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

    User getByEmail(String email);

    List<User> getAll();

    List<User> find(SortOrder order, int limit, int offset);

    User update(User user);

    void refresh(User user);

    void delete(Long id);

    User load(Long id);

    User accept(Long id, String login);

    User getByUsername(String username);

    boolean existByName(String name);

    boolean existByLogin(String login);
}
