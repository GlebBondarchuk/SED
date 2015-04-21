package com.bsu.sed.service;

import com.bsu.sed.dao.UserDao;
import com.bsu.sed.exception.UserAcceptingException;
import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.persistent.Document;
import com.bsu.sed.model.persistent.User;
import com.bsu.sed.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private Md5PasswordEncoder passwordEncoder;

    @Override
    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public List<User> find(SortOrder order, int limit, int offset) {
        return userDao.find(order, limit, offset);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void refresh(User user) {
        userDao.refresh(user);
    }

    @Override
    public void delete(Long id) {
        User user = userDao.load(id);
        if(user.getLogin().equals(SYSTEM_USER)) {
            throw new RuntimeException("System User can't be deleted");
        }
        userDao.delete(id);
    }

    @Override
    public User load(Long id) {
        return userDao.load(id);
    }

    @Override
    public User accept(Long id, String login) {
        User user = userDao.load(id);
        String encodedLogin = passwordEncoder.encodePassword(user.getLogin(), null);
        if (login.equals(encodedLogin)) {
            user.setDisabled(false);
            return userDao.update(user);
        }
        throw new UserAcceptingException("User not accepted due to incorrect accepting data.");
    }

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public User getSystem() {
        return getByLogin(SYSTEM_USER);
    }

    @Override
    public boolean existByName(String name) {
        return userDao.existByName(name);
    }

    @Override
    public boolean existByLogin(String login) {
        return userDao.existByLogin(login);
    }

    @Override
    public List<String> getNewsSubscribers() {
        return userDao.getNewsSubscribers();
    }

    @Override
    public List<User> getEnabled() {
        return userDao.getEnabled();
    }

    @Override
    public void enable(Long id, boolean enable) {
        User user = userDao.load(id);
        if(user.getLogin().equals(SYSTEM_USER)) {
            return;
        }
        user.setDisabled(!enable);
        userDao.update(user);
    }

    @Override
    public String getJson(int limit, int offset, String search, String sort, SortOrder order) {
        List<String> searchFields = Arrays.asList("name", "login", "email");
        List<User> users = userDao.find(searchFields, limit, offset, search, sort, order);
        long total = userDao.count(searchFields, search);
        return JsonUtils.usersToJson(users, total);
    }

    @Override
    public void delete(List<Long> ids) {
        userDao.delete(ids);
    }
}
