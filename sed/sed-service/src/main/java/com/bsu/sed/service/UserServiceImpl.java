package com.bsu.sed.service;

import com.bsu.sed.dao.PeopleDao;
import com.bsu.sed.dao.UserDao;
import com.bsu.sed.model.MailMessage;
import com.bsu.sed.model.Role;
import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.dto.UserDto;
import com.bsu.sed.model.persistent.People;
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
    private PeopleDao peopleDao;
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
    public List<User> find(SortOrder order, int limit, int offset) {
        return userDao.find(order, limit, offset);
    }

    @Override
    public User create(UserDto details) {
        User user = new User();
        user.setLogin(details.getLogin());
        user.setPassword(details.getPassword());
        user.setPhone(details.getPhone());
        user.setRole(details.getRole());
        user.setName(details.getName());
        user.setPhoto(details.getPhoto());
        user.setDisabled(!user.getRole().in(Role.TEACHER, Role.ADMIN));
        userDao.create(user);

        if (user.getRole().in(Role.TEACHER, Role.ADMIN)) {
            People people = new People();
            people.setUser(user);
            people.setAddress(details.getAddress());
            people.setPosition(details.getPosition());
            peopleDao.create(people);
        }

        MailMessage message = mailBuilder.buildRegistrationMessage(user);
        mailSender.send(message);
        return user;
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

    @Override
    public User accept(String password) {
        User user = userDao.getByPassword(password);
        if (user.isDisabled()) {
            user.setDisabled(false);
            userDao.update(user);
        }
        return user;
    }

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }
}
