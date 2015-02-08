package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.persistent.User;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface UserDao extends GenericDao<User, Long> {

    /**
     * Get user by login name.
     *
     * @param login Login name.
     * @return User object.
     */
    User getByLogin(String login);

    /**
     * Get user by encoded password.
     *
     * @param password Encoded password.
     * @return User object.
     */
    User getByPassword(String password);

    User getByUsername(String username);

    List<User> find(SortOrder order, int limit, int offset);
}
