package com.bsu.sed.service;

import com.bsu.sed.dao.UserDao;
import com.bsu.sed.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author gbondarchuk
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }
}
