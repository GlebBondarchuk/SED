package com.bsu.sed.service;

import com.bsu.sed.common.AbstractTransactionalIntegrationTest;
import com.bsu.sed.model.Role;
import com.bsu.sed.model.dto.UserDto;
import com.bsu.sed.model.persistent.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Integration Test for {@code UserService}
 *
 * @author gbondarchuk
 */
public class UserServiceTest extends AbstractTransactionalIntegrationTest {
    @Autowired
    private UserService userService;

    @Test
    public void systemUserTest() {
        autowireTest();
        User user = userService.getByLogin(UserService.SYSTEM_USER);
        notNullTest(user);
        likeSystemUserTest(user);
    }

    @Test
    public void createUserTest() {
        UserDto user = createUser();
        userService.create(user);
    }

    @Test
    public void updateUserTest() {
        User user = userService.getByLogin(UserService.SYSTEM_USER);
        user.setName("System2");
        user = userService.update(user);
        Assert.assertEquals(user.getName(), "System2");
    }

    @Test
    public void refreshUserTest() {
        User user = userService.getByLogin(UserService.SYSTEM_USER);
        user.setDisabled(true);
        userService.refresh(user);
    }

    @Test
    public void loadUserTest() {
        User user = userService.load(1L);
        notNullTest(user);
        likeSystemUserTest(user);
    }

    @Test
    public void deleteUserTest() {
        User user = userService.getByLogin(UserService.SYSTEM_USER);
        userService.delete(user.getId());
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = userService.getAll();
        Assert.assertNotNull(users);
        for(User user: users) {
            Assert.assertNotNull(user.getId());
            Assert.assertNotNull(user.getLogin());
            Assert.assertNotNull(user.getPassword());
            Assert.assertNotNull(user.getName());
            Assert.assertNotNull(user.getRole());
            Assert.assertNotNull(user.isDisabled());
        }

    }

    private void autowireTest() {
        if (userService == null) {
            Assert.fail("UserService not initialized.");
        }
    }

    private void notNullTest(User user) {
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertNotNull(user.getLogin());
        Assert.assertNotNull(user.getName());
        Assert.assertNotNull(user.getPassword());
        Assert.assertNotNull(user.getRole());
        Assert.assertNotNull(user.isDisabled());
    }

    private void likeSystemUserTest(User user) {
        Assert.assertEquals(user.getLogin(), "sed.bsu@gmail.com");
        Assert.assertEquals(user.getName(), "System");
        Assert.assertEquals(user.getPassword(), "system");
        Assert.assertEquals(user.isDisabled(), true);
    }

    private UserDto createUser() {
        UserDto user = new UserDto();
        user.setName("User");
        user.setPassword("Password");
        user.setRole(Role.TEACHER);
        user.setLogin("Login@gmail.com");
        return user;
    }
}
