package com.bsu.sed.service;

import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.dto.UserDto;
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

    List<User> find(SortOrder order, int limit, int offset);

    User create(UserDto details);

    User update(User user);

    void refresh(User user);

    void delete(Long id);

    User load(Long id);

    User accept(String password);

    User getByUsername(String username);
}
