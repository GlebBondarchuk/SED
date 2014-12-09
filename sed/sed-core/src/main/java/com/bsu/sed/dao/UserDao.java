package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.model.User;

/**
 * @author gbondarchuk
 */
public interface UserDao extends GenericDao<User, Long> {
    User getByLogin(String login);
}
