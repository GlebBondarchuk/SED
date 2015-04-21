package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.dao.generic.browsable.BrowsableDao;
import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.persistent.User;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface UserDao extends BrowsableDao<User> {

    /**
     * Get user by login name.
     *
     * @param login Login name.
     * @return User object.
     */
    User getByLogin(String login);

    /**
     * Get user by email
     *
     * @param email Email
     * @return User object.
     */
    User getByEmail(String email);

    User getByUsername(String username);

    List<User> find(SortOrder order, int limit, int offset);

    boolean existByName(String name);

    boolean existByLogin(String login);

    List<String> getNewsSubscribers();

    List<User> getEnabled();

    List<User> get(List<Long> userIds);
}
