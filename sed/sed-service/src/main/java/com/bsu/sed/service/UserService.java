package com.bsu.sed.service;

import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.persistent.User;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface UserService extends BrowsableService {
    /**
     * System user.
     */
    static final String SYSTEM_USER = "sed.bsu";

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

    User getSystem();

    boolean existByName(String name);

    boolean existByLogin(String login);

    List<String> getNewsSubscribers();

    List<User> getEnabled();

    void enable(Long id, boolean enable);
}
