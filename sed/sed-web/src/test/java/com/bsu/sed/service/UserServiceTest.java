package com.bsu.sed.service;

import com.bsu.sed.common.AbstractNonTransactionalIntegrationTest;
import com.bsu.sed.model.persistent.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Integration Test for {@code UserService}
 *
 * @author gbondarchuk
 */
public class UserServiceTest extends AbstractNonTransactionalIntegrationTest {
    @Autowired
    private UserService userService;

    @Test
    public void systemUserTest() {
        autowireTest();
        User user = userService.getByLogin(UserService.SYSTEM_USER);
        notNullTest(user);
        likeSystemUserTest(user);
    }

    private void autowireTest() {
        if (userService == null) {
            Assert.fail("UserService not initialized.");
        }
    }

    private void notNullTest(User user) {
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertNotNull(user.getEmail());
        Assert.assertNotNull(user.getLogin());
        Assert.assertNotNull(user.getName());
        Assert.assertNotNull(user.getPassword());
        Assert.assertNotNull(user.getRole());
        Assert.assertNotNull(user.isDisabled());
    }

    private void likeSystemUserTest(User user) {
        Assert.assertEquals(user.getEmail(), "sed.bsu@gmail.com");
        Assert.assertEquals(user.getLogin(), "system");
        Assert.assertEquals(user.getName(), "System User");
        Assert.assertEquals(user.getPassword(), "system");
        Assert.assertEquals(user.isDisabled(), true);
    }
}
