package com.bsu.sed.service;

import com.bsu.sed.dao.UserDao;
import com.bsu.sed.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        throw new RuntimeException("Exception occurred.");
//        return userDao.getByLogin(login);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userDao.getAll();
    }
}
