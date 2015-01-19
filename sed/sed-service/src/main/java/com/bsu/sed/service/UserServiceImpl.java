package com.bsu.sed.service;

import com.bsu.sed.dao.UserDao;
import com.bsu.sed.model.MailMessage;
import com.bsu.sed.model.dto.UserDto;
import com.bsu.sed.model.persistent.User;
import com.bsu.sed.service.builder.MailBuilder;
import com.bsu.sed.service.sender.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private MailBuilder mailBuilder;
    @Autowired
    private MailSender mailSender;

    @Override

    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void create(UserDto details) {
        User user = new User();
        user.setLogin(details.getLogin());
        user.setPassword(details.getPassword());
        user.setRole(details.getRole());
        user.setName(details.getName());
        user.setDisabled(true);
        userDao.create(user);
        MailMessage message = mailBuilder.buildRegistrationMessage(user);
        mailSender.send(message);
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
        userDao.delete(id);
    }

    @Override
    public User load(Long id) {
        return userDao.load(id);
    }
}
